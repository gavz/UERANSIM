package tr.havelsan.ueransim.app.common.sw;

import java.util.Map;

public class SwTableInfo extends SocketWrapper{
    public final Map<String, Map<String, Object>> tableInfo;

    public SwTableInfo(Map<String, Map<String, Object>> tableInfo) {
        this.tableInfo = tableInfo;
    }
}
