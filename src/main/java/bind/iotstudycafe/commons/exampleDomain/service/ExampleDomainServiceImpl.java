package bind.iotstudycafe.commons.exampleDomain.service;

import bind.iotstudycafe.commons.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.commons.exampleDomain.repository.ExampleDomainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExampleDomainServiceImpl implements ExampleDomainService {

    @Autowired
    private final ExampleDomainRepository exampleDomainRepository;

    @Override
    public ExampleDomain save(ExampleDomain exampleDomain) {

        return exampleDomainRepository.save(exampleDomain);
    }

    @Override
    public Optional<ExampleDomain> findById(Long id) {

        log.info("ExampleDomainService findById id: {}", id);

        return exampleDomainRepository.findById(id);
    }

    @Override
    public List<ExampleDomain> findExampleDomains(ExampleDomain exampleDomain) {
        return exampleDomainRepository.findExampleDomains(exampleDomain);
    }
}
