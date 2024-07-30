package bind.iotstudycafe.commons.config;

import bind.iotstudycafe.member.repository.MemberRepository;
import bind.iotstudycafe.member.repository.querydsl.MemberQueryRepository;
import bind.iotstudycafe.member.service.MemberService;
import bind.iotstudycafe.member.service.MemberServiceImpl;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MemberConfig {

    private final EntityManager em;
    private final MemberRepository memberRepository; //SpringDataJPA

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository, memberQueryRepository());
    }

    @Bean
    public MemberQueryRepository memberQueryRepository() {
        return new MemberQueryRepository(em);
    }

}
