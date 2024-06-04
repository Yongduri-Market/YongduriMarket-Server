package Graduation.work.YongduriMarketServer.repository;

import Graduation.work.YongduriMarketServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
