package dev.renvl.blog.management.repository;

import dev.renvl.blog.management.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findBlogByBlogCode(String blogCode);
}