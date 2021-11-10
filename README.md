# e-commerce demo


## pre-requisites
Needs the following environment variables defined:
 - ASTRA_DB_ID
 - ASTRA_DB_REGION
 - ASTRA_DB_KEYSPACE
 - ASTRA_DB_APPLICATION_TOKEN

These can be defined in the same terminal session as the application is run from.  You can also specify these env vars as a part of a run configuration in your IDE.

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
