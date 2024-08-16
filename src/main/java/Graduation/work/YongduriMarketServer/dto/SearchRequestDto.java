package Graduation.work.YongduriMarketServer.dto;

import lombok.Getter;
import lombok.Setter;

public class SearchRequestDto {
    @Getter
    @Setter
    public class RegisterDto{
        private String keyword;

    }
    @Getter
    @Setter
    public class DeleteDto{
        private Long searchId;

    }

    @Getter
    @Setter
    public class CheckDto {
        private String keyword;
    }
}
