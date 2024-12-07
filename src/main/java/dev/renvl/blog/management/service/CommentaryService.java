package dev.renvl.blog.management.service;

import dev.renvl.blog.management.dto.AddCommentaryDTO;

public interface CommentaryService {
    AddCommentaryDTO addCommentary(AddCommentaryDTO request);
}
