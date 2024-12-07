package dev.renvl.blog.management.service;

import dev.renvl.blog.management.model.Author;
import dev.renvl.blog.management.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    public AuthorServiceImplTest() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testCreateAuthor() {
        // Arrange
        Author mockAuthor = new Author();
        mockAuthor.setId(1L);
        mockAuthor.setName("John Doe");
        mockAuthor.setEmail("john.doe@example.com");

        when(authorRepository.save(mockAuthor)).thenReturn(mockAuthor);

        // Act
        Author result = authorService.createAuthor(mockAuthor);

        // Assert
        assertEquals(mockAuthor, result);
        verify(authorRepository, times(1)).save(mockAuthor); // Ensure save method was called once
    }
}
