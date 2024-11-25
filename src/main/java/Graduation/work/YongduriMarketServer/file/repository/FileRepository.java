package Graduation.work.YongduriMarketServer.file.repository;

import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, Long> {
}