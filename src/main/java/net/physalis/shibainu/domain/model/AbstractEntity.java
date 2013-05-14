package net.physalis.shibainu.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

    private Integer id;

    protected AbstractEntity() {
    }

    protected AbstractEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isPersisted() {
        return id != null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o == this) { return true; }
        if (o.getClass() != getClass()) { return false; }

        AbstractEntity rhs = (AbstractEntity) o;
        return new EqualsBuilder().append(id, rhs.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

}