package net.physalis.shibainu.domain.model;

import com.mysema.query.annotations.QueryEntity;

@QueryEntity
public class Book extends AbstractEntity {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public Book(int id, String title) {
        super(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != getClass()) return false;

        return super.equals(o);
    }
}
