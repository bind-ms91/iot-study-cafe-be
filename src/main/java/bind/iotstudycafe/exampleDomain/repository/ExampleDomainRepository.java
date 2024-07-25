package bind.iotstudycafe.exampleDomain.repository;

import bind.iotstudycafe.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainUpdate;

import java.util.List;
import java.util.Optional;

public interface ExampleDomainRepository {

    ExampleDomain save(ExampleDomain exampleDomain);

    Optional<ExampleDomain> findById(Long id);

    List<ExampleDomain> findExampleDomains(ExampleDomain exampleDomain);

    void update(Long id, ExampleDomainUpdate updateParam);

    void deleteById(Long id);
}
