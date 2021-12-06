package com.datastax.tutorials.service.category;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

/** Repository for {@link CategoryEntity}. */
public interface CategoryRepository extends CassandraRepository<CategoryEntity, CategoryPrimaryKey> {
    
    /**
     * Retrieve the list of categories of a parent partition.
     *
     * @param productId
     *      parent
     * @return
     *      list of prices
     */
    List<CategoryEntity> findByKeyParent(String parent);
    
    /**
     * Find the category if exist.
     *
     * @param parent
     *      parent
     * @param name
     *      name
     * @return
     *      price if it exists in the db
     */
    List<CategoryEntity> findByKeyParentAndKeyName(String parent, String name);
    
}
