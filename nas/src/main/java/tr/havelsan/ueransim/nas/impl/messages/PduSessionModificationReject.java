/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.IE5gSmCause;
import tr.havelsan.ueransim.nas.impl.ies.IEExtendedProtocolConfigurationOptions;
import tr.havelsan.ueransim.nas.impl.ies.IEGprsTimer3;

public class PduSessionModificationReject extends PlainSmMessage {
    public IE5gSmCause smCause;
    public IEGprsTimer3 backOffTimerValue;
    public IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions;

    public PduSessionModificationReject() {
        super(EMessageType.PDU_SESSION_MODIFICATION_REJECT);
    }

    public PduSessionModificationReject(IE5gSmCause smCause, IEGprsTimer3 backOffTimerValue, IEExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions) {
        this();
        this.smCause = smCause;
        this.backOffTimerValue = backOffTimerValue;
        this.extendedProtocolConfigurationOptions = extendedProtocolConfigurationOptions;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("smCause");
        builder.optionalIE(0x37, "backOffTimerValue");
        builder.optionalIE(0x7B, "extendedProtocolConfigurationOptions");
    }
}
