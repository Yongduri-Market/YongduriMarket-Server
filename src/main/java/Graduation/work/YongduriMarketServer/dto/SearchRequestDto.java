package Graduation.work.YongduriMarketServer.dto;

import lombok.Getter;
import lombok.Setter;

public class SearchRequestDto {
    @Getter
    @Setter
    public static class RegisterDto{
        private String keyword;

    }
    @Getter
    @Setter
    public static class DeleteDto{
        private Long searchId;

    }

    @Getter
    @Setter
    public static class CheckDto {
        private String keyword;
    }
}
