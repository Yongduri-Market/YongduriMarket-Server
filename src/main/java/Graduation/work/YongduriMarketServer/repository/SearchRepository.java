package Graduation.work.YongduriMarketServer.repository;

import Graduation.work.YongduriMarketServer.domain.Report;
import Graduation.work.YongduriMarketServer.domain.Search;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public interface SearchRepository extends JpaRepository<Search, Long> {

    List<Search> findByOrderByCreatedAtDesc();

    Optional<Search> findBySearchId(Long searchId);



}
