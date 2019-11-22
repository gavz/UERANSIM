package com.runsim.backend.nas.impl.ies;

import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.impl.enums.EImsVoPsN3gpp;
import com.runsim.backend.nas.impl.enums.EMcsIndicator;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.octets.Octet;

public class IE5gsNetworkFeatureSupport extends InformationElement4 {
    public EImsVoPs3gpp imsVoPs3gpp;
    public EImsVoPsN3gpp imsVoPsN3gpp;
    public EEmergencyServiceSupport3gppIndicator emc;
    public EEmergencyServiceFallback3gppIndicator emf;
    public EInterworkingWithoutN26InterfaceIndicator iwkN26;
    public EMpsIndicator mpsi;
    public EEmergencyServiceSupportNon3gppIndicator emcn3;
    public EMcsIndicator mcsi;

    @Override
    protected IE5gsNetworkFeatureSupport decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsNetworkFeatureSupport();

        var octet = stream.readOctet();
        res.imsVoPs3gpp = EImsVoPs3gpp.fromValue(octet.getBitI(0));
        res.imsVoPsN3gpp = EImsVoPsN3gpp.fromValue(octet.getBitI(1));
        res.emc = EEmergencyServiceSupport3gppIndicator.fromValue(octet.getBitRangeI(2, 3));
        res.emf = EEmergencyServiceFallback3gppIndicator.fromValue(octet.getBitRangeI(4, 5));
        res.iwkN26 = EInterworkingWithoutN26InterfaceIndicator.fromValue(octet.getBitI(6));
        res.mpsi = EMpsIndicator.fromValue(octet.getBitI(7));
        octet = stream.readOctet();
        res.emcn3 = EEmergencyServiceSupportNon3gppIndicator.fromValue(octet.getBitI(0));
        res.mcsi = EMcsIndicator.fromValue(octet.getBitI(1));
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var octet = new Octet();
        octet = octet.setBit(0, imsVoPs3gpp.intValue());
        octet = octet.setBit(1, imsVoPsN3gpp.intValue());
        octet = octet.setBitRange(2, 3, emc.intValue());
        octet = octet.setBitRange(4, 5, emf.intValue());
        octet = octet.setBit(1, iwkN26.intValue());
        octet = octet.setBit(1, mpsi.intValue());
        stream.writeOctet(octet);

