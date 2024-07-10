package bind.iotstudycafe.commons.exampleDomain.repository.memory;

import bind.iotstudycafe.commons.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.commons.exampleDomain.repository.ExampleDomainRepository;
import bind.iotstudycafe.member.domain.Member;
import org.springframework.http.ResponseEntity;
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
    public List<ExampleDomain> findExampleDomains(ExampleDomain exampleDomain) {


        String name = exampleDomain.getName();
        Integer age = exampleDomain.getAge();

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

    //    @Override
//    public void update(Long id, MemberUpdateDto updateParam) {
//
//        Member findMember = findById(id).orElseThrow();
//        findMember.setMemberGrade(updateParam.getMemberGrade());
//        findMember.setMemberPassword(updateParam.getMemberPassword());
//        findMember.setMemberName(updateParam.getMemberName());
//        findMember.setAge(updateParam.getAge());
//
//    }
//
//    @Override
//    public void delete(Long id) {
//        store.remove(id);
//    }
//
//    public void clearStore() {
//        store.clear();
//    }

}
