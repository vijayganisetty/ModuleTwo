package com.prictice.spring.ModuleTwo.DTO;

import com.prictice.spring.ModuleTwo.annotation.CustomAnnotation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 3, max = 10, message = "Number of characters should be in the range 3 to 10")
    private String name;

    @Email
    private String email;

    @CustomAnnotation
    private Integer age;

    private LocalDate dateOfJoining;

    private Boolean isActive;
}
