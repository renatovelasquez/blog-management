package dev.renvl.blog.management.service;

import dev.renvl.blog.management.dto.CreateBlogRequest;
import dev.renvl.blog.management.dto.CreateBlogResponse;
import dev.renvl.blog.management.model.Author;
import dev.renvl.blog.management.model.Blog;
import dev.renvl.blog.management.model.BlogHistory;
import dev.renvl.blog.management.repository.AuthorRepository;
import dev.renvl.blog.management.repository.BlogHistoryRepository;
import dev.renvl.blog.management.repository.BlogRepository;
import exceptions.BlogManagementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BlogServiceImpl implements BlogService {


    private final BlogRepository blogRepository;
    private final AuthorRepository authorRepository;
    private final BlogHistoryRepository blogHistoryRepository;

    public BlogServiceImpl(BlogRepository blogRepository, AuthorRepository authorRepository, BlogHistoryRepository blogHistoryRepository) {
        this.blogRepository = blogRepository;
        this.authorRepository = authorRepository;
        this.blogHistoryRepository = blogHistoryRepository;
    }

    /**
     * @param request
     * @return
     */
    @Override
    @Transactional
    public CreateBlogResponse createBlog(CreateBlogRequest request) {

        Author author = authorRepository.save(request.getAuthor());

        Blog blog = request.getBlog();
        blog.setAuthor(author);

        String blogCode = UUID.randomUUID().toString().split("-")[0];
        blog.setBlogCode(blogCode);

        blog = blogRepository.save(blog);

        return CreateBlogResponse.builder().blogCode(blog.getBlogCode()).build();
    }

    /**
     * @param request
     * @return
     */
    @Override
    @Transactional
    public Blog updateBlog(BlogHistory request) {

        Blog blog = blogRepository
                .findBlogByBlogCode(request.getBlogCode())
                .orElseThrow(() -> new BlogManagementException("Blog not found."));

        BlogHistory blogHistory = BlogHistory.builder()
                .blogCode(blog.getBlogCode())
                .topic(blog.getTopic())
                .title(blog.getTitle())
                .content(blog.getContent())
                .periodicity(blog.getPeriodicity())
                .commentariesEnabled(blog.isCommentariesEnabled())
                .date(LocalDate.now())
                .build();
        blogHistoryRepository.save(blogHistory);

        blog.setTitle(request.getTitle());
        blog.setTopic(request.getTitle());
        blog.setContent(request.getTitle());
        blog.setPeriodicity(request.getPeriodicity());
        blog.setCommentariesEnabled(request.isCommentariesEnabled());

        return blogRepository.save(blog);
    }

    /**
     * @param blogCode
     * @return
     */
    @Override
    public Blog getBlog(String blogCode) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Blog> retrieveBlogs() {
        return List.of();
    }
}
