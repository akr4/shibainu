package net.physalis.shibainu.infrastructure.repository;

import com.mysema.query.Tuple;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.MappingProjection;
import net.physalis.shibainu.domain.model.Book;
import net.physalis.shibainu.domain.model.QBook;
import net.physalis.shibainu.domain.repository.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlDeleteCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class BookManager implements EntityManager<Book> {

    private static final QBook BOOK = QBook.book;

    @Autowired
    private QueryDslJdbcTemplate qdsl;

    @Cacheable("Book")
    @Override
    public Book get(int id) {
        return qdsl.queryForObject(
                qdsl.newSqlQuery().from(BOOK).where(BOOK.id.eq(id)),
                MappingBookProjection.I);

    }

    @CacheEvict(value = "Book.findAll", allEntries = true)
    @Override
    public Book insert(final Book book) {
        assert !book.isPersisted();

        int id = qdsl.insertWithKey(BOOK, new SqlInsertWithKeyCallback<Integer>() {
            @Override
            public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
                return insert
                        .columns(BOOK.title)
                        .values(book.getTitle())
                        .executeWithKey(BOOK.id);
            }
        });
        return new Book(id, book.getTitle());
    }

    @CacheEvict(value = "Book", key = "#book.id")
    @Override
    public Book update(final Book book) {
        long rows = qdsl.update(BOOK, new SqlUpdateCallback() {

            @Override
            public long doInSqlUpdateClause(SQLUpdateClause update) {
                return update
                        .where(BOOK.id.eq(book.getId()))
                        .set(BOOK.title, book.getTitle())
                        .execute();
            }
        });

        assert rows == 1;
        return book;
    }

    @CacheEvict(value = "Book.findAll", allEntries = true)
    @Override
    public void delete(final Book book) {
        long rows = qdsl.delete(BOOK, new SqlDeleteCallback() {
            @Override
            public long doInSqlDeleteClause(SQLDeleteClause delete) {
                return delete.where(BOOK.id.eq(book.getId())).execute();
            }
        });
        assert rows == 1;
    }

    private static class MappingBookProjection extends MappingProjection<Book> {
        public static final MappingBookProjection I = new MappingBookProjection();

        private MappingBookProjection() {
            super(Book.class, BOOK.id, BOOK.title);
        }

        @Override
        protected Book map(Tuple row) {
            return new Book(row.get(BOOK.id), row.get(BOOK.title));
        }
    }
}
