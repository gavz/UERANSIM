/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.utils;

public enum Severity {
    DEBUG,
    SUCCESS,
    INFO,
    WARNING,
    ERROR,
    FUNC_IN,
    FUNC_OUT;

    public boolean dispatch() {
        switch (this) {
            case SUCCESS:
            case ERROR:
                return true;
            default:
                return false;
        }
    }
}
