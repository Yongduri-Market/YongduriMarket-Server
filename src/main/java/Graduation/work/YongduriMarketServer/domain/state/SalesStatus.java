package Graduation.work.YongduriMarketServer.domain.state;

import lombok.Getter;

@Getter
public enum SalesStatus {
    판매중(0), // 판매중
    판매완료(1), // 거래예약
    판매취소(2), // 거래완료
    예약중(3); // 예약중

    private final int state;

    SalesStatus(Integer state) {
        this.state = state;
    }

    public static SalesStatus fromInt(int value) {
        for (SalesStatus status : SalesStatus.values()) {
            if (status.getState() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
