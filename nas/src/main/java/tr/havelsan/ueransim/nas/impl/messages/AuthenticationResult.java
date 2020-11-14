/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IEAbba;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;

public class AuthenticationResult extends PlainMmMessage {
    public IENasKeySetIdentifier ngKSI;
    public IEEapMessage eapMessage;
    public IEAbba abba;

    public AuthenticationResult() {
        super(EMessageType.AUTHENTICATION_RESULT);
    }

    public AuthenticationResult(IENasKeySetIdentifier ngKSI, IEEapMessage eapMessage, IEAbba abba) {
        this();
        this.ngKSI = ngKSI;
        this.eapMessage = eapMessage;
        this.abba = abba;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("ngKSI");
        builder.mandatoryIE("eapMessage");
        builder.optionalIE(0x38, "abba");
    }
}
