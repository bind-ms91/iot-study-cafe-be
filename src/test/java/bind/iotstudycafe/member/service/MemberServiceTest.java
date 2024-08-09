package bind.iotstudycafe.member.service;

import bind.iotstudycafe.member.domain.Member;
import bind.iotstudycafe.member.domain.MemberGrade;
import bind.iotstudycafe.member.dto.MemberSaveDto;
import bind.iotstudycafe.member.dto.MemberSearchCond;
import bind.iotstudycafe.member.dto.MemberUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void save() {

        //given
        MemberSaveDto memberSaveDto = new MemberSaveDto("ms91", "123", "조민성", 23, null);

        //when
        Member savedMember = memberService.save(memberSaveDto);

        //then
        Member findMember = memberService.findById(savedMember.getId()).get();

        log.info("savedMember: {}", savedMember);
        log.info("findMember: {}", findMember);

        assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    void findByMemberId() {

        //given
        MemberSaveDto memberSaveDto = new MemberSaveDto("ms91", "123", "조민성", 23, "OPERATOR");

        //when
        Member savedMember = memberService.save(memberSaveDto);
        Member findMember = memberService.findByMemberId(savedMember.getMemberId()).get();

        //then
        log.info("savedMember: {}", savedMember);
        log.info("findMember: {}", findMember);
        assertThat(savedMember).isEqualTo(findMember);


    }

    @Test
    void update() {

        //given
        MemberSaveDto memberSaveDto = new MemberSaveDto("ms91", "123", "조민성", 23, "OPERATOR");

        Member savedMember = memberService.save(memberSaveDto);
        Long memberId = savedMember.getId();

        //when
        MemberUpdateDto updateParam = new MemberUpdateDto("1234", "민성", 24, "OWNER");
        memberService.update(memberId, updateParam);

        //then
        Member findMember = memberService.findById(memberId).get();
        assertThat(findMember.getMemberPassword()).isEqualTo(updateParam.getMemberPassword());
        assertThat(findMember.getMemberName()).isEqualTo(updateParam.getMemberName());
        assertThat(findMember.getAge()).isEqualTo(updateParam.getAge());
        assertThat(findMember.getMemberGrade()).isEqualTo(getMemberGrade(updateParam.getMemberGrade()));
        log.info("findMember: {}", findMember);
        log.info("updateParam: {}", updateParam);

    }

    @Test
    void findMembers() {

        //given
        MemberSaveDto memberSaveDto1 = new MemberSaveDto("ms91", "123", "조민성1", 23, "OWNER");
        MemberSaveDto memberSaveDto2 = new MemberSaveDto("ms92", "1234", "조민성2", 22, "OPERATOR");
        MemberSaveDto memberSaveDto3 = new MemberSaveDto("ms93", "1235", "조민성3", 21, "BASIC");

        Member savedMember1 = memberService.save(memberSaveDto1);
        Member savedMember2 = memberService.save(memberSaveDto2);
        Member savedMember3 = memberService.save(memberSaveDto3);
        //when

        //유저 아이디로 테스트
        search("ms91", null, null, null, null, savedMember1);
        search("ms", null, null, null, null, savedMember1, savedMember2, savedMember3);
        search("", null, null, null, null, savedMember1, savedMember2, savedMember3);

        //유저 이름 테스트
        search(null, "조민성1", null, null, null, savedMember1);
        search(null, "민성", null, null, null, savedMember1, savedMember2, savedMember3);
        search(null, "", null, null, null, savedMember1, savedMember2, savedMember3);

        //유저 등급 테스트
        search(null, null, null, null, "OWNER", savedMember1);
        search(null, null, null, null, "OPERATOR", savedMember2);
        search(null, null, null, null, "BASIC", savedMember3);
        search(null, null, null, null, null, savedMember1, savedMember2, savedMember3);

        //나이 테스트
        search(null, null, 23, null, null, savedMember1, savedMember2, savedMember3);
        search(null, null, 23, 23, null, savedMember1);
        search(null, null, null, 22, null, savedMember1, savedMember2);

        List<Member> findMembers = memberService.findAll(new MemberSearchCond());
        //then
        //search()

        assertThat(findMembers).hasSize(3);

    }

    @Test
    void deleteById() {

        //given
        MemberSaveDto memberSaveDto = new MemberSaveDto("ms91", "123", "조민성", 23, "OPERATOR");

        Member savedMember = memberService.save(memberSaveDto);
        Long memberId = savedMember.getId();
        Member findMember = memberService.findById(memberId).get();
        log.info("findMember: {}", findMember);

        //when
        memberService.deleteById(memberId);

        //then
        assertThatThrownBy(() -> memberService.findById(memberId).get())
                .isInstanceOf(NoSuchElementException.class);
    }

    void search(String userId, String userName, Integer maxAge, Integer minAge, String memberGrade, Member... members) {
        List<Member> result = memberService.findAll(new MemberSearchCond(userId, userName, maxAge, minAge, memberGrade));
        assertThat(result).containsExactly(members);
    }

    private static MemberGrade getMemberGrade(String grade) {
        return MemberGrade.valueOf(grade.toUpperCase());
    }
}