/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.pdu;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;

public class NGAP_ProtocolIEContainer extends NGAP_SequenceOf<NGAP_ProtocolIE> {

    @Override
    public String getAsnName() {
        return "ProtocolIE-Container";
    }

    @Override
    public String getXmlTagName() {
        return "ProtocolIE-Container";
    }

    @Override
    public Class<NGAP_ProtocolIE> getItemType() {
        return NGAP_ProtocolIE.class;
    }
}
