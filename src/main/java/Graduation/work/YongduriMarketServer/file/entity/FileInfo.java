package Graduation.work.YongduriMarketServer.file.entity;


import Graduation.work.YongduriMarketServer.domain.Base;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_info_id")
    private Long id;

    private String fileName;

    private String fileOriName;

    private String fileUrl;

    private String fileExt;

    private Long fileSize;
}