        octet = new Octet();
        octet = octet.setBit(0, emcn3.intValue());
        octet = octet.setBit(1, mcsi.intValue());
        stream.writeOctet(octet);
    }

    public static class EEmergencyServiceFallback3gppIndicator extends ProtocolEnum {
        public static final EEmergencyServiceFallback3gppIndicator NOT_SUPPORTED
                = new EEmergencyServiceFallback3gppIndicator(0b00, "Emergency services fallback not supported");
        public static final EEmergencyServiceFallback3gppIndicator SUPPORTED_IN_NR_CONNECTED_TO_5GCN_ONLY
                = new EEmergencyServiceFallback3gppIndicator(0b01, "Emergency services fallback supported in NR connected to 5GCN only");
        public static final EEmergencyServiceFallback3gppIndicator SUPPORTED_IN_EUTRA_CONNTECTED_TO_5GCN_ONLY
                = new EEmergencyServiceFallback3gppIndicator(0b10, "Emergency services fallback supported in E-UTRA connected to 5GCN only");
        public static final EEmergencyServiceFallback3gppIndicator SUPPORTED_IN_NR_CONNTECTED_TO_5GCN_AND_EUTRA_CONNECTED_TO_5GCN
                = new EEmergencyServiceFallback3gppIndicator(0b11, "Emergency services fallback supported in NR connected to 5GCN and E-UTRA connected to 5GCN");

        private EEmergencyServiceFallback3gppIndicator(int value, String name) {
            super(value, name);
        }

        public static EEmergencyServiceFallback3gppIndicator fromValue(int value) {
            return fromValueGeneric(EEmergencyServiceFallback3gppIndicator.class, value);
        }
    }

    public static class EEmergencyServiceSupport3gppIndicator extends ProtocolEnum {
        public static final EEmergencyServiceSupport3gppIndicator NOT_SUPPORTED
                = new EEmergencyServiceSupport3gppIndicator(0b00, "Emergency services not supported");
        public static final EEmergencyServiceSupport3gppIndicator SUPPORTED_IN_NR_CONNECTED_TO_5GCN_ONLY
                = new EEmergencyServiceSupport3gppIndicator(0b01, "Emergency services supported in NR connected to 5GCN only");
        public static final EEmergencyServiceSupport3gppIndicator SUPPORTED_IN_EUTRA_CONNTECTED_TO_5GCN_ONLY
                = new EEmergencyServiceSupport3gppIndicator(0b10, "Emergency services supported in E-UTRA connected to 5GCN only");
        public static final EEmergencyServiceSupport3gppIndicator SUPPORTED_IN_NR_CONNTECTED_TO_5GCN_AND_EUTRA_CONNECTED_TO_5GCN
                = new EEmergencyServiceSupport3gppIndicator(0b11, "Emergency services supported in NR connected to 5GCN and E-UTRA connected to 5GCN");

        private EEmergencyServiceSupport3gppIndicator(int value, String name) {
            super(value, name);
        }

        public static EEmergencyServiceSupport3gppIndicator fromValue(int value) {
            return fromValueGeneric(EEmergencyServiceSupport3gppIndicator.class, value);
        }
    }

    public static class EEmergencyServiceSupportNon3gppIndicator extends ProtocolEnum {
        public static final EEmergencyServiceSupportNon3gppIndicator NOT_SUPPORTED
                = new EEmergencyServiceSupportNon3gppIndicator(0b0, "Emergency services not supported over non-3GPP access");
        public static final EEmergencyServiceSupportNon3gppIndicator SUPPORTED
                = new EEmergencyServiceSupportNon3gppIndicator(0b1, "Emergency services supported over non-3GPP access");

        private EEmergencyServiceSupportNon3gppIndicator(int value, String name) {
            super(value, name);
        }

        public static EEmergencyServiceSupportNon3gppIndicator fromValue(int value) {
            return fromValueGeneric(EEmergencyServiceSupportNon3gppIndicator.class, value);
        }
    }

    public static class EInterworkingWithoutN26InterfaceIndicator extends ProtocolEnum {
        public static final EInterworkingWithoutN26InterfaceIndicator NOT_SUPPORTED
                = new EInterworkingWithoutN26InterfaceIndicator(0b0, "Interworking without N26 interface not supported");
        public static final EInterworkingWithoutN26InterfaceIndicator SUPPORTED
                = new EInterworkingWithoutN26InterfaceIndicator(0b1, "Interworking without N26 interface supported");

        private EInterworkingWithoutN26InterfaceIndicator(int value, String name) {
            super(value, name);
        }

        public static EInterworkingWithoutN26InterfaceIndicator fromValue(int value) {
            return fromValueGeneric(EInterworkingWithoutN26InterfaceIndicator.class, value);
        }
    }

    public static class EMpsIndicator extends ProtocolEnum {
        public static final EMpsIndicator NOT_SUPPORTED
                = new EMpsIndicator(0b0, "Access identity 1 not valid in RPLMN or equivalent PLMN");
        public static final EMpsIndicator SUPPORTED
                = new EMpsIndicator(0b1, "Access identity 1 valid in RPLMN or equivalent PLMN");

        private EMpsIndicator(int value, String name) {
            super(value, name);
        }

        public static EMpsIndicator fromValue(int value) {
            return fromValueGeneric(EMpsIndicator.class, value);
        }
    }

    public static class EImsVoPs3gpp extends ProtocolEnum {
        public static final EImsVoPs3gpp NOT_SUPPORTED
                = new EImsVoPs3gpp(0b0, "IMS voice over PS session not supported over 3GPP access");
        public static final EImsVoPs3gpp SUPPORTED
                = new EImsVoPs3gpp(0b1, "IMS voice over PS session supported over 3GPP access");

        private EImsVoPs3gpp(int value, String name) {
            super(value, name);
        }

        public static EImsVoPs3gpp fromValue(int value) {
            return fromValueGeneric(EImsVoPs3gpp.class, value);
        }
    }
}
