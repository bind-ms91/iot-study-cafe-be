package bind.iotstudycafe.member.controller;

import bind.iotstudycafe.commons.login.domain.LoginDto;
import bind.iotstudycafe.exampleDomain.dto.ExampleDomainSearchCond;
import bind.iotstudycafe.member.domain.Member;
import bind.iotstudycafe.member.dto.MemberSaveDto;
import bind.iotstudycafe.member.dto.MemberSearchCond;
import bind.iotstudycafe.member.dto.MemberUpdateDto;
import bind.iotstudycafe.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "회원", description = "회원 컨트롤러")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "저장", description = "저장",
            responses = {@ApiResponse(responseCode = "200", description = "저장 성공", content = @Content(schema = @Schema(implementation = Member.class)))}
    )
    @PostMapping("/save")
    public Member save(@Validated @RequestBody MemberSaveDto memberSaveDto) {

        return memberService.save(memberSaveDto);
    }

    @Operation(summary = "id로 조회", description = "id로 조회",
            parameters = {@Parameter(name = "id", description = "id")},
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = Member.class)))}
    )
    @GetMapping("/{id}")
    public Optional<Member> findByIdToEntity(@PathVariable Long id) {

        return memberService.findById(id);
    }

    @Operation(summary = "member id 로 조회", description = "member id 로 조회",
            parameters = {@Parameter(name = "memberId", description = "memberId")},
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = Member.class)))}
    )
    @GetMapping()
    public Optional<Member> findByMemberId(@RequestParam String memberId) {

        return memberService.findByMemberId(memberId);
    }

    @Operation(summary = "리스트 조회", description = "리스트 조회",
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = Member.class)))}
    )
    @GetMapping("/list")
    public List<Member> findExampleDomains(@Validated @ParameterObject @ModelAttribute MemberSearchCond memberSearchCond) {

        return memberService.findAll(memberSearchCond);
    }

    @Operation(summary = "수정", description = "수정")
    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id,
                       @Validated @RequestBody MemberUpdateDto updateParam) {

        memberService.update(id, updateParam);
    }

    @Operation(summary = "삭제", description = "삭제")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {

        log.info("ExampleDomainController.update post id: {}", id);

        memberService.deleteById(id);
    }

}
