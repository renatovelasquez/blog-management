package dev.renvl.blog.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
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
    private String commentary;
    private String name;
    private String residenceCountry;
    private String email;
    @Positive(message = "Max capacity must not be zero")
    private int punctuation;
    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
}