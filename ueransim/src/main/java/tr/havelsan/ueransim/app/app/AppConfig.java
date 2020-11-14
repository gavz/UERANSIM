/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app;

import tr.havelsan.ueransim.app.common.configs.GnbConfig;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.utils.MtsInitializer;
import tr.havelsan.ueransim.mts.ImplicitTypedObject;
import tr.havelsan.ueransim.mts.MtsContext;
import tr.havelsan.ueransim.utils.Constants;
import tr.havelsan.ueransim.utils.console.Console;
import tr.havelsan.ueransim.utils.jcolor.AnsiPalette;

public class AppConfig {

    public final MtsContext mts;
    private final String profile;

    public AppConfig() {
        this.mts = new MtsContext();
        MtsInitializer.initDefaultMts(this.mts);

        var root = (ImplicitTypedObject) mts.decoder.decode("config/profile.yaml");
        var profile = root.getString("selected-profile");
        this.profile = "config/" + profile + "/";
        Console.println(AnsiPalette.PAINT_IMPORTANT_INFO, "INFO: Selected profile: \"%s\"", profile);

        var general = (ImplicitTypedObject) mts.decoder.decode(this.profile + "general.yaml");
        Constants.USE_LONG_MNC = general.getBool("use-long-mnc");
        Constants.TREAT_ERRORS_AS_FATAL = general.getBool("treat-errors-as-fatal");
    }

    public GnbConfig createGnbConfig() {
        return mts.constructor.construct(GnbConfig.class, ((ImplicitTypedObject) mts.decoder.decode(profile + "gnb.yaml")), true);
    }

    public UeConfig createUeConfig() {
        return mts.constructor.construct(UeConfig.class, ((ImplicitTypedObject) mts.decoder.decode(profile + "ue.yaml")), true);
    }
}
