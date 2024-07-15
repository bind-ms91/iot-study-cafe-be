package bind.iotstudycafe.commons.exampleDomain.controller;


import bind.iotstudycafe.commons.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.commons.exampleDomain.dto.ExampleDomainSearchCond;
import bind.iotstudycafe.commons.exampleDomain.service.ExampleDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/example")
public class ExampleDomainController {

    @Autowired
    private ExampleDomainService exampleDomainService;

    @PostMapping("/save")
    public ExampleDomain save(@RequestBody ExampleDomain exampleDomain) {

        log.info("ExampleDomainController.save post exampleDomain: {}", exampleDomain);

        return exampleDomainService.save(exampleDomain);
    }

    @GetMapping("/{id}")
    public Optional<ExampleDomain> findByIdToEntity(@PathVariable Long id) {

        log.info("ExampleDomainController.findByIdToEntity get id: {}", id);

        return exampleDomainService.findById(id);
    }

//    @GetMapping("/list")
//    public List<ExampleDomain> findExampleDomains(
//            @RequestParam("name") String name,
//            @RequestParam("age") Integer age) {
//
//        ExampleDomain exampleDomain = new ExampleDomain();
//        exampleDomain.setName(name);
//        exampleDomain.setAge(age);
//
//        return exampleDomainService.findExampleDomains(exampleDomain);
//    }

    @GetMapping("/list")
    public List<ExampleDomain> findExampleDomains(@Validated @ModelAttribute ExampleDomainSearchCond exampleDomainSearchCond) {

        log.info("ExampleDomainController.findExampleDomains search cond: {}", exampleDomainSearchCond);

        ExampleDomain exampleDomain = new ExampleDomain();
        exampleDomain.setName(exampleDomainSearchCond.getName());
        exampleDomain.setAge(exampleDomainSearchCond.getAge());

        return exampleDomainService.findExampleDomains(exampleDomain);
    }

}
