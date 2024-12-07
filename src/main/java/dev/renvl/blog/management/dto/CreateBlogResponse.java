package dev.renvl.blog.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CreateBlogResponse {
    private String blogCode;
}
