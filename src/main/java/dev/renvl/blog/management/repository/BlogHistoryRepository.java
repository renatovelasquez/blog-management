package dev.renvl.blog.management.repository;

import dev.renvl.blog.management.model.BlogHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogHistoryRepository extends JpaRepository<BlogHistory, Long> {
}