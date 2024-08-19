package Graduation.work.YongduriMarketServer.domain.state;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum SalesType {
    책(0),
    의류(1),
    전자기기(2),
    부동산(3),
    기타(4);


    private final int value;

    SalesType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SalesType fromInt(int value) {
        for (SalesType sales : SalesType.values()) {
            if (sales.getValue() == value) {
                return sales;
            }
        }
        throw new IllegalArgumentException("Invalid sales value: " + value);
    }

}
