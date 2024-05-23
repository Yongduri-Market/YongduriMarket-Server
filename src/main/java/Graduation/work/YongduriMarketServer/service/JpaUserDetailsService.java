package Graduation.work.YongduriMarketServer.service;

import Graduation.work.YongduriMarketServer.config.CustomUserDetails;
import Graduation.work.YongduriMarketServer.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import Graduation.work.YongduriMarketServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Value("${admin.studentId1}")
    private Long admin1;
    @Value("${admin.studentId2}")
    private Long admin2;


    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(nickname).orElseThrow(
                () -> new UsernameNotFoundException("사용자가 존재하지 않습니다.")
        );
        return new CustomUserDetails(user);
    }

    //    @Override
    @Transactional
    public UserDetails loadUserByStudentId(Long studentId) throws UsernameNotFoundException {
        User user = userRepository.findByStudentId(studentId).orElseThrow(
                () -> new UsernameNotFoundException("사용자가 존재하지 않습니다.")
        );
        CustomUserDetails userDetails = new CustomUserDetails(user);

        if (user.getStudentId().equals(admin1) ||
                user.getStudentId().equals(admin2)) {
            userDetails.setRole("ADMIN");
        } else {
            userDetails.setRole("USER");
        }

        return userDetails;
    }
}
