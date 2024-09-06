package bind.iotstudycafe.commons.login.service;

import bind.iotstudycafe.commons.login.domain.LoginDto;
import bind.iotstudycafe.member.domain.Member;
import bind.iotstudycafe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService memberService;

    public Member login(LoginDto loginDto) {

        Optional<Member> findMemberOptional = memberService.findByMemberId(loginDto.getLoginId());

        log.info("findMemberOptional: {}", findMemberOptional);

        //TODO 로그인 예외처리
        if (findMemberOptional.isPresent()) {

            //스프링 시큐리티 적용 예정
            //passwordEncoder.matches() 사용하여 암호화된 패워스워드 검증 예정
            return findMemberOptional.filter(m ->
                    m.getMemberId().equals(loginDto.getLoginId()) && m.getMemberPassword().equals(loginDto.getPassword()))
                    .orElse(null);
        }

        return null;
    }

}

