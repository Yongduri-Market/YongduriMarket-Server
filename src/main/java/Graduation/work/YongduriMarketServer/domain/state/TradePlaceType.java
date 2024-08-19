package Graduation.work.YongduriMarketServer.domain.state;

import lombok.Getter;

@Getter
public enum TradePlaceType {
    인성관(0) ,
    문예대(1),
    체과대(2),
    종합체육관(4),
    도서관(5),
    에융대(6),
    보1(7),
    보2(8),
    무도대(9),
    대운동장(10);

    private final int state;

    TradePlaceType(Integer state) {
        this.state = state;
    }

    public static TradePlaceType fromInt(int value) {
        for (TradePlaceType place : TradePlaceType.values()) {
            if (place.getState() == value) {
                return place;
            }
        }
        throw new IllegalArgumentException("Invalid place value: " + value);
    }
}
