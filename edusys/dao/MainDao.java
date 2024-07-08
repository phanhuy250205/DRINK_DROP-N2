
package com.edusys.dao;

import java.util.List;

public abstract class MainDao<EntityType, KeyType> {
    public abstract void insert(EntityType entity); 
    public abstract void update(EntityType entity); 
    public abstract void delete(KeyType id);
    public abstract List<EntityType> selectAll();
    public abstract EntityType selectById(KeyType id);
    public abstract List<EntityType> selectBySql(String sql, Object... args);
}
