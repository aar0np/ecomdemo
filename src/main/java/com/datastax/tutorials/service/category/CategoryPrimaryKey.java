package com.datastax.tutorials.service.category;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class CategoryPrimaryKey {

    @PrimaryKeyColumn(
            name = "parent", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
    private String parent;

    @PrimaryKeyColumn(
            name = "name", 
            ordinal = 1, 
            type = PrimaryKeyType.CLUSTERED, 
            ordering = Ordering.ASCENDING)
    private String name;
    
    @PrimaryKeyColumn(
            name = "id", 
            ordinal = 2, 
            type = PrimaryKeyType.CLUSTERED, 
            ordering = Ordering.ASCENDING)
    private UUID id;

    /**
     * Getter accessor for attribute 'parent'.
     *
     * @return
     *       current value of 'parent'
     */
    public String getParent() {
        return parent;
    }

    /**
     * Setter accessor for attribute 'parent'.
     * @param parent
     * 		new value for 'parent '
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * Getter accessor for attribute 'name'.
     *
     * @return
     *       current value of 'name'
     */
    public String getName() {
        return name;
    }

    /**
     * Setter accessor for attribute 'name'.
     * @param name
     * 		new value for 'name '
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter accessor for attribute 'id'.
     *
     * @return
     *       current value of 'id'
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setter accessor for attribute 'id'.
     * @param id
     * 		new value for 'id '
     */
    public void setId(UUID id) {
        this.id = id;
    }
    
}
