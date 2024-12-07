package dev.renvl.blog.management.service;

import dev.renvl.blog.management.dto.CreateBlogRequest;
import dev.renvl.blog.management.dto.CreateBlogResponse;
import dev.renvl.blog.management.dto.RetrieveBlogResponse;
import dev.renvl.blog.management.model.Author;
import dev.renvl.blog.management.model.Blog;
import dev.renvl.blog.management.model.BlogHistory;
import dev.renvl.blog.management.model.Commentary;
import dev.renvl.blog.management.repository.AuthorRepository;
import dev.renvl.blog.management.repository.BlogHistoryRepository;
import dev.renvl.blog.management.repository.BlogRepository;
import dev.renvl.blog.management.repository.CommentaryRepository;
import exceptions.BlogManagementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlogServiceImpl implements BlogService {


    private final BlogRepository blogRepository;
    private final AuthorRepository authorRepository;
    private final CommentaryRepository commentaryRepository;
    private final BlogHistoryRepository blogHistoryRepository;

    public BlogServiceImpl(BlogRepository blogRepository, AuthorRepository authorRepository, CommentaryRepository commentaryRepository, BlogHistoryRepository blogHistoryRepository) {
        this.blogRepository = blogRepository;
        this.authorRepository = authorRepository;
        this.commentaryRepository = commentaryRepository;
        this.blogHistoryRepository = blogHistoryRepository;
    }

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
                .date(LocalDateTime.now())
                .build();
        blogHistoryRepository.save(blogHistory);

        blog.setTitle(request.getTitle());
        blog.setTopic(request.getTitle());
        blog.setContent(request.getTitle());
        blog.setPeriodicity(request.getPeriodicity());
        blog.setCommentariesEnabled(request.isCommentariesEnabled());

        return blogRepository.save(blog);
    }

    @Override
    public RetrieveBlogResponse retrieveBlog(String blogCode) {
        Blog blog = blogRepository
                .findBlogByBlogCode(blogCode)
                .orElseThrow(() -> new BlogManagementException("Blog not found."));

        Author author = authorRepository.findById(blog.getAuthor().getId())
                .orElseThrow(() -> new BlogManagementException("Author not found."));

        List<Commentary> commentaries = commentaryRepository.getAllByBlog_BlogCode(blogCode);

        int max = 0;
        int min = 0;
        double average = 0;

        if (!commentaries.isEmpty()) {
            max = commentaries.stream().mapToInt(Commentary::getScore).max().orElseThrow();
            min = commentaries.stream().mapToInt(Commentary::getScore).min().orElseThrow();
            average = commentaries.stream().mapToInt(Commentary::getScore).average().orElseThrow();
        }

        return RetrieveBlogResponse.builder().blogCode(blog.getBlogCode())
                .author(author).blog(blog)
                .commentaries(commentaries)
                .maxScore(max)
                .minScore(min)
                .avgScore(average)
                .build();
    }

    @Override
    public List<RetrieveBlogResponse> retrieveBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        List<RetrieveBlogResponse> responseList = new ArrayList<>();
        for (Blog blog : blogs) {
            RetrieveBlogResponse response = retrieveBlog(blog.getBlogCode());
            responseList.add(response);
        }
        return responseList;
    }
}
