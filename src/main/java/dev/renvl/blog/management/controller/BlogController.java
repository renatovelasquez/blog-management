package dev.renvl.blog.management.controller;

import dev.renvl.blog.management.dto.MessageResponseDto;
import dev.renvl.blog.management.model.Blog;
import dev.renvl.blog.management.service.BlogService;
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
import java.util.List;
import java.util.Set;

@Tag(name = "Blog", description = "Blog management APIs")
@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @Operation(
            summary = "Create Blog",
            description = "Create Blog object by specifying its values. The response is Blog object")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Blog.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBlog(@Valid @RequestBody Blog request) {
        Set<String> messages = new HashSet<>();
        HttpStatus httpStatus = HttpStatus.CREATED;
        try {
            Blog blog = blogService.createBlog(request);
            return ResponseEntity.status(httpStatus).body(blog);
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

    @Operation(
            summary = "Update Blog",
            description = "Update Blog object by specifying its values. The response is Blog object")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Blog.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateBlog(@Valid @RequestBody Blog updateBlogRequest) {
        Set<String> messages = new HashSet<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Blog blog = blogService.updateBlog(updateBlogRequest);
            return ResponseEntity.status(httpStatus).body(blog);
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

    @Operation(
            summary = "Retrieve Blog",
            description = "Retrieve Blog object by specifying its values. The response is Blog object")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Blog.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> retrieveBlog(@PathVariable("code") String code) {
        Set<String> messages = new HashSet<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Blog blog = blogService.getBlog(code);
            return ResponseEntity.status(httpStatus).body(blog);
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


    @Operation(
            summary = "Retrieve Blogs",
            description = "Retrieve Blogs objects by specifying its values. The response is a list of Blog objects")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Blog.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> retrieveBlogs() {
        Set<String> messages = new HashSet<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            List<Blog> blogs = blogService.retrieveBlogs();
            return ResponseEntity.status(httpStatus).body(blogs);
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
