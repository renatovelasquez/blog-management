package dev.renvl.blog.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String blogCode;
    private String title;
    private String topic;
    private String content;
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;
    private boolean commentariesEnabled;
    @JsonIgnore
    private LocalDateTime date;
}