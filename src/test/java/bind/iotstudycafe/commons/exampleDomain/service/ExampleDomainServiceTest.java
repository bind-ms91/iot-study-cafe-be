package bind.iotstudycafe.commons.exampleDomain.service;

import bind.iotstudycafe.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainUpdate;
import bind.iotstudycafe.exampleDomain.repository.ExampleDomainRepository;
import bind.iotstudycafe.exampleDomain.repository.memory.MemoryExampleDomainRepository;
import bind.iotstudycafe.exampleDomain.service.ExampleDomainService;
import bind.iotstudycafe.exampleDomain.service.ExampleDomainServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
@Slf4j
class ExampleDomainServiceTest {

    ExampleDomainService exampleDomainService;

    @BeforeEach
    public void beforeEach() {
        ExampleDomainRepository exampleDomainRepository = new MemoryExampleDomainRepository();
        exampleDomainService = new ExampleDomainServiceImpl(exampleDomainRepository);
    }


    @Test
    void findByIdToEntity() {

        //given
        ExampleDomain exampleDomain = new ExampleDomain("ms90", "1111", "Chominseong", 20);
        exampleDomainService.save(exampleDomain);

        //when
        ExampleDomain findExampleDomain = exampleDomainService.findById(exampleDomain.getId()).get();

        log.info("exampleDomain: {}", exampleDomain);
        log.info("findExampleDomain: {}", findExampleDomain);

        //then
        Assertions.assertThat(exampleDomain).isEqualTo(findExampleDomain);

    }

    @Test
    void update() {

        //given
        ExampleDomain exampleDomain = new ExampleDomain("ms90", "1111", "Chominseong", 20);

        exampleDomainService.save(exampleDomain);

        Long id = exampleDomain.getId();


        //when
        ExampleDomainUpdate updateParam = new ExampleDomainUpdate("2222", "minseong", 22);
        exampleDomainService.update(id, updateParam);

        log.info("exampleDomain: {}", exampleDomain);

        log.info("updateParam: {}", updateParam);

        //then

        Assertions.assertThat(exampleDomain.getPassword()).isEqualTo(updateParam.getPassword());
        Assertions.assertThat(exampleDomain.getName()).isEqualTo(updateParam.getName());
        Assertions.assertThat(exampleDomain.getAge()).isEqualTo(updateParam.getAge());

    }

    @Test
    void delete() {

        //given
        ExampleDomain exampleDomain = new ExampleDomain("ms90", "1111", "Chominseong", 20);

        exampleDomainService.save(exampleDomain);

        Long id = exampleDomain.getId();

        log.info("exampleDomain: {}", exampleDomain);

        //when
        exampleDomainService.deleteById(id);

        //then
        Assertions.assertThatThrownBy(()-> exampleDomainService.findById(id).get())
                .isInstanceOf(NoSuchElementException.class);

    }


}