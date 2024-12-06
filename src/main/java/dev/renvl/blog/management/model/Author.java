package dev.renvl.blog.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @JsonIgnore
    private Long id;
    private String name;
    private String firstLastname;
    private String secondLastname;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
    private String residenceCountry;
    private String email;
}