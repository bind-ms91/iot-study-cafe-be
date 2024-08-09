package bind.iotstudycafe.exampleDomain.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "예제 객체")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExampleDomain {

    @Setter
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "로그인 ID")
    private String loginId;

    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "나이")
    private Integer age;

    @Builder
    public ExampleDomain(String loginId, String password, String name, Integer age) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public ExampleDomain update(String password, String name, Integer age) {
        this.password = password;
        this.name = name;
        this.age = age;
        return this;
    }

}
