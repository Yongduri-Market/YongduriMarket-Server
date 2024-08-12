package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.Board;
import Graduation.work.YongduriMarketServer.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class ChatRoomRequestDto {
    @Getter
    @Setter
    public class CreateDto {
        private Long boardId;
        private Long buyer;

    }


    @Getter
    @Setter
    public class DetailDto {
        private Long roomId;
    }
    @Getter
    @Setter
    public class EndTradeDto {
        private Long roomId;
        private Long boardId;
    }
    @Getter
    @Setter
    public class DeleteDto {
        private Long roomId;

    }

    @Getter
    @Setter
    public class reserveTradeDto {
        private Long roomId;
        private Long boardId;

    }
}
