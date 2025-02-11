package com.example.scheduleprojectver2;

import com.example.scheduleprojectver2.dto.schedules.CreateScheduleRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationTest {

    @Test
    void validation() {
        CreateScheduleRequestDto requestDto = new CreateScheduleRequestDto(
            "", "제목보다 큽니다".repeat(30), "정상"
        );

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<CreateScheduleRequestDto>> violations = validator.validate(requestDto);

        assertThat(violations).isNotEmpty();

        assertThat(violations.size()).isEqualTo(3);

        for(ConstraintViolation<CreateScheduleRequestDto> violation : violations) {
            // 아래의 결과에 Message가 있으면 Validation에 걸린것.
            // Default Message가 있기 때문에 출력됨
            // Message를 수정하고싶다면 Annotation 속성값(message="입력")으로 설정할 수 있다.
            // 결과가 비어있으면 Validation에 걸리지 않은것.
            System.out.println("violation = " + violation.getMessage());
        }
    }
}
