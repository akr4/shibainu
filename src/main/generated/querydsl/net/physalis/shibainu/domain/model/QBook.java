package net.physalis.shibainu.domain.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBook is a Querydsl query type for QBook
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QBook extends com.mysema.query.sql.RelationalPathBase<QBook> {

    private static final long serialVersionUID = -497235706;

    public static final QBook book = new QBook("book");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath title = createString("title");

    public final com.mysema.query.sql.PrimaryKey<QBook> bookPkey = createPrimaryKey(id);

    public QBook(String variable) {
        super(QBook.class, forVariable(variable), "shibainu", "book");
    }

    @SuppressWarnings("all")
    public QBook(Path<? extends QBook> path) {
        super((Class)path.getType(), path.getMetadata(), "shibainu", "book");
    }

    public QBook(PathMetadata<?> metadata) {
        super(QBook.class, metadata, "shibainu", "book");
    }

}

