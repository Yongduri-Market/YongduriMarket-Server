package Graduation.work.YongduriMarketServer.domain.state;

import lombok.Getter;


@Getter
public enum PushType {

    후기작성(0);




    private final int value;

    PushType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PushType fromInt(int value) {
        for (PushType pushCategory : PushType.values()) {
            if (pushCategory.getValue() == value) {
                return pushCategory;
            }
        }
        throw new IllegalArgumentException("Invalid category value: " + value);
    }
}
