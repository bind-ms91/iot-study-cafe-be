package bind.iotstudycafe.commons.exampleDomain;

import bind.iotstudycafe.commons.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.commons.exampleDomain.repository.ExampleDomainRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TestExamoleDataInit {

    private final ExampleDomainRepository exampleDomainRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        ExampleDomain exampleDomain = new ExampleDomain("ms91", "1234", "Chominseong", 23);

        exampleDomainRepository.save(exampleDomain);

    }

}