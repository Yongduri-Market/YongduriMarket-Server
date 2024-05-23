package Graduation.work.YongduriMarketServer.repository;

import Graduation.work.YongduriMarketServer.domain.Ask;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public interface AskRepository extends JpaRepository<Ask, Long> {

    List<Ask> findByOrderByCreatedAtDesc();
    Optional<Ask> findByAskId(Long id);

}
