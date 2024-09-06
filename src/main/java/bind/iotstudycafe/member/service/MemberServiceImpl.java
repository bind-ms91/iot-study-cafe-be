package bind.iotstudycafe.member.service;

import bind.iotstudycafe.member.domain.Member;
import bind.iotstudycafe.member.domain.MemberGrade;
import bind.iotstudycafe.member.dto.MemberSaveDto;
import bind.iotstudycafe.member.dto.MemberSearchCond;
import bind.iotstudycafe.member.dto.MemberUpdateDto;
import bind.iotstudycafe.member.repository.MemberRepository;
import bind.iotstudycafe.member.repository.querydsl.MemberQueryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Override
    public Member save(MemberSaveDto memberSaveDto) {

        MemberGrade memberGrade = getMemberGrade(memberSaveDto.getMemberGrade());

        //TODO 스프링시큐리티 패워스드 암호화 예정

        Member member = Member.builder()
                .memberId(memberSaveDto.getMemberId())
                .memberPassword(memberSaveDto.getMemberPassword())
                .memberName(memberSaveDto.getMemberName())
                .age(memberSaveDto.getAge())
                .memberGrade(memberGrade)
                .build();



        return memberRepository.save(member);
    }


    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<Member> findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    @Override
    public List<Member> findAll(MemberSearchCond cond) {
        return memberQueryRepository.findAll(cond);
    }

    @Override
    public void update(Long id, MemberUpdateDto updateParam) {
        Member findMember = memberRepository.findById(id).orElseThrow();

        MemberGrade memberGrade = getMemberGrade(updateParam.getMemberGrade());

        findMember.update(
                updateParam.getMemberPassword(),
                updateParam.getMemberName(),
                updateParam.getAge(),
                memberGrade
        );

//        findMember.setMemberGrade(updateParam.getMemberGrade());
//        findMember.setMemberPassword(updateParam.getMemberPassword());
//        findMember.setMemberName(updateParam.getMemberName());
//        findMember.setAge(updateParam.getAge());
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

//    private static MemberGrade getMemberGrade(String grade) {
//
//        if (grade == null) {
//            return null;
//        }
//
//        try {
//            return MemberGrade.valueOf(grade.toUpperCase());
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("유효하지 않은 회원 등급입니다: " + grade, e);
//        }
//    }

    private static MemberGrade getMemberGrade(String grade) {

        if (grade == null) {
            return null;
        } else {
            return MemberGrade.valueOf(grade.toUpperCase());
        }

    }
}
