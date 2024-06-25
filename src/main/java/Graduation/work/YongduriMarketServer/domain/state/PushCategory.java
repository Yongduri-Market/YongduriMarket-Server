package Graduation.work.YongduriMarketServer.domain.state;

import lombok.Getter;


@Getter
public enum PushCategory {

    후기작성(0);




    private final int value;

    PushCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PushCategory fromInt(int value) {
        for (PushCategory pushCategory : PushCategory.values()) {
            if (pushCategory.getValue() == value) {
                return pushCategory;
            }
        }
        throw new IllegalArgumentException("Invalid category value: " + value);
    }
}
