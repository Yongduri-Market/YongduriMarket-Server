package Graduation.work.YongduriMarketServer.repository;

import Graduation.work.YongduriMarketServer.domain.Report;
import Graduation.work.YongduriMarketServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByOrderByCreatedAtDesc();
    Optional<Report> findByReportId(Long reportId);
}
