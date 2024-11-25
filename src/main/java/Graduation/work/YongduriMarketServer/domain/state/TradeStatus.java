package Graduation.work.YongduriMarketServer.domain.state;

import lombok.Getter;

@Getter
public enum TradeStatus {
<<<<<<< HEAD
    거래중(0), // 판매중
    거래예약(1), // 거래예약
    거래완료(2), // 거래완료
    예약중(3); // 예약중
=======
    판매중(0), // 판매중
    거래예약(1), // 거래예약
    거래완료(2); // 거래완료
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f

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
