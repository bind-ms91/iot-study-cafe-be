package bind.iotstudycafe.exampleDomain.controller;


import bind.iotstudycafe.exampleDomain.domain.ExampleDomain;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainSave;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainSearchCond;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainUpdate;
import bind.iotstudycafe.exampleDomain.service.ExampleDomainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation(summary = "저장", description = "저장",
            responses = {@ApiResponse(responseCode = "200", description = "저장 성공", content = @Content(schema = @Schema(implementation = ExampleDomain.class)))}
    )
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

    @Operation(summary = "id로 조회", description = "id로 조회",
            parameters = {@Parameter(name = "id", description = "id")},
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ExampleDomain.class)))}
    )
    @GetMapping("/{id}")
    public Optional<ExampleDomain> findByIdToEntity(@PathVariable Long id) {

        log.info("ExampleDomainController.findByIdToEntity get id: {}", id);

        return exampleDomainService.findById(id);
    }


//    @Operation(summary = "리스트 조회", description = "리스트 조회",
//            parameters = {
//                    @Parameter(name = "name", description = "이름", required = true),
//                    @Parameter(name = "age", description = "나이")
//            },
//            responses = {@ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ExampleDomain.class)))}
//    )
//    @GetMapping("/list")
//    public List<ExampleDomain> findExampleDomains(
//            @RequestParam("name") @NotBlank String name,
//            @RequestParam(name = "age", required = false)  Integer age) {
//
//        ExampleDomain exampleDomain = new ExampleDomain();
//        exampleDomain.setName(name);
//        exampleDomain.setAge(age);
//
//        return exampleDomainService.findExampleDomains(exampleDomain);
//    }

    @Operation(summary = "리스트 조회", description = "리스트 조회",
//            parameters = {
//                    @Parameter(name = "name", description = "이름", required = true),
//                    @Parameter(name = "age", description = "나이")
//            },
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ExampleDomain.class)))}
    )
    @GetMapping("/list")
    public List<ExampleDomain> findExampleDomains(@Validated @ParameterObject @ModelAttribute ExampleDomainSearchCond exampleDomainSearchCond) {

        log.info("ExampleDomainController.findExampleDomains search cond: {}", exampleDomainSearchCond);

        return exampleDomainService.findExampleDomains(exampleDomainSearchCond);
    }

    @Operation(summary = "수정", description = "수정")
    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id,
                       @Validated @RequestBody ExampleDomainUpdate updateParam) {

        log.info("ExampleDomainController.update post id: {}", id);
        log.info("ExampleDomainController.update updateParam: {}", updateParam);

        exampleDomainService.update(id, updateParam);
    }

    @Operation(summary = "삭제", description = "삭제")
    @DeleteMapping("/delete/{id}")
    public void update(@PathVariable Long id) {

        log.info("ExampleDomainController.update post id: {}", id);

        exampleDomainService.deleteById(id);
    }
}
