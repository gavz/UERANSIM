/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.crypto;

import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class ZUC {

    public static Octet4[] zuc(OctetString key, OctetString iv, int length) {
        int[] rn = zuc(key.toByteArray(), iv.toByteArray(), length);
        Octet4[] rm = new Octet4[rn.length];
        for (int i = 0; i < rn.length; i++) {
            rm[i] = new Octet4(Integer.toUnsignedLong(rn[i]));
        }
        return rm;
    }

    private static native int[] zuc(byte[] key, byte[] iv, int length);
}
