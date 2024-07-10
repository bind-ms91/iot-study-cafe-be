package bind.iotstudycafe.commons.exampleDomain.service;

import bind.iotstudycafe.commons.exampleDomain.domain.ExampleDomain;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ExampleDomainService {

    ExampleDomain save(ExampleDomain exampleDomain);

    Optional<ExampleDomain> findById(Long id);

    List<ExampleDomain> findExampleDomains(ExampleDomain exampleDomain);

//    List<ExampleDomain> findAll(ExampleDomain cond);
//
//     Mono<Void> update(Long id, ExampleDomainUpdateDto updateParam);

}
