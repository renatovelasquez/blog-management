package dev.renvl.blog.management.controller;

import dev.renvl.blog.management.dto.AddCommentaryDTO;
import dev.renvl.blog.management.model.Commentary;
import dev.renvl.blog.management.service.CommentaryService;
import exceptions.BlogManagementException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CommentaryControllerTest {

    private final CommentaryService commentaryService = Mockito.mock(CommentaryService.class);
    private final CommentaryController commentaryController = new CommentaryController(commentaryService);

    @Test
    public void testAddCommentary_Success() {
        AddCommentaryDTO mockRequest = AddCommentaryDTO.builder()
                .commentary(Commentary.builder().commentary("Test Comment").build())
                .build();

        when(commentaryService.addCommentary(mockRequest)).thenReturn(mockRequest);

        ResponseEntity<?> response = commentaryController.addCommentary(mockRequest);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(mockRequest, response.getBody());
    }

    @Test
    public void testAddCommentary_BadRequest() {
        AddCommentaryDTO mockRequest = AddCommentaryDTO.builder().build();
        when(commentaryService.addCommentary(mockRequest)).thenThrow(new BlogManagementException("Invalid input"));

        ResponseEntity<?> response = commentaryController.addCommentary(mockRequest);
        assertEquals(400, response.getStatusCodeValue());
    }
}
