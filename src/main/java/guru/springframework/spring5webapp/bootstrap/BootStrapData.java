package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

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

        Publisher penguin = new Publisher("penguin", "Penguinroad", "", "3660", "Rjukan");

        publisherRepository.save(penguin);

        eragon.setPublisher(penguin);
        penguin.getBooks().add(eragon);
        publisherRepository.save(penguin);
        bookRepository.save(eragon);

        lotr.setPublisher(penguin);
        penguin.getBooks().add(lotr);

        publisherRepository.save(penguin);
        bookRepository.save(lotr);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + penguin.getBooks().size());

    }
}
