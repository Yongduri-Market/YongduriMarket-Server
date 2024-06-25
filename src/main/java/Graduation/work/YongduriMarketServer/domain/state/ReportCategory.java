package Graduation.work.YongduriMarketServer.domain.state;

import lombok.Getter;


@Getter
public enum ReportCategory {

    유저신고(0),
    앱버그신고(1),
    기타(2);



    private final int value;

    ReportCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ReportCategory fromInt(int value) {
        for (ReportCategory reportCategory : ReportCategory.values()) {
            if (reportCategory.getValue() == value) {
                return reportCategory;
            }
        }
        throw new IllegalArgumentException("Invalid category value: " + value);
    }
}
