package dev.renvl.blog.management.dto;

import dev.renvl.blog.management.model.Author;
import dev.renvl.blog.management.model.Blog;
import dev.renvl.blog.management.model.Commentary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class RetrieveBlogResponse {
    private Author author;
    private Blog blog;
    private List<Commentary> commentaries;
    private int maxScore;
    private int minScore;
    private double avgScore;
}
