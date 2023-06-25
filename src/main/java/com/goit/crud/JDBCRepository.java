package com.goit.crud;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public abstract class JDBCRepository<T> {

    public abstract List<T> listAll();

    public abstract void setName(Long id, String name);

    public abstract void deleteById(Long id);

    public abstract String getById(Long id);

    public abstract Long create(String name);
}
