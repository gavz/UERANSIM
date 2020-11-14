/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils.bits;

public final class Bit12 extends BitN {

    public Bit12(int value) {
        super(value, 12);
    }

    public Bit12(Bit bit11, Bit bit10, Bit bit9, Bit bit8, Bit bit7, Bit bit6, Bit bit5, Bit bit4, Bit bit3, Bit bit2, Bit bit1, Bit bit0) {
        super(bit11, bit10, bit9, bit8, bit7, bit6, bit5, bit4, bit3, bit2, bit1, bit0);
    }

    public Bit12(int bit11, int bit10, int bit9, int bit8, int bit7, int bit6, int bit5, int bit4, int bit3, int bit2, int bit1, int bit0) {
        super(bit11, bit10, bit9, bit8, bit7, bit6, bit5, bit4, bit3, bit2, bit1, bit0);
    }
}