package tr.havelsan.ueransim.app.common.sw;

public class SwTime extends SocketWrapper {
    public final String deviceName;
    public final String intervalId;
    public final boolean status;
    public final long delta;

    public SwTime(String deviceName, String intervalId, boolean status, long delta) {
        this.deviceName = deviceName;
        this.intervalId = intervalId;
        this.status = status;
        this.delta = delta;
    }
}
