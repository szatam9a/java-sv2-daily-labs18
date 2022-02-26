package day02;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public BookRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//      this.id = id;
//        this.author = author;
//        this.title = title;
//        this.price = price;
//        this.pieces = pieces;

    public void saveBook(String author, String title, int price, int pieces) {
        jdbcTemplate.update("insert into books(author,title,price,pieces) values(?,?,?,?)", author, title, price, pieces);
    }

    public List<Book> findAllBooks() {
        return jdbcTemplate.query("select * from books ", (rs, rowNum) ->
                new Book(rs.getLong("id"), rs.getString("author"),
                        rs.getString("title"), rs.getInt("price"), rs.getInt("pieces"))
        );
    }

    public List<Book> findBooksByAuthor(String author) {
        return jdbcTemplate.query("select * from books where author like ? ",
                (rs, rowNum) ->
                        new Book(rs.getLong("id"), rs.getString("author"),
                                rs.getString("title"), rs.getInt("price"), rs.getInt("pieces"))
                , author + "%");
    }

    public Optional<Book> findBookByPriceCheapest() {
        return Optional.of(jdbcTemplate.queryForObject("SELECT *, MIN(price) FROM books", (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("author"),
                rs.getString("title"), rs.getInt("price"), rs.getInt("pieces"))));

    }

    public void updateIncreasePiecesBy(Long id, int pieces) {
        jdbcTemplate.update("update books set pieces = pieces+? where id =?", pieces, id);
    }
}
