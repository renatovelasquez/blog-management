package dev.renvl.blog.management.service;

import dev.renvl.blog.management.dto.AddCommentaryDTO;
import dev.renvl.blog.management.model.Blog;
import dev.renvl.blog.management.model.Commentary;
import dev.renvl.blog.management.repository.BlogRepository;
import dev.renvl.blog.management.repository.CommentaryRepository;
import exceptions.BlogManagementException;
import org.springframework.stereotype.Service;

@Service
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryRepository commentaryRepository;
    private final BlogRepository blogRepository;

    public CommentaryServiceImpl(CommentaryRepository commentaryRepository, BlogRepository blogRepository) {
        this.commentaryRepository = commentaryRepository;
        this.blogRepository = blogRepository;
    }

    /**
     * @param request
     * @return
     */
    @Override
    public AddCommentaryDTO addCommentary(AddCommentaryDTO request) {
        Blog blog = blogRepository
                .findBlogByBlogCode(request.getBlogCode())
                .orElseThrow(() -> new BlogManagementException("Blog not found."));

        if (!blog.isCommentariesEnabled())
            throw new BlogManagementException("Blog does not have commentaries enabled.");

        Commentary commentary = request.getCommentary();
        commentary.setBlog(blog);

        commentary = commentaryRepository.save(commentary);

        return AddCommentaryDTO.builder().blogCode(blog.getBlogCode()).commentary(commentary).build();
    }
}
