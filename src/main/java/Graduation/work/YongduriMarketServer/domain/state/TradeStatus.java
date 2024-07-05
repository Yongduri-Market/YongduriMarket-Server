package Graduation.work.YongduriMarketServer.domain.state;

import lombok.Getter;

@Getter
public enum TradeStatus {
    ACTIVE(0), // 판매중
    WAITING(1), // 거래예약
    FINISHED(2); // 거래완료

    private final int state;

    TradeStatus(Integer state) {
        this.state = state;
    }

    public static TradeStatus fromInt(int value) {
        for (TradeStatus tradeStatus : TradeStatus.values()) {
            if (tradeStatus.getState() == value) {
                return tradeStatus;
            }
        }
        throw new IllegalArgumentException("Invalid tradeStatus value: " + value);
    }
}
