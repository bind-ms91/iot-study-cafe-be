package bind.iotstudycafe.commons.exampleDomain.repository;

import bind.iotstudycafe.commons.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.commons.exampleDomain.dto.ExampleDomainUpdate;

import java.util.List;
import java.util.Optional;

public interface ExampleDomainRepository {

    ExampleDomain save(ExampleDomain exampleDomain);

    Optional<ExampleDomain> findById(Long id);

    List<ExampleDomain> findExampleDomains(ExampleDomain exampleDomain);

    void update(Long id, ExampleDomainUpdate updateParam);

    void deleteById(Long id);
}
