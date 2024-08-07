package bind.iotstudycafe.exampleDomain.repository.memory;

import bind.iotstudycafe.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainSave;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainSearchCond;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainUpdate;
import bind.iotstudycafe.exampleDomain.repository.ExampleDomainRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryExampleDomainRepository implements ExampleDomainRepository {

    private static final Map<Long, ExampleDomain> store = new HashMap<>(); //static
    private static long sequence = 0L; //static


    @Override
    public ExampleDomain save(ExampleDomain exampleDomain) {

        exampleDomain.setId(++sequence);

        store.put(exampleDomain.getId(), exampleDomain);

        return exampleDomain;
    }

    @Override
    public Optional<ExampleDomain> findById(Long id) {

        ExampleDomain exampleDomain = store.get(id);

        return Optional.ofNullable(exampleDomain);
    }

    @Override
    public List<ExampleDomain> findExampleDomains(ExampleDomainSearchCond exampleDomainSearchCond) {


        String name = exampleDomainSearchCond.getName();
        Integer age = exampleDomainSearchCond.getAge();

        return store.values().stream()
                .filter(example -> {
                    if (ObjectUtils.isEmpty(name)) {
                        return true;
                    }
                    return example.getName().contains(name);
                }).filter(member -> {
                    if (ObjectUtils.isEmpty(age)) {
                        return true;
                    }
                    return Objects.equals(member.getAge(), age);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id, ExampleDomainUpdate updateParam) {

        ExampleDomain findMember = findById(id).orElseThrow();

        findMember.update(updateParam.getPassword(),updateParam.getName(),updateParam.getAge());

    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    public void clearStore() {
        store.clear();
    }

}
