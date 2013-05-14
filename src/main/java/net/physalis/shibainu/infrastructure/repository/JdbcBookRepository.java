package net.physalis.shibainu.infrastructure.repository;

import net.physalis.shibainu.domain.model.Book;
import net.physalis.shibainu.domain.repository.BookRepository;
import net.physalis.shibainu.domain.repository.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcBookRepository implements BookRepository {

    @Autowired
    private BookQuery bookQuery;

    @Autowired
    private EntityManager<Book> bookEm;

    @Override
    public Book findById(int id) {
        return bookEm.get(id);
    }

    @Override
    public List<Book> findAll() {
        return load(bookQuery.findAll());
    }

    @Override
    public Book save(Book book) {
        if (book.isPersisted()) {
            return bookEm.update(book);
        } else {
            return bookEm.insert(book);
        }
    }

    @Override
    public void delete(Book book) {
        bookEm.delete(book);
    }

    private List<Book> load(List<Integer> ids) {
        List<Book> entities = new ArrayList<>(ids.size());
        for (int id : ids) {
            Book e = bookEm.get(id);
            if (e == null) {
                throw new IllegalStateException("Entity not found for key: " + id);
            }
            entities.add(e);
        }
        return entities;
    }

}
