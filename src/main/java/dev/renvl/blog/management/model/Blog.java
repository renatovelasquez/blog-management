package dev.renvl.blog.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String blogCode;
    private String title;
    private String topic;
    private String content;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Periodicity must not be null")
    private Periodicity periodicity;
    private boolean commentariesEnabled;
    @JsonIgnore
    @OneToOne
    private Author author;
    @JsonIgnore
    @OneToMany(mappedBy = "blog")
    private Set<Commentary> commentaries;
}