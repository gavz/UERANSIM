package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.utils.OctetInputStream;

public class IESorTransparentContainer extends InformationElement6 {

    @Override
    protected InformationElement6 decodeIE6(OctetInputStream stream, int length) {
        throw new NotImplementedException("SorTransparentContainer not implemented yet");
    }
}
