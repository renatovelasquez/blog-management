package dev.renvl.blog.management.controller;

import dev.renvl.blog.management.model.Author;
import dev.renvl.blog.management.service.AuthorService;
import exceptions.BlogManagementException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthorControllerTest {

    private final AuthorService authorService = Mockito.mock(AuthorService.class);
    private final AuthorController authorController = new AuthorController(authorService);

    @Test
    public void testCreateAuthor_Success() {
        Author mockAuthor = new Author();
        mockAuthor.setName("John Doe");

        when(authorService.createAuthor(mockAuthor)).thenReturn(mockAuthor);

        ResponseEntity<?> response = authorController.createAuthor(mockAuthor);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(mockAuthor, response.getBody());
    }

    @Test
    public void testCreateAuthor_BadRequest() {
        Author mockAuthor = new Author();
        when(authorService.createAuthor(mockAuthor)).thenThrow(new BlogManagementException("Invalid input"));

        ResponseEntity<?> response = authorController.createAuthor(mockAuthor);
        assertEquals(400, response.getStatusCodeValue());
    }
}
