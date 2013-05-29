package net.physalis.shibainu.domain.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSchemaVersionPk is a Querydsl query type for QSchemaVersionPk
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSchemaVersionPk extends com.mysema.query.sql.RelationalPathBase<QSchemaVersionPk> {

    private static final long serialVersionUID = -1151701483;

    public static final QSchemaVersionPk schemaVersionPk = new QSchemaVersionPk("schema_version_pk");

    public final StringPath version = createString("version");

    public QSchemaVersionPk(String variable) {
        super(QSchemaVersionPk.class, forVariable(variable), "shibainu", "schema_version_pk");
    }

    @SuppressWarnings("all")
    public QSchemaVersionPk(Path<? extends QSchemaVersionPk> path) {
        super((Class)path.getType(), path.getMetadata(), "shibainu", "schema_version_pk");
    }

    public QSchemaVersionPk(PathMetadata<?> metadata) {
        super(QSchemaVersionPk.class, metadata, "shibainu", "schema_version_pk");
    }

}

