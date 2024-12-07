package dev.renvl.blog.management.controller;

import dev.renvl.blog.management.dto.RetrieveBlogResponse;
import dev.renvl.blog.management.service.BlogService;
import exceptions.BlogManagementException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BlogControllerTest {

    private final BlogService blogService = Mockito.mock(BlogService.class);
    private final BlogController blogController = new BlogController(blogService);

    @Test
    public void testGetBlogById_Success() {
        RetrieveBlogResponse mockBlog = RetrieveBlogResponse.builder().blogCode("XXXXXX").build();

        when(blogService.retrieveBlog("XXXXXX")).thenReturn(mockBlog);

        ResponseEntity<?> response = blogController.retrieveBlog("XXXXXX");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockBlog, response.getBody());
    }

    @Test
    public void testGetBlogById_NotFound() {
        when(blogService.retrieveBlog("XXXXXX")).thenThrow(new BlogManagementException("Blog not found"));

        ResponseEntity<?> response = blogController.retrieveBlog("XXXXXX");
        assertEquals(404, response.getStatusCodeValue());
    }
}
