package bind.iotstudycafe.commons.web.session;

import bind.iotstudycafe.member.domain.Member;
import bind.iotstudycafe.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SessionInfoController {

//    @GetMapping("/session-info")
//    public String sessionInfo(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            return "세션이 없습니다.";
//        }
//
//        //세션 데이터 출력
//        session.getAttributeNames().asIterator()
//                .forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));
//
//        log.info("sessionId={}", session.getId());
//        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
//        log.info("getCreationTime={}", new Date(session.getCreationTime()) );
//        log.info("getLastAccessedTime={}", new Date(session.getLastAccessedTime()));
//        log.info("isNew={}", session.isNew());
//
//        return "세션 출력";
//    }

}
