package net.physalis.shibainu.domain.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBookPkey is a Querydsl query type for QBookPkey
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QBookPkey extends com.mysema.query.sql.RelationalPathBase<QBookPkey> {

    private static final long serialVersionUID = 1699402261;

    public static final QBookPkey bookPkey = new QBookPkey("book_pkey");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QBookPkey(String variable) {
        super(QBookPkey.class, forVariable(variable), "shibainu", "book_pkey");
    }

    @SuppressWarnings("all")
    public QBookPkey(Path<? extends QBookPkey> path) {
        super((Class)path.getType(), path.getMetadata(), "shibainu", "book_pkey");
    }

    public QBookPkey(PathMetadata<?> metadata) {
        super(QBookPkey.class, metadata, "shibainu", "book_pkey");
    }

}

