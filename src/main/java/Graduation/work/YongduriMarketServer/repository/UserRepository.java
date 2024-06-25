package Graduation.work.YongduriMarketServer.repository;

import Graduation.work.YongduriMarketServer.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByStudentId(Long studentId);

    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findByNickname(String nickname);
}
