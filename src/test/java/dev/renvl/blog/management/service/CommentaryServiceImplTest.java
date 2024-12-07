package dev.renvl.blog.management.service;

import dev.renvl.blog.management.dto.AddCommentaryDTO;
import dev.renvl.blog.management.model.Blog;
import dev.renvl.blog.management.model.Commentary;
import dev.renvl.blog.management.repository.BlogRepository;
import dev.renvl.blog.management.repository.CommentaryRepository;
import exceptions.BlogManagementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentaryServiceImplTest {

    @Mock
    private CommentaryRepository commentaryRepository;

    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private CommentaryServiceImpl commentaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCommentary_Success() {
        // Arrange
        Blog blog = new Blog();
        blog.setBlogCode("123");
        blog.setCommentariesEnabled(true);

        Commentary commentary = new Commentary();
        commentary.setCommentary("Sample Commentary");

        AddCommentaryDTO request = AddCommentaryDTO.builder()
                .blogCode(blog.getBlogCode())
                .commentary(commentary)
                .build();

        when(blogRepository.findBlogByBlogCode("123")).thenReturn(Optional.of(blog));
        when(commentaryRepository.save(any(Commentary.class))).thenReturn(commentary);

        // Act
        AddCommentaryDTO response = commentaryService.addCommentary(request);

        // Assert
        assertNotNull(response);
        assertEquals("123", response.getBlogCode());
        assertEquals(commentary, response.getCommentary());
        verify(blogRepository, times(1)).findBlogByBlogCode("123");
        verify(commentaryRepository, times(1)).save(commentary);
    }

    @Test
    void testAddCommentary_BlogNotFound() {
        // Arrange
        AddCommentaryDTO request = AddCommentaryDTO.builder()
                .blogCode("123")
                .build();

        when(blogRepository.findBlogByBlogCode("123")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BlogManagementException.class, () -> commentaryService.addCommentary(request));
        verify(blogRepository, times(1)).findBlogByBlogCode("123");
        verify(commentaryRepository, never()).save(any(Commentary.class));
    }

    @Test
    void testAddCommentary_CommentariesDisabled() {
        // Arrange
        Blog blog = new Blog();
        blog.setBlogCode("123");
        blog.setCommentariesEnabled(false);

        AddCommentaryDTO request = AddCommentaryDTO.builder()
                .blogCode(blog.getBlogCode())
                .build();

        when(blogRepository.findBlogByBlogCode("123")).thenReturn(Optional.of(blog));

        // Act & Assert
        assertThrows(BlogManagementException.class, () -> commentaryService.addCommentary(request));
        verify(blogRepository, times(1)).findBlogByBlogCode("123");
        verify(commentaryRepository, never()).save(any(Commentary.class));
    }
}
