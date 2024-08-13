package bind.iotstudycafe.member;

import bind.iotstudycafe.member.domain.Member;
import bind.iotstudycafe.member.domain.MemberGrade;
import bind.iotstudycafe.member.dto.MemberSaveDto;
import bind.iotstudycafe.member.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestMemberDataInit {

    private final MemberService memberService;

    /**
     * 테스트용 데이터 추가
     */
//    @PostConstruct
//    public void init() {
//
//        MemberSaveDto Member1 = new MemberSaveDto("ms91", "1111", "조민성1", 21, "BASIC");
//        MemberSaveDto Member2 = new MemberSaveDto("ms92", "2222", "조민성2", 22, "OPERATOR");
//
//        memberService.save(Member1);
//        memberService.save(Member2);
//
//    }

}
