package bind.iotstudycafe.member.domain;

import bind.iotstudycafe.exampleDomain.domain.ExampleDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
//@DynamicInsert
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        //seq

    private String memberId;
    private String memberPassword;
    private String memberName;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private MemberGrade memberGrade;

    @Builder
    public Member(String memberId, String memberPassword, String memberName, Integer age, MemberGrade memberGrade) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.age = age;
        this.memberGrade = memberGrade != null ? memberGrade : MemberGrade.BASIC;
    }

    public Member update(String memberPassword, String memberName, Integer age, MemberGrade memberGrade) {
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.age = age;
        this.memberGrade = memberGrade;
        return this;
    }

    // Grade를 포함하는지 확인하는 메서드 추가
    public boolean containsGrade(String memberGrade) {
        return this.memberGrade == MemberGrade.valueOf(memberGrade);
    }

}
