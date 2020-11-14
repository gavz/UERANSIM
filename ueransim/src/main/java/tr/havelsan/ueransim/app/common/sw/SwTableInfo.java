/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.sw;

import java.util.Map;

public class SwTableInfo extends SocketWrapper{
    public final Map<String, Map<String, Object>> tableInfo;

    public SwTableInfo(Map<String, Map<String, Object>> tableInfo) {
        this.tableInfo = tableInfo;
    }
}
