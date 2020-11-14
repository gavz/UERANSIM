/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.values;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.exceptions.ReservedOrInvalidValueException;
import tr.havelsan.ueransim.utils.octets.Octet;

public class VTimeZone extends NasValue {
    public Octet value;

    public VTimeZone() {
    }

    public VTimeZone(Octet value) {
        this.value = value;
    }

    public VTimeZone(String hex) {
        this.value = new Octet(hex);
    }

    public VTimeZone(int hourDifference) {
        this.value = fromHourDifference(hourDifference).value;
    }

    @Override
    public VTimeZone decode(OctetInputStream stream) {
        return fromOctet(stream.readOctet());
    }

    public static VTimeZone fromOctet(Octet octet) {
        valueControl(octet);
        var res = new VTimeZone();
        res.value = octet;
        return res;
    }

    public static VTimeZone fromOctet(int octet) {
        return fromOctet(new Octet(octet));
    }

    public static VTimeZone fromHourDifference(int difference) {
        return fromQuarterDifference(difference * 4);
    }

    public static VTimeZone fromQuarterDifference(int difference) {
        return fromDifference(difference < 0, Math.abs(difference));
    }

    public static VTimeZone fromDifference(boolean isNegative, int difference) {
        var octet = new Octet();
        octet = octet.setBit(7, isNegative ? 1 : 0);

        int nibble0 = difference % 10;
        int nibble1 = difference / 10;
        if (nibble1 > 7)
            throw new ReservedOrInvalidValueException("invalid difference at timezone value: " + difference);

        octet = octet.setBitRange(0, 3, nibble1);
        octet = octet.setBitRange(4, 6, nibble0);

        var res = new VTimeZone();
        res.value = octet;
        return res;
    }

    private static void valueControl(Octet rawOctet) {
        int nibble1 = rawOctet.getBitRangeI(0, 3);
        int nibble0 = rawOctet.getBitRangeI(4, 6);

        if (nibble0 > 9) throw new ReservedOrInvalidValueException("invalid timezone value: " + rawOctet.toHexString());
        if (nibble1 > 7) throw new ReservedOrInvalidValueException("invalid timezone value: " + rawOctet.toHexString());
    }

    public int getSign() {
        return value.getBitI(7) == 0 ? 1 : -1;
    }

    public int getDifferenceInQuarters() {
        // (They are swapped nibble)
        int nibble1 = value.getBitRangeI(0, 3);
        int nibble0 = value.getBitRangeI(4, 6);
        return nibble1 * 10 + nibble0;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(value);
    }

    @Override
    public String toString() {
        int quarters = getDifferenceInQuarters();
        int whole = quarters / 4;
        int remainder = quarters % 4;

        var diffHour = whole + "";
        if (remainder == 1) diffHour += ":15";
        else if (remainder == 2) diffHour += ":30";
        else if (remainder == 3) diffHour += ":45";

        return "GMT " + (getSign() == 1 ? "+" : "-") + diffHour;
    }
}
