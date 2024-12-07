package dev.renvl.blog.management.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String name;
    private String firstLastname;
    private String secondLastname;
    @Temporal(TemporalType.DATE)
    @Schema(type = "string", pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String residenceCountry;
    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;
}