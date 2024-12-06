package dev.renvl.blog.management.controller;

import dev.renvl.blog.management.dto.MessageResponseDto;
import dev.renvl.blog.management.model.Author;
import dev.renvl.blog.management.service.AuthorService;
import exceptions.BlogManagementException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Tag(name = "Author", description = "Author management APIs")
@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(
            summary = "Create Author",
            description = "Create Author object by specifying its values. The response is Author object")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Author.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createAuthor(@Valid @RequestBody Author request) {
        Set<String> messages = new HashSet<>();
        HttpStatus httpStatus = HttpStatus.CREATED;
        try {
            Author author = authorService.createAuthor(request);
            return ResponseEntity.status(httpStatus).body(author);
        } catch (BlogManagementException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            messages.add(e.getMessage());
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            messages.add(e.getMessage());
        }
        MessageResponseDto messageResponseDto = MessageResponseDto.builder()
                .httpStatus(httpStatus)
                .timestamp(System.currentTimeMillis())
                .messages(messages).build();
        return ResponseEntity.status(messageResponseDto.getHttpStatus()).body(messageResponseDto);
    }
}
