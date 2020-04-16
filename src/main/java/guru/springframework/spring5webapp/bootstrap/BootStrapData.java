package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author christopher = new Author("Christopher", "Paolini");
        Book eragon = new Book("Eragon", "123123");
        christopher.getBooks().add(eragon);
        eragon.getAuthors().add(christopher);

        authorRepository.save(christopher);
        bookRepository.save(eragon);

        Author tolkien = new Author("J.R.R", "Tolkien");
        Book lotr = new Book("Lord of the Rings", "512344231");
        tolkien.getBooks().add(lotr);
        lotr.getAuthors().add(tolkien);
        authorRepository.save(tolkien);
        bookRepository.save(lotr);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());

    }
}
