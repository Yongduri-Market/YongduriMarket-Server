package Graduation.work.YongduriMarketServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Search {


    @Id
    @Column(nullable = false, unique = true, length = 255)
    private Long searchId;

    @Column
    private String keyword;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt; //연,월,일,시,분,초

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;

}
