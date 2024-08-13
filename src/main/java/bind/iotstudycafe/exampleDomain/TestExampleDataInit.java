package bind.iotstudycafe.exampleDomain;

import bind.iotstudycafe.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainSave;
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
//    @PostConstruct
//    public void init() {
//
//        ExampleDomainSave ExampleDomainSave1 = new ExampleDomainSave("ms91", "1234", "Chominseong", 21);
//        ExampleDomainSave ExampleDomainSave2 = new ExampleDomainSave("ms92", "1234", "Chominseong", 22);
//
//
//        exampleDomainRepository.save(ExampleDomainSave1);
//        exampleDomainRepository.save(ExampleDomainSave2);
//    }

}