package com.datastax.tutorials.service.category;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("category")
public class CategoryEntity implements Serializable {

    /** Serial. */
    private static final long serialVersionUID = 4504093417018484534L;
    
    @PrimaryKey
    private CategoryPrimaryKey key;
    
    @Column("image")
    @CassandraType(type = Name.TEXT)
    private String image;
    
    @Column("children")
    @CassandraType(type = CassandraType.Name.LIST, typeArguments = Name.TEXT)
    private List<String> children;
    
    @Column("products")
    @CassandraType(type = CassandraType.Name.LIST, typeArguments = Name.TEXT)
    private List<String> products;

    /**
     * Getter accessor for attribute 'key'.
     *
     * @return
     *       current value of 'key'
     */
    public CategoryPrimaryKey getKey() {
        return key;
    }

    /**
     * Setter accessor for attribute 'key'.
     * @param key
     * 		new value for 'key '
     */
    public void setKey(CategoryPrimaryKey key) {
        this.key = key;
    }

    /**
     * Getter accessor for attribute 'image'.
     *
     * @return
     *       current value of 'image'
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter accessor for attribute 'image'.
     * @param image
     * 		new value for 'image '
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Getter accessor for attribute 'children'.
     *
     * @return
     *       current value of 'children'
     */
    public List<String> getChildren() {
        return children;
    }

    /**
     * Setter accessor for attribute 'children'.
     * @param children
     * 		new value for 'children '
     */
    public void setChildren(List<String> children) {
        this.children = children;
    }

    /**
     * Getter accessor for attribute 'products'.
     *
     * @return
     *       current value of 'products'
     */
    public List<String> getProducts() {
        return products;
    }

    /**
     * Setter accessor for attribute 'products'.
     * @param products
     * 		new value for 'products '
     */
    public void setProducts(List<String> products) {
        this.products = products;
    }

}
