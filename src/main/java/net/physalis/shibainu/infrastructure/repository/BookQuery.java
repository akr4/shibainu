package net.physalis.shibainu.infrastructure.repository;

import com.mysema.query.Tuple;
import com.mysema.query.types.MappingProjection;
import net.physalis.shibainu.domain.model.QBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookQuery {

    private static final QBook BOOK = QBook.book;

    @Autowired
    private QueryDslJdbcTemplate qdslTemplate;

    @Cacheable("Book.findAll")
    public List<Integer> findAll() {
        return qdslTemplate.query(
                qdslTemplate.newSqlQuery().from(BOOK).orderBy(BOOK.id.asc()),
                MappingBookProjection.I);
    }

    private static class MappingBookProjection extends MappingProjection<Integer> {
        public static final MappingBookProjection I = new MappingBookProjection();

        private MappingBookProjection() {
            super(Integer.class, BOOK.id);
        }

        @Override
        protected Integer map(Tuple row) {
            return row.get(BOOK.id);
        }
    }
}
