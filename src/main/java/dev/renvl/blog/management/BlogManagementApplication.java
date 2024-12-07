package dev.renvl.blog.management;

import dev.renvl.blog.management.model.Author;
import dev.renvl.blog.management.model.Blog;
import dev.renvl.blog.management.model.Commentary;
import dev.renvl.blog.management.model.Periodicity;
import dev.renvl.blog.management.repository.AuthorRepository;
import dev.renvl.blog.management.repository.BlogRepository;
import dev.renvl.blog.management.repository.CommentaryRepository;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.time.LocalDate;

@SpringBootApplication
public class BlogManagementApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogManagementApplication.class);

    @Bean
    public CommandLineRunner initDatabase(BlogRepository blogRepository, AuthorRepository authorRepository, CommentaryRepository commentaryRepository) {
        return args -> {

            Author author1 = Author.builder().name("blog one").firstLastname("topic one").secondLastname("Lorem Ipsum 1").dateOfBirth(LocalDate.parse("2000-01-28")).residenceCountry("Bolivia").email("email1@email.com").build();
            Author author2 = Author.builder().name("blog two").firstLastname("topic two").secondLastname("Lorem Ipsum 2").dateOfBirth(LocalDate.parse("2001-02-28")).residenceCountry("USA").email("email2@email.com").build();
            Author author3 = Author.builder().name("blog three").firstLastname("topic three").secondLastname("Lorem Ipsum 3").dateOfBirth(LocalDate.parse("2002-03-28")).residenceCountry("UK").email("email3@email.com").build();
            Author author4 = Author.builder().name("blog four").firstLastname("topic four").secondLastname("Lorem Ipsum 4").dateOfBirth(LocalDate.parse("2003-04-28")).residenceCountry("Trinidad & Tobago").email("email4@email.com").build();
            LOGGER.info("Preloading {}", authorRepository.save(author1));
            LOGGER.info("Preloading {}", authorRepository.save(author2));
            LOGGER.info("Preloading {}", authorRepository.save(author3));
            LOGGER.info("Preloading {}", authorRepository.save(author4));

            Blog blog1 = Blog.builder().blogCode("AAAAAAAA").title("blog one").topic("topic one").content("Lorem Ipsum 1").periodicity(Periodicity.DAILY).commentariesEnabled(true).author(author1).build();
            Blog blog2 = Blog.builder().blogCode("BBBBBBBB").title("blog two").topic("topic two").content("Lorem Ipsum 2").periodicity(Periodicity.DAILY).commentariesEnabled(false).author(author2).build();
            Blog blog3 = Blog.builder().blogCode("CCCCCCCC").title("blog three").topic("topic three").content("Lorem Ipsum 3").periodicity(Periodicity.WEEKLY).commentariesEnabled(false).author(author3).build();
            Blog blog4 = Blog.builder().blogCode("DDDDDDDD").title("blog four").topic("topic four").content("Lorem Ipsum 4").periodicity(Periodicity.MONTHLY).commentariesEnabled(true).author(author4).build();
            LOGGER.info("Preloading {}", blogRepository.save(blog1));
            LOGGER.info("Preloading {}", blogRepository.save(blog2));
            LOGGER.info("Preloading {}", blogRepository.save(blog3));
            LOGGER.info("Preloading {}", blogRepository.save(blog4));

            Commentary commentary1 = Commentary.builder().commentary("commentary one").name("user 1").residenceCountry("Bolivia").email("email1@email.com").score(2).blog(blog1).build();
            Commentary commentary2 = Commentary.builder().commentary("commentary two").name("user 2").residenceCountry("USA").email("email2@email.com").score(5).blog(blog1).build();
            Commentary commentary3 = Commentary.builder().commentary("commentary three").name("user 3").residenceCountry("UK").email("email3@email.com").score(7).blog(blog1).build();
            Commentary commentary4 = Commentary.builder().commentary("commentary four").name("user 4").residenceCountry("Trinidad & Tobago").email("email4@email.com").score(3).blog(blog2).build();
            Commentary commentary5 = Commentary.builder().commentary("commentary five").name("user 5").residenceCountry("Bolivia").email("email5@email.com").score(5).blog(blog2).build();
            Commentary commentary6 = Commentary.builder().commentary("commentary six").name("user 6").residenceCountry("USA").email("email6@email.com").score(9).blog(blog3).build();
            Commentary commentary7 = Commentary.builder().commentary("commentary seven").name("user 7").residenceCountry("UK").email("email7@email.com").score(0).blog(blog4).build();
            LOGGER.info("Preloading {}", commentaryRepository.save(commentary1));
            LOGGER.info("Preloading {}", commentaryRepository.save(commentary2));
            LOGGER.info("Preloading {}", commentaryRepository.save(commentary3));
            LOGGER.info("Preloading {}", commentaryRepository.save(commentary4));
            LOGGER.info("Preloading {}", commentaryRepository.save(commentary5));
            LOGGER.info("Preloading {}", commentaryRepository.save(commentary6));
            LOGGER.info("Preloading {}", commentaryRepository.save(commentary7));
        };
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogManagementApplication.class, args);
    }
}
