package dev.renvl.blog.management.dto;

import dev.renvl.blog.management.model.Commentary;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AddCommentaryDTO {
    @NotBlank(message = "Blog code must not be blank")
    private String blogCode;
    private Commentary commentary;
}
