package Graduation.work.YongduriMarketServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long searchId;

    @JoinColumn
    @ManyToOne
    private User userId;

    @Column
    private String keyword;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt; 

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;

}
