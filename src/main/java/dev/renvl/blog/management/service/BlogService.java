package dev.renvl.blog.management.service;

import dev.renvl.blog.management.model.Blog;

import java.util.List;

public interface BlogService {
    Blog createBlog(Blog request);

    Blog updateBlog(Blog request);

    Blog getBlog(String registrationCode);

    List<Blog> retrieveBlogs();
}
