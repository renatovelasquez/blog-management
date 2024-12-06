package dev.renvl.blog.management.repository;

import dev.renvl.blog.management.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaryRepository extends JpaRepository<Author, Long> {
}