package bind.iotstudycafe.member.dto;

import bind.iotstudycafe.commons.enumvalidator.EnumValidatorMessage;
import bind.iotstudycafe.commons.enumvalidator.EnumValue;
import bind.iotstudycafe.member.domain.MemberGrade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchCond {

    private String memberId;
    private String memberName;
    private Integer maxAge;
    private Integer minAge;

    @EnumValue(enumClass = MemberGrade.class, ignoreCase = true)
    private String memberGrade;

}
