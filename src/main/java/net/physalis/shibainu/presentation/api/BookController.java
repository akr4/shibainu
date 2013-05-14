package net.physalis.shibainu.presentation.api;

import net.physalis.shibainu.domain.model.Book;
import net.physalis.shibainu.domain.repository.BookRepository;
import net.physalis.shibainu.presentation.csrf.PreventCsrf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@PreventCsrf
@Controller
@RequestMapping(value = "/books", produces = "application/json")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody Book find(@PathVariable int id) {
        return bookRepository.findById(id);
    }
}
