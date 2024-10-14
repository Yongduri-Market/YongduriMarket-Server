package Graduation.work.YongduriMarketServer.domain.state;

import lombok.Getter;

@Getter
public enum TradeStatus {
    거래중(0), // 판매중
    거래예약(1), // 거래예약
    거래완료(2), // 거래완료
    예약중(3); // 예약중

    private final int state;

    TradeStatus(Integer state) {
        this.state = state;
    }

    public static TradeStatus fromInt(int value) {
        for (TradeStatus status : TradeStatus.values()) {
            if (status.getState() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
