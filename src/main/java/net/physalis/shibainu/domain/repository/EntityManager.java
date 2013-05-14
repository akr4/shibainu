package net.physalis.shibainu.domain.repository;

import net.physalis.shibainu.domain.model.AbstractEntity;

public interface EntityManager<T extends AbstractEntity> {

    T get(int id);

    T insert(T entity);

    T update(T entity);

    void delete(T entity);
}
