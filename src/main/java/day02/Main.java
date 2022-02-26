package day02;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/bookstore?useUnicode=true");
            dataSource.setUserName("root");
            dataSource.setPassword("root");

        } catch (SQLException sqlException) {
            throw new IllegalArgumentException("SQL" + sqlException);
        }
        Flyway flyway = Flyway.configure().locations("db/migration/bookstore").dataSource(dataSource).load();
        flyway.migrate();

        BookRepository bookRepository = new BookRepository(dataSource);
        // bookRepository.saveBook("None","The big one", 1,100);
        //bookRepository.saveBook("Non","The big one", 1,100);
        bookRepository.updateIncreasePiecesBy(1L,10);
        System.out.println(bookRepository.findBookByPriceCheapest());
        bookRepository.findAllBooks().stream().forEach(System.out::println);
        bookRepository.findBooksByAuthor("Mo").stream().forEach(System.out::println);
    }
}
