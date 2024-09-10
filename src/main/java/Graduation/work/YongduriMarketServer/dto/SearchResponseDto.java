package Graduation.work.YongduriMarketServer.dto;

import Graduation.work.YongduriMarketServer.domain.ChatRoom;
import Graduation.work.YongduriMarketServer.domain.Search;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
