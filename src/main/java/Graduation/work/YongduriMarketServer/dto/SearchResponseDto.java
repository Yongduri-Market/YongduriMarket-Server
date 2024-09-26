package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.Search;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseDto
{

    private Long searchId;
    private Long userId;
    private String keyword;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    public static SearchResponseDto getSearchDto(Search search) {
        return new SearchResponseDto(
                search.getSearchId(),
                search.getUserId().getStudentId(),
                search.getKeyword(),
                search.getCreatedAt(),
                search.getUpdatedAt()
        );
    }
}
