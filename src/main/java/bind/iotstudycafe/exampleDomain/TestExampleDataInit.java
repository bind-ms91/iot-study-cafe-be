package bind.iotstudycafe.exampleDomain;

import bind.iotstudycafe.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.exampleDomain.repository.ExampleDomainRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TestExampleDataInit {

    private final ExampleDomainRepository exampleDomainRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        ExampleDomain exampleDomain1 = new ExampleDomain("ms91", "1234", "Chominseong", 21);
        ExampleDomain exampleDomain2 = new ExampleDomain("ms92", "1234", "Chominseong", 22);


        exampleDomainRepository.save(exampleDomain1);
        exampleDomainRepository.save(exampleDomain2);
    }

}