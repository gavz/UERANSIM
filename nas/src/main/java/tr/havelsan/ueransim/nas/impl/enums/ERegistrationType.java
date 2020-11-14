/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class ERegistrationType extends ProtocolEnum {

    public static final ERegistrationType INITIAL_REGISTRATION
            = new ERegistrationType(0b001, "initial registration");
    public static final ERegistrationType MOBILITY_REGISTRATION_UPDATING
            = new ERegistrationType(0b010, "mobility registration updating");
    public static final ERegistrationType PERIODIC_REGISTRATION_UPDATING
            = new ERegistrationType(0b011, "periodic registration updating");
    public static final ERegistrationType EMERGENCY_REGISTRATION
            = new ERegistrationType(0b100, "emergency registration");
    //public static final ERegistrationType RESERVED
    //        = new ERegistrationType(0b111, "reserved");

    private ERegistrationType(int value, String name) {
        super(value, name);
    }

    public static ERegistrationType fromValue(int value) {
        return fromValueGeneric(ERegistrationType.class, value, null);
    }
}