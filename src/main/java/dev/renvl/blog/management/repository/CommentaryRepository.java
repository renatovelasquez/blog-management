package dev.renvl.blog.management.repository;

import dev.renvl.blog.management.model.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
    List<Commentary> getAllByBlog_BlogCode(String blogCode);
}