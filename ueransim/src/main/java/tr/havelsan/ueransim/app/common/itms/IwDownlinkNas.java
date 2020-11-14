/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.itms;

import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.UUID;

public class IwDownlinkNas {
    public final UUID ue;
    public final OctetString nasPdu;

    public IwDownlinkNas(UUID ue, OctetString nasPdu) {
        this.ue = ue;
        this.nasPdu = nasPdu;
    }
}
