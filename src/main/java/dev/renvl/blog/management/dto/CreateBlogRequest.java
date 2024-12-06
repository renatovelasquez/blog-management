package dev.renvl.blog.management.dto;

import dev.renvl.blog.management.model.Author;
import dev.renvl.blog.management.model.Blog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CreateBlogRequest {
    private Author author;
    private Blog blog;
}
