package bind.iotstudycafe.commons.exampleDomain.controller;


import bind.iotstudycafe.commons.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.commons.exampleDomain.dto.ExampleDomainSave;
import bind.iotstudycafe.commons.exampleDomain.dto.ExampleDomainSearchCond;
import bind.iotstudycafe.commons.exampleDomain.dto.ExampleDomainUpdate;
import bind.iotstudycafe.commons.exampleDomain.service.ExampleDomainService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/example")
public class ExampleDomainController {

    @Autowired
    private ExampleDomainService exampleDomainService;

    @PostMapping("/save")
    public ExampleDomain save(@Validated @RequestBody ExampleDomainSave exampleDomainSave) {

        log.info("ExampleDomainController.save post exampleDomainSave: {}", exampleDomainSave);

        ExampleDomain exampleDomain = new ExampleDomain();
        exampleDomain.setLoginId(exampleDomainSave.getLoginId());
        exampleDomain.setPassword(exampleDomainSave.getPassword());
        exampleDomain.setName(exampleDomainSave.getName());
        exampleDomain.setAge(exampleDomainSave.getAge());

        return exampleDomainService.save(exampleDomain);
    }

    @GetMapping("/{id}")
    public Optional<ExampleDomain> findByIdToEntity(@PathVariable Long id) {

        log.info("ExampleDomainController.findByIdToEntity get id: {}", id);

        return exampleDomainService.findById(id);
    }


    @GetMapping("/list")
    public List<ExampleDomain> findExampleDomains(
            @RequestParam("name") @NotBlank String name,
            @RequestParam(name = "age", required = false)  Integer age) {

        ExampleDomain exampleDomain = new ExampleDomain();
        exampleDomain.setName(name);
        exampleDomain.setAge(age);

        return exampleDomainService.findExampleDomains(exampleDomain);
    }

//    @GetMapping("/list")
//    public List<ExampleDomain> findExampleDomains(@Validated @ModelAttribute ExampleDomainSearchCond exampleDomainSearchCond) {
//
//        log.info("ExampleDomainController.findExampleDomains search cond: {}", exampleDomainSearchCond);
//
//        ExampleDomain exampleDomain = new ExampleDomain();
//        exampleDomain.setName(exampleDomainSearchCond.getName());
//        exampleDomain.setAge(exampleDomainSearchCond.getAge());
//
//        return exampleDomainService.findExampleDomains(exampleDomain);
//    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id,
                       @Validated @RequestBody ExampleDomainUpdate updateParam) {

        log.info("ExampleDomainController.update post id: {}", id);
        log.info("ExampleDomainController.update updateParam: {}", updateParam);

        exampleDomainService.update(id, updateParam);
    }

    @DeleteMapping("/delete/{id}")
    public void update(@PathVariable Long id) {

        log.info("ExampleDomainController.update post id: {}", id);

        exampleDomainService.deleteById(id);
    }
}
