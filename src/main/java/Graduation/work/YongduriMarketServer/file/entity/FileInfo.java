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
<<<<<<< HEAD

    public void update(String fileName, String fileOriName, String fileUrl, String fileExt, Long fileSize) {
        this.fileName = fileName;
        this.fileOriName = fileOriName;
        this.fileUrl = fileUrl;
        this.fileExt = fileExt;
        this.fileSize = fileSize;
    }
=======
>>>>>>> 3cd1db23067c925001b498afd4ae827eed6b4d5f
}
