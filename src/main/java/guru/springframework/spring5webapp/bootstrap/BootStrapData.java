package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
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

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher1 = new Publisher("Pub1", "B126, 1/16 Road", "Tokyo", "112134");
        Publisher publisher2 = new Publisher("Pub2", "B110, 1/26 Road", "New York", "112156");

        publisherRepository.save(publisher1);
        publisherRepository.save(publisher2);


        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher1);

        publisher1.getBooks().add(ddd);


        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE Development without EJB", "124432");
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);
        noEjb.setPublisher(publisher2);

        publisher2.getBooks().add(noEjb);

        authorRepository.save(rod);
        bookRepository.save(noEjb);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: "+ bookRepository.count());
        System.out.println("Number of publishers: "+ publisherRepository.count());
        System.out.println("Number of books for publisher 1: "+ publisher1.getBooks().size());
        System.out.println("Number of books for publisher 2: "+ publisher2.getBooks().size());

    }
}
