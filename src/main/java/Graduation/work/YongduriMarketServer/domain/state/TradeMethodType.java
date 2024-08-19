package Graduation.work.YongduriMarketServer.domain.state;

import lombok.Getter;

@Getter
public enum TradeMethodType {
    판매하기(0),
    나눔하기(1),
    구해요(2);



    private final int state;

    TradeMethodType(Integer state) {
        this.state = state;
    }

    public static TradeMethodType fromInt(int value) {
        for (TradeMethodType method : TradeMethodType.values()) {
            if (method.getState() == value) {
                return method;
            }
        }
        throw new IllegalArgumentException("Invalid method value: " + value);
    }
}
