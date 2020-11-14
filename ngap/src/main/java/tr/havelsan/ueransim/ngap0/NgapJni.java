/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0;

public class NgapJni {

    private static final int ATS_NONSTANDARD_PLAINTEXT = 1;
    private static final int ATS_UNALIGNED_BASIC_PER = 8;
    private static final int ATS_UNALIGNED_CANONICAL_PER = 9;
    private static final int ATS_ALIGNED_BASIC_PER = 10;
    private static final int ATS_ALIGNED_CANONICAL_PER = 11;
    private static final int ATS_BASIC_XER = 12;
    private static final int ATS_CANONICAL_XER = 13;

    private static final int RESULT_OK = 0;
    private static final int RESULT_DECODING_FAILED = 1;
    private static final int RESULT_ENCODING_FAILED = 2;

    private static native byte[] convertEncoding(byte[] data, int fromEncoding, int toEncoding, int[] result, int pduType);

    private static byte[] convert(byte[] data, int fromEncoding, int toEncoding, NgapDataUnitType pduType) {
        int[] resCode = new int[1];
        var converted = convertEncoding(data, fromEncoding, toEncoding, resCode, pduType.value);
        if (resCode[0] == RESULT_DECODING_FAILED) {
            throw new RuntimeException("NgapJni convertEncoding RESULT_DECODING_FAILED");
        }
        if (resCode[0] == RESULT_ENCODING_FAILED) {
            throw new RuntimeException("NgapJni convertEncoding RESULT_ENCODING_FAILED");
        }
        return converted;
    }

    public static String aperToXer(byte[] data, NgapDataUnitType pduType) {
        var converted = convert(data, ATS_ALIGNED_CANONICAL_PER, ATS_CANONICAL_XER, pduType);
        return new String(converted);
    }

    public static String aperToPlainText(byte[] data, NgapDataUnitType pduType) {
        var converted = convert(data, ATS_ALIGNED_CANONICAL_PER, ATS_NONSTANDARD_PLAINTEXT, pduType);
        return new String(converted);
    }

    public static byte[] xerToAper(String xer, NgapDataUnitType pduType) {
        return convert(xer.getBytes(), ATS_CANONICAL_XER, ATS_ALIGNED_CANONICAL_PER, pduType);
    }
}
