package Graduation.work.YongduriMarketServer.repository;

import Graduation.work.YongduriMarketServer.domain.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByOrderByCreatedAtDesc();
}