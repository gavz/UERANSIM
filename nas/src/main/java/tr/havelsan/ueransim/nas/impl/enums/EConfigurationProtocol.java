package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EConfigurationProtocol extends ProtocolEnum {
    public static final EConfigurationProtocol PPP
            = new EConfigurationProtocol(0b000, "PPP for use with IP PDP type or IP PDN type");

    private EConfigurationProtocol(int value, String name) {
        super(value, name);
    }

    public static EConfigurationProtocol fromValue(int value) {
        // All values are accepted as PPP for current version of the protocol.
        return PPP;
    }
}
