package bind.iotstudycafe.commons.exampleDomain.service;

import bind.iotstudycafe.commons.exampleDomain.domain.ExampleDomain;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ExampleDomainServiceTest {

    @Autowired
    private ExampleDomainService exampleDomainService;

    @Test
    void findByIdToEntity() {

        ExampleDomain exampleDomain = new ExampleDomain("ms91", "1111", "Chominseong", 23);

        exampleDomainService.save(exampleDomain);

        ExampleDomain findExampleDomain = exampleDomainService.findById(exampleDomain.getId()).get();

        log.info("exampleDomain: {}", exampleDomain);
        log.info("findExampleDomain: {}", findExampleDomain);

        Assertions.assertThat(exampleDomain).isEqualTo(findExampleDomain);

    }
}