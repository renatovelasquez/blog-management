package dev.renvl.blog.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commentary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @NotBlank(message = "commentary must not be blank")
    private String commentary;
    private String name;
    private String residenceCountry;
    @Email
    @NotBlank(message = "email must not be blank")
    private String email;
    @Max(message = "Max punctuation must not be greater than 10", value = 10)
    @Min(message = "Min punctuation must not be lower than 0", value = 0)
    private int punctuation;
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Blog blog;
}