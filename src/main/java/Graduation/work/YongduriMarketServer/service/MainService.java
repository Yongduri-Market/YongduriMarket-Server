package Graduation.work.YongduriMarketServer.service;

import Graduation.work.YongduriMarketServer.domain.*;
import Graduation.work.YongduriMarketServer.dto.*;
import Graduation.work.YongduriMarketServer.exception.CustomException;
import Graduation.work.YongduriMarketServer.exception.ErrorCode;
import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
import Graduation.work.YongduriMarketServer.file.service.FileService;
import Graduation.work.YongduriMarketServer.file.service.ImageType;
import Graduation.work.YongduriMarketServer.repository.*;
import Graduation.work.YongduriMarketServer.security.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MainService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final TokenProvider tokenProvider;
    private final JavaMailSender javaMailSender;;
    private final BoardService boardService;
    private final FileService fileService;
//

    private String authNum;
    private long exp_refreshToken = Duration.ofDays(14).toMillis();

    //회원가입
    public Boolean join(JoinDto request, MultipartFile file) throws Exception {
        //400 - 데이터 미입력
        if(request.getStudentId() == null || request.getPassword() == null || request.getName() == null ||request.getNickname() ==null
        || request.getBirthDate() ==null || request.getPhone() == null){
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }
        //409 - 데이터 중복(학번)
        if(userRepository.findByStudentId(request.getStudentId()).isPresent()){
            throw new CustomException(ErrorCode.DUPLICATE);
        }
        //409 - 데이터 중복(닉네임)
        if(userRepository.findByNickname(request.getNickname()).isPresent()){
            throw new CustomException(ErrorCode.DUPLICATE);
        }

        List<FileInfo> fileInfos = null;

        if(file != null) {
            fileInfos = fileService.saveFile(List.of(file), ImageType.USER);
        }

        try{
            User user = User.builder()
                    .studentId(request.getStudentId())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .nickname(request.getNickname())
                    .phone(request.getPhone())
                    .birthDate(request.getBirthDate())
                    .fileInfo(fileInfos != null ? fileInfos.get(0) : null)
                    .build();
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
        }

    //로그인
    public LoginResponseDto login(LoginRequestDto request) throws Exception {

        // 400 - 데이터 미입력
        if (request.getStudentId() == null || request.getPassword() == null ) {
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        }

        // 404 - 회원 없음
        User user = userRepository.findByStudentId(request.getStudentId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
        // 401 - 회원정보 불일치
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.USER_DATA_INCONSISTENCY);
        }


        try {
            // 토큰 발급 (추가 정보 확인 하기 위해 이름 포함 시킴)
            user.setRefreshToken(createRefreshToken(user));
            LoginResponseDto response = LoginResponseDto.builder()
                    .studentId(user.getStudentId())
                    .nickname(user.getNickname())
                    .token(TokenDto.builder()
                            .accessToken(tokenProvider.createToken(user))
                            .refreshToken(user.getRefreshToken())
                            .build())
                    .build();

            return response;
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);

        }
    }


    //회원가입 시 이메일 인증
    public String joinEmail(String email) throws MessagingException, UnsupportedEncodingException {
        if(email == null) throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        MimeMessage emailForm = createEmailForm(email + "@yiu.ac.kr", "용두리마켓 회원가입 인증번호");
        javaMailSender.send(emailForm);
        return authNum;
    }
    //비밀번호 변경 시 이메일 인증
    public String pwdEmail(String email) throws MessagingException, UnsupportedEncodingException {
        if(email == null) throw new CustomException(ErrorCode.INSUFFICIENT_DATA);
        MimeMessage emailForm = createEmailForm(email + "@yiu.ac.kr", "비밀번호 재설정을 위한 안내");
        javaMailSender.send(emailForm);
        return authNum;
    }
    private MimeMessage createEmailForm(String email, String title) throws MessagingException, UnsupportedEncodingException {
        // 코드 생성
        createCode();
        String setFrom = "dkswndus6988@gmail.com";
        String toEmail = email;
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, toEmail);
        message.setSubject(title);

        // 메일 내용 설정
        String msgOfEmail = "";
        msgOfEmail += "<div style='margin: 20px;'>";
        msgOfEmail += "<h1>안녕하세요 용인대학교 용두리마켓 입니다.</h1>";
        msgOfEmail += "<br>";
        if (title.equals("용두리마켓 회원가입 인증번호")) {
            msgOfEmail += "<p><strong>용두리마켓 가입을 위한 인증번호입니다.</strong><p>";
        } else if (title.equals("비밀번호 재설정을 위한 안내")) {
            msgOfEmail += "<p><strong>비밀번호 재설정을 위한 안내입니다.</strong><p>";
        }
        msgOfEmail += "<br>";
        msgOfEmail += "<p>아래 인증번호를 확인하여 이메일 주소 인증을 완료해주세요.<p>";
        msgOfEmail += "<br>";
        msgOfEmail += "<div align='center' style='border-top: 1px solid black; border-bottom: 1px solid black; font-family: verdana;'>";
        msgOfEmail += "<div style='font-size: 150%; justify-content: center; align-items: center; display: flex; flex-direction: column; height: 100px;'>";
        msgOfEmail += "<strong style='color: blue; margin-top: auto; margin-bottom: auto;'>";
        msgOfEmail += authNum + "</strong>";
        msgOfEmail += "</div>";
        msgOfEmail += "</div>";
        msgOfEmail += "</div>";
        message.setFrom(setFrom);
        message.setText(msgOfEmail, "utf-8", "html");
        return message;
    }


    public void createCode() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for(int i = 0; i < 4; i++) {
            int digit = random.nextInt(10);
            key.append(digit);
        }
        authNum = key.toString();
    }
    //비밀번호 변경
    public Boolean pwdChange(PwdChangeRequestDto request)  throws Exception{
        // 400 - 데이터 없음
        if(request.getStudentId() == null || request.getPassword() == null)
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);

        // 404 - 회원 존재 확인
        User user = userRepository.findByStudentId(request.getStudentId()).orElseThrow(()
                -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
        try {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
        }
        catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return true;
    }







    //닉네임 중복
    public Boolean nicknameCheck(NicknameCheckRequestDto request) throws Exception{
        // 409 데이터 중복
        if (userRepository.findByNickname(request.getNickname()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE);
        }
        return true;

    }
    
    
    
    
    
    
    public TokenDto refreshAccessToken(TokenDto token) throws Exception {
        Long studentId = null;
        try {
            studentId = tokenProvider.getStudentId(token.getAccessToken());
        }//만료된 경우
        catch (ExpiredJwtException e) {
            studentId = e.getClaims().get("studentId", Long.class);
        }
        //이메일을 사용하여 해당 사용자를 db에서 찾음
        User user = userRepository.findByStudentId(studentId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_EXIST_MEMBER));
        //유효성 검사
        Token refreshToken = validRefreshToken(user, token.getRefreshToken());

        try {
            if (refreshToken != null) {    //유효하다면 토큰 생성
                return TokenDto.builder()
                        .accessToken(tokenProvider.createToken(user))
                        .refreshToken(refreshToken.getRefreshToken())
                        .build();
            }//로그인 필요
            else {
                throw new CustomException(ErrorCode.LOGIN_REQUIRED);
            }
        }
        catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    // refreshToken 생성
    private String createRefreshToken(User user) {
        Token token = tokenRepository.save(
                Token.builder()
                        .studentId(user.getStudentId())
                        .refreshToken(UUID.randomUUID().toString())
                        .expiration(exp_refreshToken)
                        .build()
        );
        return token.getRefreshToken();
    }

    //유효성 검사
    public Token validRefreshToken(User user, String refreshToken) throws Exception {
        //토큰 없을 시 로그인 필요
        Token token = tokenRepository.findById(user.getStudentId())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_REQUIRED));

        //RefreshToken 만료
        if(token.getRefreshToken() == null) throw new CustomException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        try{
            if(token.getExpiration() < 10){   //10초
                token.setExpiration(1000L); //1000초
                tokenRepository.save(token);
            }

            //로그인 필요
            if(!token.getRefreshToken().equals(refreshToken)){
                throw new CustomException(ErrorCode.LOGIN_REQUIRED);
            }else{
                return token;
            }
        }
        catch (Exception e){
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }



    
}
