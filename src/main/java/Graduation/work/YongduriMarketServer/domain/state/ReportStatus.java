package Graduation.work.YongduriMarketServer.domain.state;

import lombok.Getter;

@Getter
public enum ReportStatus {

    접수완료(0), //접수 완료
    답변완료(1); //답변 완료




    private final int value;

    ReportStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ReportStatus fromInt(int value) {
        for (ReportStatus reportStatus : ReportStatus.values()) {
            if (reportStatus.getValue() == value) {
                return reportStatus;
            }
        }
        throw new IllegalArgumentException("Invalid category value: " + value);
    }
}
