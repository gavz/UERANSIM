/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EPduSessionType extends ProtocolEnum {
    public static final EPduSessionType IPV4
            = new EPduSessionType(0b001, "IPv4");
    public static final EPduSessionType IPV6
            = new EPduSessionType(0b010, "IPv6");
    public static final EPduSessionType IPV4V6
            = new EPduSessionType(0b011, "IPv4v6");
    public static final EPduSessionType UNSTRUCTURED
            = new EPduSessionType(0b100, "Unstructured");
    public static final EPduSessionType ETHERNET
            = new EPduSessionType(0b101, "Ethernet");
    //public static final EPduSessionType RESERVED
    //        = new EPduSessionType(0b111, "reserved");

    private EPduSessionType(int value, String name) {
        super(value, name);
    }

    public static EPduSessionType fromValue(int value) {
        return fromValueGeneric(EPduSessionType.class, value, null);
    }
}
