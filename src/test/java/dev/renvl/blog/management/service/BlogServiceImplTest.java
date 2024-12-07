package dev.renvl.blog.management.service;

import dev.renvl.blog.management.dto.CreateBlogRequest;
import dev.renvl.blog.management.dto.CreateBlogResponse;
import dev.renvl.blog.management.model.Author;
import dev.renvl.blog.management.model.Blog;
import dev.renvl.blog.management.model.BlogHistory;
import dev.renvl.blog.management.repository.AuthorRepository;
import dev.renvl.blog.management.repository.BlogHistoryRepository;
import dev.renvl.blog.management.repository.BlogRepository;
import dev.renvl.blog.management.repository.CommentaryRepository;
import exceptions.BlogManagementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BlogServiceImplTest {

    @Mock
    private BlogRepository blogRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private CommentaryRepository commentaryRepository;

    @Mock
    private BlogHistoryRepository blogHistoryRepository;

    @InjectMocks
    private BlogServiceImpl blogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBlog() {
        // Arrange
        Author author = new Author();
        author.setId(1L);

        Blog blog = new Blog();
        blog.setTitle("Sample Blog");

        CreateBlogRequest request = CreateBlogRequest.builder()
                .author(author)
                .blog(blog)
                .build();

        when(authorRepository.save(author)).thenReturn(author);
        when(blogRepository.save(any(Blog.class))).thenReturn(blog);

        // Act
        CreateBlogResponse response = blogService.createBlog(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBlogCode());
        verify(authorRepository, times(1)).save(author);
        verify(blogRepository, times(1)).save(any(Blog.class));
    }

    @Test
    void testUpdateBlog_BlogNotFound() {
        // Arrange
        BlogHistory history = new BlogHistory();
        history.setBlogCode("123");

        when(blogRepository.findBlogByBlogCode("123")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BlogManagementException.class, () -> blogService.updateBlog(history));
        verify(blogRepository, times(1)).findBlogByBlogCode("123");
    }

    @Test
    void testRetrieveBlog_BlogNotFound() {
        // Arrange
        when(blogRepository.findBlogByBlogCode("123")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BlogManagementException.class, () -> blogService.retrieveBlog("123"));
        verify(blogRepository, times(1)).findBlogByBlogCode("123");
    }
}
