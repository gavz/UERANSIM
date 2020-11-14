/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class IEAuthenticationResponseParameter extends InformationElement4 {
    public OctetString rawData;

    public IEAuthenticationResponseParameter() {
    }

    public IEAuthenticationResponseParameter(OctetString rawData) {
        this.rawData = rawData;
    }

    @Override
    protected IEAuthenticationResponseParameter decodeIE4(OctetInputStream stream, int length) {
        var res = new IEAuthenticationResponseParameter();
        res.rawData = stream.readOctetString(length);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        stream.writeOctetString(rawData);
    }
}
