package bind.iotstudycafe.commons.exampleDomain.repository;

import bind.iotstudycafe.commons.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.member.domain.Member;
import bind.iotstudycafe.member.dto.MemberSearchCond;
import bind.iotstudycafe.member.dto.MemberUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ExampleDomainRepository {

    ExampleDomain save(ExampleDomain exampleDomain);

    Optional<ExampleDomain> findById(Long id);

    List<ExampleDomain> findExampleDomains(ExampleDomain exampleDomain);
}
