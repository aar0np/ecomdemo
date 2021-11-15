# e-commerce demo


## pre-requisites
Needs the following environment variables defined:
 - ASTRA_DB_ID
 - ASTRA_DB_REGION
 - ASTRA_DB_KEYSPACE
 - ASTRA_DB_APPLICATION_TOKEN

These can be defined in the same terminal session as the application is run from.  You can also specify these env vars as a part of a run configuration in your IDE.

## To run:

    mvn clean install
    java -jar target/ecomdemo-0.1.jar

## Services
Endpoints and expected table CQL listed for each service below.  Create each table in the same keyspace that you have set the `ASTRA_DB_KEYSPACE` env var to.

### Product service
Endpoint:
 - /product/{productid}

Table CQL:

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

### Price service
Endpoints:
 - /price/{productid}
 - /price/{productid}/{storeid}

Note: Without specifying a `storeid`, the default `storeid` of "web" is used.  See `EcomController.java`.

Table CQL:

    CREATE TABLE price (
        product_id TEXT,
        store_id TEXT,
        value DECIMAL,
    PRIMARY KEY(product_id,store_id));

### Category service
Endpoints:
 - /category/{parent}
 - /category/{parent}/{category}

Notes:
 - The "top" categories of the product hierarchy can be retrieved using a `parent` of "toplevel".
 - Without specifying a `category`, all categories for the `parent` are returned in an array.
 - Once a category from the bottom of the hierarchy is returned, a `products` ArrayList will be returned.  From there, the returned productIds can be used with the `/product` service.
 - Category navigation is achieved by using the `name` properties returned for each category.
 - /category/toplevel  =>  Category[]: Clothing, Cups and Mugs, Tech Accessories, Wall Decor
 - /category/toplevel/Clothing  =>  Category: Clothing
 - /category/Clothing  =>  Category[]: T-Shirts, Hoodies, Jackets
 - /category/T-Shirts  =>  Category[]: Men’s "Your Face...Autowired" T-Shirt, Men’s "Go Away...Annotation" T-Shirt
 - /category/T-Shirts/Men’s%20"Your%20Face...Autowired"%20T-Shirt  ==>  products List<String>: 'LS534S','LS534M','LS534L','LS534XL','LS5342XL','LS5343XL'

Table CQL:

    CREATE TABLE price (
        product_id TEXT,
        store_id TEXT,
        value DECIMAL,
    PRIMARY KEY(product_id,store_id));
