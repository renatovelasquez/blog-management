package dev.renvl.blog.management.service;

import dev.renvl.blog.management.model.Blog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    /**
     * @param request 
     * @return
     */
    @Override
    public Blog createBlog(Blog request) {
        return null;
    }

    /**
     * @param request 
     * @return
     */
    @Override
    public Blog updateBlog(Blog request) {
        return null;
    }

    /**
     * @param registrationCode 
     * @return
     */
    @Override
    public Blog getBlog(String registrationCode) {
        return null;
    }

    /**
     * @return 
     */
    @Override
    public List<Blog> retrieveBlogs() {
        return List.of();
    }
}
