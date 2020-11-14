/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.listeners;

import io.javalin.websocket.WsConnectContext;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.sw.SwTime;
import tr.havelsan.ueransim.app.utils.ConfigUtils;
import tr.havelsan.ueransim.app.utils.SocketWrapperSerializer;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupFailure;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupResponse;
import tr.havelsan.ueransim.utils.console.BaseConsole;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoadTestMessagingListener implements INodeMessagingListener {
    public static final Map<String, Map<String, Object>> infoMap;

    static {
        Map<String, Map<String, Object>> map = new LinkedHashMap<>();

        final Map<String, Object> registration = new LinkedHashMap<>();
        registration.put("name", "Registration");
        registration.put("parent", null);
        map.put("registration", registration);

        final Map<String, Object> phase1 = new LinkedHashMap<>();
        phase1.put("name", "[Registration-Authentication]");
        phase1.put("parent", "registration");
        map.put("phase1", phase1);

        final Map<String, Object> authentication = new LinkedHashMap<>();
        authentication.put("name", "Authentication");
        authentication.put("parent", "registration");
        map.put("authentication", authentication);

        final Map<String, Object> phase2 = new LinkedHashMap<>();
        phase2.put("name", "[Authentication-SecurityModeControl]");
        phase2.put("parent", "registration");
        map.put("phase2", phase2);

        final Map<String, Object> securityModeControl = new LinkedHashMap<>();
        securityModeControl.put("name", "SecurityModeControl");
        securityModeControl.put("parent", "registration");
        map.put("securityModeControl", securityModeControl);

        final Map<String, Object> phase3 = new LinkedHashMap<>();
        phase3.put("name", "[SecurityModeControl-RegistrationAccept]");
        phase3.put("parent", "registration");
        map.put("phase3", phase3);

        infoMap = Collections.unmodifiableMap(map);
    }

    private final BaseConsole console;
    private WsConnectContext ws;

    private final Map<Integer, Long> ngSetupTimers = new ConcurrentHashMap<>();
    private final Map<String, Long> registrationTimers = new ConcurrentHashMap<>();
    private final Map<String, Long> authenticationTimers = new ConcurrentHashMap<>();
    private final Map<String, Long> securityModeControlTimers = new ConcurrentHashMap<>();
    private final Map<String, Long> phase1Timers = new ConcurrentHashMap<>();
    private final Map<String, Long> phase2Timers = new ConcurrentHashMap<>();
    private final Map<String, Long> phase3Timers = new ConcurrentHashMap<>();
    private final Map<String, Long> deregistrationTimers = new ConcurrentHashMap<>();

    public LoadTestMessagingListener(BaseConsole console) {
        this.console = console;
    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {
        String deviceName = ConfigUtils.generateNodeName(ctx);

        if (message instanceof NGAP_NGSetupRequest) {
            int gnbId = ((GnbSimContext) ctx).config.gnbId;
            ngSetupTimers.put(gnbId, System.currentTimeMillis());
        } else if (message instanceof RegistrationRequest) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            registrationTimers.put(supi, System.currentTimeMillis());
            phase1Timers.put(supi, System.currentTimeMillis());
        } else if (message instanceof AuthenticationResponse) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            phase2Timers.put(supi, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - authenticationTimers.get(supi);
            sendTime(deviceName, "authentication", true, delta);
            console.println(null, "\u2714 [Authentication (UE/RAN)] [ue: %s] [%d ms]", supi, delta);
        } else if (message instanceof SecurityModeComplete) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            phase3Timers.put(supi, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - securityModeControlTimers.get(supi);
            sendTime(deviceName, "securityModeControl", true, delta);
            console.println(null, "\u2714 [Security Mode Control (UE/RAN)] [ue: %s] [%d ms]", supi, delta);
        } else if (message instanceof DeRegistrationRequestUeOriginating) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            deregistrationTimers.put(supi, System.currentTimeMillis());
        }
    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        String deviceName = ConfigUtils.generateNodeName(ctx);

        if (message instanceof NGAP_NGSetupFailure) {
            int gnbId = ((GnbSimContext) ctx).config.gnbId;
            long delta = System.currentTimeMillis() - ngSetupTimers.get(gnbId);
            sendTime(deviceName, "ngSetup", false, delta);
            console.println(null, "\u2718 [NGSetup] [gnbId: %d] [%d ms]", gnbId, delta);
        } else if (message instanceof NGAP_NGSetupResponse) {
            int gnbId = ((GnbSimContext) ctx).config.gnbId;
            long delta = System.currentTimeMillis() - ngSetupTimers.get(gnbId);
            sendTime(deviceName, "ngSetup", true, delta);
            console.println(null, "\u2714 [NGSetup] [gnbId: %d] [%d ms]", gnbId, delta);
        } else if (message instanceof RegistrationReject) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            long delta = System.currentTimeMillis() - registrationTimers.get(supi);
            sendTime(deviceName, "registration", false, delta);
            console.println(null, "\u2718 [Registration] [ue: %s] [%d ms]", supi, delta);
        } else if (message instanceof RegistrationAccept) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            long delta = System.currentTimeMillis() - registrationTimers.get(supi);
            sendTime(deviceName, "registration", true, delta);
            console.println(null, "\u2714 [Registration] [ue: %s] [%d ms]", supi, delta);

            long delta2 = System.currentTimeMillis() - phase3Timers.get(supi);
            sendTime(deviceName, "phase3", true, delta2);
            console.println(null, "\u2714 [Phase 3 (Network)] [SecurityModeControl-RegistrationAccept] [ue: %s] [%d ms]", supi, delta2);
        } else if (message instanceof AuthenticationRequest) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            authenticationTimers.put(supi, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - phase1Timers.get(supi);
            sendTime(deviceName, "phase1", true, delta);
            console.println(null, "\u2714 [Phase 1 (Network)] [Registration-Authentication] [ue: %s] [%d ms]", supi, delta);
        } else if (message instanceof SecurityModeCommand) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            securityModeControlTimers.put(supi, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - phase2Timers.get(supi);
            sendTime(deviceName, "phase2", true, delta);
            console.println(null, "\u2714 [Phase 2 (Network)] [Authentication-SecurityModeControl] [ue: %s] [%d ms]", supi, delta);
        } else if (message instanceof DeRegistrationAcceptUeOriginating) {
            String supi = (((UeSimContext) ctx).ueConfig.supi).toString();
            long delta = System.currentTimeMillis() - deregistrationTimers.get(supi);
            sendTime(deviceName, "deregistration", true, delta);
            console.println(null, "\u2714 [De-Registration] [ue: %s] [%d ms]", supi, delta);
        }
    }

    @Override
    public void onConnect(WsConnectContext ctx) {
        this.ws = ctx;
    }

    private void sendTime(String deviceName, String intervalId, boolean status, long delta) {
        var swTime = new SwTime(deviceName, intervalId, status, delta);

        if (ws != null) {
            ws.send(SocketWrapperSerializer.toJson(swTime));
        }
    }
}