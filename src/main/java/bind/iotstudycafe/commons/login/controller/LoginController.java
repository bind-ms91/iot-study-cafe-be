package bind.iotstudycafe.commons.login.controller;

import bind.iotstudycafe.commons.config.RedisConfig;
import bind.iotstudycafe.commons.login.domain.LoginDto;
import bind.iotstudycafe.commons.login.service.LoginService;
import bind.iotstudycafe.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.HttpCookie;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Tag(name = "로그인", description = "로그인 컨트롤러")
@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

//    private final RedisTemplate<String, Object> redisTemplate;

    private final LoginService loginService;

//    @ResponseBody
//    @PostMapping("/login")
//    public String login(@Validated  @ModelAttribute("loginDto") LoginDto loginDto, BindingResult bindingResult,
//                        @RequestParam(defaultValue ="/") String redirectURL, HttpServletRequest request) {
//
//        if(bindingResult.hasErrors()) {
//            return "/home";
//        }
//
//        Member loginMember = loginService.login(loginDto.getLoginId(), loginDto.getPassword());
//
//        if(loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
//            return "/home";
//        }
//
//        //로그인 성공 처리
//        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
//        HttpSession session = request.getSession();
//        //세션에 로그인 회원 정보 보관
//        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
//
//        return "ok";
//    }
//
//    @PostMapping("/logout")
//    public String logoutV3(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if(session != null) {
//            session.invalidate();
//        }
//        return "redirect:/";
//    }
//
//    private static void expireCookie(HttpServletResponse response, String cookieName) {
//        Cookie cookie = new Cookie(cookieName, null);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//    }

    @Operation(summary = "로그인", description = "로그인",
            responses = {@ApiResponse(responseCode = "200", description = "로그인 성공")}
    )
    @PostMapping("/login")
    private ResponseEntity<Member> login(@Validated @RequestBody LoginDto loginDto, HttpServletRequest request) {

        Member loginMember = loginService.login(loginDto);

        log.info("loginMember: {}", loginMember);

        if (loginMember == null) {
            return ResponseEntity.notFound().build();
        }

        if(loginMember != null) {
            //로그인 성공 처리
            HttpSession session = request.getSession();
            //세션에 로그인 회원 정보 보관
            session.setAttribute("loginMember", loginMember.getMemberId());
        }

        return ResponseEntity.ok(loginMember);
    }

    @Operation(summary = "로그아웃", description = "로그아웃",
            responses = {@ApiResponse(responseCode = "200", description = "로그아웃 성공")}
    )
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {

        String sessionId = request.getHeader(HttpHeaders.COOKIE);

        log.info("sessionId: {}", sessionId);

        HttpSession session = request.getSession(false);

        log.info("session: {}", session);

        if(session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok("로그아웃 성공");

//        String sessionId = request.getHeader(HttpHeaders.COOKIE);
//
//        log.info("sessionId: {}", sessionId);
//
//        // 쿠키 추출
//        Optional<Cookie> findCookie = Arrays.stream(request.getCookies())
//                .filter(cookie -> cookie.getName().equals("JSESSIONID"))
//                .findFirst();
//
//        log.info("findCookie = {}", findCookie);
//
//        if (findCookie.isPresent()) {
//            String value = findCookie.get().getValue();
//            log.info("value: {}", value);
//        }
//
//        if (sessionId != null) {
//
//            log.info("sessionId: {}", sessionId);
//
//            // Base64 디코딩
//            byte[] decodedBytes = Base64.getDecoder().decode(sessionId);
//            String decodedSessionId = new String(decodedBytes);
//
//            log.info("Decoded sessionId = {}", decodedSessionId);
//
//            // Redis에서 세션 삭제
//            String sessionKey = "spring:session:sessions:" + decodedSessionId;
//            redisTemplate.delete(sessionKey);
//        }
//
//        return ResponseEntity.ok("로그아웃 성공");
    }

}
