package bind.iotstudycafe.exampleDomain.service;

import bind.iotstudycafe.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainSearchCond;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainUpdate;

import java.util.List;
import java.util.Optional;

public interface ExampleDomainService {

    ExampleDomain save(ExampleDomain exampleDomain);

    Optional<ExampleDomain> findById(Long id);

    List<ExampleDomain> findExampleDomains(ExampleDomainSearchCond exampleDomainSearchCond);

    void update(Long id, ExampleDomainUpdate updateParam);

    void deleteById(Long id);

}
