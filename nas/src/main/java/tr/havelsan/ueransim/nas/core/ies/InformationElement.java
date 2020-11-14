/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.core.ies;

import tr.havelsan.ueransim.nas.core.ProtocolValue;
import tr.havelsan.ueransim.utils.OctetInputStream;

/**
 * Format   | Meaning                | IEI present | LI present  | Value part present
 * T        | Type only              | yes         | no          | no
 * V        | Value only             | no          | no          | yes
 * TV       | Type and Value         | yes         | no          | yes
 * LV       | Length and Value       | no          | yes         | yes
 * TLV      | Type, Length and Value | yes         | yes         | yes
 * LV-E     | Length and Value       | no          | yes         | yes
 * TLV-E    | Type, Length and Value | yes         | yes         | yes
 */
public abstract class InformationElement extends ProtocolValue {
    public abstract InformationElement decodeIE(OctetInputStream stream);
}
