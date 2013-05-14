package net.physalis.shibainu.domain.repository;

import net.physalis.shibainu.domain.model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();

    Book findById(int id);

    Book save(Book book);

    void delete(Book book);

}
