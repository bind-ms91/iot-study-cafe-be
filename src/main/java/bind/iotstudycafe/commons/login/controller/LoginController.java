package bind.iotstudycafe.commons.login.controller;

import bind.iotstudycafe.commons.login.domain.LoginDto;
import bind.iotstudycafe.commons.login.service.LoginService;
import bind.iotstudycafe.commons.web.SessionConst;
import bind.iotstudycafe.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Tag(name = "로그인", description = "로그인 컨트롤러")
@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

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
    private ResponseEntity<Void> login(@Validated @RequestBody LoginDto loginDto, final HttpServletRequest httpRequest) {

        Member loginMember = loginService.login(loginDto);

        log.info("loginMember: {}", loginMember);

        HttpSession session = httpRequest.getSession();
        session.setAttribute("memberId", loginMember);
        session.setMaxInactiveInterval(3600);

        return ResponseEntity.ok().build();
    }

}
