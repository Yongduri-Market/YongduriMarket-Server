package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.Ask;
import Graduation.work.YongduriMarketServer.domain.state.AskCategory;
import Graduation.work.YongduriMarketServer.domain.state.StatusCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AskResponseDto {
    private Long askId;
    private Long studentId;
    private String title;
    private String contents;
    private StatusCategory status;
    private String answer;
    private String file;
    private AskCategory askCategory;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;


    public static AskResponseDto GetAskDTO(Ask ask) {
        return new AskResponseDto(

                ask.getAskId(),
                ask.getUser().getStudentId(),
                ask.getTitle(),
                ask.getContents(),
                ask.getStatus(),
                ask.getAnswer(),
                ask.getFile(),
                ask.getAskCategory(),
                ask.getCreatedAt(),
                ask.getUpdatedAt()
        );
    }

}
