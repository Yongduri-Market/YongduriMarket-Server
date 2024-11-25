package Graduation.work.YongduriMarketServer.domain.state;
import lombok.Getter;
@Getter
public enum ReportType {

    유저신고(0),
    앱버그신고(1);




    private final int value;
    ReportType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static ReportType fromInt(int value) {
        for (ReportType reportCategory : ReportType.values()) {
            if (reportCategory.getValue() == value) {
                return reportCategory;
            }
        }
        throw new IllegalArgumentException("Invalid category value: " + value);
    }
}