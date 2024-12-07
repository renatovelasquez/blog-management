package dev.renvl.blog.management.service;

import dev.renvl.blog.management.model.Author;
import dev.renvl.blog.management.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * @param request
     * @return
     */
    @Override
    public Author createAuthor(Author request) {
        return authorRepository.save(request);
    }
}
