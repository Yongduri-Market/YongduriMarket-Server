package Graduation.work.YongduriMarketServer.service;

import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TokenService {
    private final UserRepository userRepository;


    public User findByRefreshToken(String refreshToken) {
        return userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("문제 발생"));
    }
}
