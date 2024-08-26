package Graduation.work.YongduriMarketServer.repository;

import Graduation.work.YongduriMarketServer.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByOrderByCreatedAtDesc();
}