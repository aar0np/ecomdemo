# category table
CREATE TABLE category (
    name TEXT,
    id UUID,
    image TEXT,
    parent TEXT,
    children LIST<TEXT>,
    products LIST<TEXT>,
    PRIMARY KEY (parent,name,id)
);

# price table
CREATE TABLE price (
    product_id TEXT,
    store_id TEXT,
    value DECIMAL,
PRIMARY KEY(product_id,store_id));

# product table
CREATE TABLE product (
    product_id TEXT,
    product_group TEXT,
    name TEXT,
    brand TEXT,
    model_number TEXT,
    short_desc TEXT,
    long_desc TEXT,
    specifications MAP<TEXT,TEXT>,
    linked_documents MAP<TEXT,TEXT>,
    images SET<TEXT>,
PRIMARY KEY(product_id));
