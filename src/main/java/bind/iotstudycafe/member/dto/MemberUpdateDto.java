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
public class MemberUpdateDto {

    private String memberPassword;
    private String memberName;
    private int age;

    @EnumValue(enumClass = MemberGrade.class, customMessage = EnumValidatorMessage.MEMBER_GRADE, ignoreCase = true)
    private String memberGrade;

}
