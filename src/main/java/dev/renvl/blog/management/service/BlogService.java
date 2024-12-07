package dev.renvl.blog.management.service;

import dev.renvl.blog.management.dto.CreateBlogRequest;
import dev.renvl.blog.management.dto.CreateBlogResponse;
import dev.renvl.blog.management.model.Blog;
import dev.renvl.blog.management.model.BlogHistory;

import java.util.List;

public interface BlogService {
    CreateBlogResponse createBlog(CreateBlogRequest request);

    Blog updateBlog(BlogHistory request);

    Blog getBlog(String code);

    List<Blog> retrieveBlogs();
}
