<!--- STARTEXCLUDE --->
## 🔥 Building an ECommerce Platform 🔥

[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/datastaxdevs/workshop-streaming-game)
[![License Apache2](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Discord](https://img.shields.io/discord/685554030159593522)](https://discord.com/widget?id=685554030159593522&theme=dark)

<img src="data/img/splash.png?raw=true" align="right" width="400px"/>

## 📋 Table of contents

1. [Create your Database](#1-create-astra-db-instance)
2. [Create your schema](#2-create-your-schema)
3. [Populate the dataset](#3-populate-the-data)
4. [Create a token](#4-create-your-token)
5. [Setup your application](#5-setup-your-application)
6. [Run Unit Tests](#6-run-unit-tests)
7. [Start the Backend API](#7-start-the-backend-api)
8. [Start the frontend](#8-start-the-frontend)

## 1. Create Astra DB Instance

**`ASTRA DB`** is the simplest way to run Cassandra with zero operations at all - just push the button and get your cluster. No credit card required, $25.00 USD credit every month, roughly 20M read/write operations, 80GB storage monthly - sufficient to run small production workloads.

#### ✅ 1a. Register 

If you do have an account yet register and sign In to Astra DB this is FREE and NO CREDIT CARD asked. [https://astra.datastax.com](https://astra.datastax.com): You can use your `Github`, `Google` accounts or register with an `email`.

_Make sure to chose a password with minimum 8 characters, containing upper and lowercase letters, at least one number and special character_

#### ✅ 1b. Create a "FREE" plan

Follow this [guide](https://docs.datastax.com/en/astra/docs/creating-your-astra-database.html), to set up a pay as you go database with a free $25 monthly credit. You will find below recommended values to enter:

- **For the database name** - `demos`

- **For the keyspace name** - `ecommerce`

_You can technically use whatever you want and update the code to reflect the keyspace. This is really to get you on a happy path for the first run._

- **For provider and region**: Choose a provider (GCP, Azure or AWS) and then the related region is where your database will reside physically (choose one close to you or your users).

- **Create the database**. Review all the fields to make sure they are as shown, and click the `Create Database` button.

You will see your new database `pending` in the Dashboard.

![my-pic](data/img/db-pending.png?raw=true)

The status will change to `Active` when the database is ready, this will only take 2-3 minutes. You will also receive an email when it is ready.

**👁️ Walkthrough**

*The Walkthrough mentions the wrong keyspace, make sure to use `ecommerce`*

![image](data/img/astra-create-db.gif?raw=true)

[🏠 Back to Table of Contents](#-table-of-contents)

## 2. Create your schema

Informations:
 - The "top" categories of the product hierarchy can be retrieved using a `parent` of "toplevel".
 - Without specifying a `category`, all categories for the `parent` are returned in an array.
 - Once a category from the bottom of the hierarchy is returned, a `products` ArrayList will be returned.  From there, the returned productIds can be used with the `/product` service.
 - Category navigation is achieved by using the `name` properties returned for each category.
 - /category/toplevel  =>  Category[]: Clothing, Cups and Mugs, Tech Accessories, Wall Decor
 - /category/toplevel/Clothing  =>  Category: Clothing
 - /category/Clothing  =>  Category[]: T-Shirts, Hoodies, Jackets
 - /category/T-Shirts  =>  Category[]: Men’s "Your Face...Autowired" T-Shirt, Men’s "Go Away...Annotation" T-Shirt
 - /category/T-Shirts/Men’s%20"Your%20Face...Autowired"%20T-Shirt  ==>  products List<String>: 'LS534S','LS534M','LS534L','LS534XL','LS5342XL','LS5343XL'


#### ✅ 2a. Open the CqlConsole on Astra

```sql
use ecommerce;
```

#### ✅ 2b. Execute the following script to create the schema

```sql
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
```

[🏠 Back to Table of Contents](#-table-of-contents)

## 3. Populate the Data

#### ✅ 3a. Execute the following script to populate some data

```sql
# Categories
INSERT INTO category (name,id,image,parent,children) VALUES ('Clothing',UUID(),'ls534.png','toplevel',['T-Shirts','Hoodies','Jackets']);
INSERT INTO category (name,id,image,parent,children) VALUES ('Tech Accessories',UUID(),'','toplevel',['Mousepads','Wrist Rests','Laptop Covers']);
INSERT INTO category (name,id,image,parent,children) VALUES ('Cups and Mugs',UUID(),'','toplevel',['Cups','Coffee Mugs','Travel Mugs']);
INSERT INTO category (name,id,image,parent,children) VALUES ('Wall Decor',UUID(),'bh001.png','toplevel',['Posters','Wall Art']);
INSERT INTO category (name,id,image,parent,children) VALUES ('T-Shirts',UUID(),'ls534.png','Clothing',['Men’s "Your Face...Autowired" T-Shirt','Men’s "Go Away...Annotation" T-Shirt']);
INSERT INTO category (name,id,image,parent,children) VALUES ('Hoodies',UUID(),'','Clothing',[]);
INSERT INTO category (name,id,image,parent,children) VALUES ('Jackets',UUID(),'','Clothing',[]);
INSERT INTO category (name,id,image,parent,children) VALUES ('Mousepads',UUID(),'','Tech Accessories',[]);
INSERT INTO category (name,id,image,parent,children) VALUES ('Wrist Rests',UUID(),'','Tech Accessories',[]);
INSERT INTO category (name,id,image,parent,children) VALUES ('Laptop Covers',UUID(),'','Tech Accessories',[]);
INSERT INTO category (name,id,image,parent,children) VALUES ('Cups',UUID(),'','Cups and Mugs',[]);
INSERT INTO category (name,id,image,parent,children) VALUES ('Coffee Mugs',UUID(),'','Cups and Mugs',[]);
INSERT INTO category (name,id,image,parent,children) VALUES ('Travel Mugs',UUID(),'','Cups and Mugs',[]);
INSERT INTO category (name,id,image,parent,children) VALUES ('Posters',UUID(),'','Wall Decor',[]);
INSERT INTO category (name,id,image,parent,children) VALUES ('Wall Art',UUID(),'bh001.png','Wall Decor',['Bigheads']);
INSERT INTO category (name,id,image,parent,products) VALUES ('Men’s "Go Away...Annotation" T-Shirt',UUID(),'ls534.png','T-Shirts',['LS534S','LS534M','LS534L','LS534XL','LS5342XL','LS5343XL']);
INSERT INTO category (name,id,image,parent,products) VALUES ('Men’s "Your Face...Autowired" T-Shirt',UUID(),'ls355.png','T-Shirts',['LS355S','LS355M','LS355L','LS355XL','LS3552XL','LS3553XL']);
INSERT INTO category (name,id,image,parent,products) VALUES ('Bigheads',UUID(),'bh001.png','Wall Art',['bh001','bh002','bh003']);

# Prices
INSERT INTO price(product_id,store_id,value) VALUES ('LS534S','web',14.99);
INSERT INTO price(product_id,store_id,value) VALUES ('LS534M','web',14.99);
INSERT INTO price(product_id,store_id,value) VALUES ('LS534L','web',14.99);
INSERT INTO price(product_id,store_id,value) VALUES ('LS534XL','web',14.99);
INSERT INTO price(product_id,store_id,value) VALUES ('LS5342XL','web',16.99);
INSERT INTO price(product_id,store_id,value) VALUES ('LS5343XL','web',16.99);
INSERT INTO price(product_id,store_id,value) VALUES ('LN355S','web',14.99);
INSERT INTO price(product_id,store_id,value) VALUES ('LN355M','web',14.99);
INSERT INTO price(product_id,store_id,value) VALUES ('LN355L','web',14.99);
INSERT INTO price(product_id,store_id,value) VALUES ('LN355XL','web',14.99);
INSERT INTO price(product_id,store_id,value) VALUES ('LN3552XL','web',16.99);
INSERT INTO price(product_id,store_id,value) VALUES ('LN3553XL','web',16.99);

# Products
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LS534S','LS534','Go Away Annotation T-Shirt','NerdShirts','NS101','Men’s Small "Go Away...Annotation" T-Shirt','Having to answer support questions when you really want to get back to coding?  Wear this to work, and let there be no question as to what you’d rather be doing.',{'size':'Small','material':'cotton, polyester','cut':'men’s','color':'black'},{'ls534.png'});
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LS534M','LS534','Go Away Annotation T-Shirt','NerdShirts','NS101','Men’s Medium "Go Away...Annotation" T-Shirt','Having to answer support questions when you really want to get back to coding?  Wear this to work, and let there be no question as to what you’d rather be doing.',{'size':'Medium','material':'cotton, polyester','cut':'men’s','color':'black'},{'ls534.png'});
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LS534L','LS534','Go Away Annotation T-Shirt','NerdShirts','NS101','Men’s Large "Go Away...Annotation" T-Shirt','Having to answer support questions when you really want to get back to coding?  Wear this to work, and let there be no question as to what you’d rather be doing.',{'size':'Large','material':'cotton, polyester','cut':'men’s','color':'black'},{'ls534.png'});
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LS534XL','LS534','Go Away Annotation T-Shirt','NerdShirts','NS101','Men’s Extra Large "Go Away...Annotation" T-Shirt','Having to answer support questions when you really want to get back to coding?  Wear this to work, and let there be no question as to what you’d rather be doing.',{'size':'Extra Large','material':'cotton, polyester','cut':'men’s','color':'black'},{'ls534.png'});
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LS5342XL','LS534','Go Away Annotation T-Shirt','NerdShirts','NS101','Men’s 2x Large "Go Away...Annotation" T-Shirt','Having to answer support questions when you really want to get back to coding?  Wear this to work, and let there be no question as to what you’d rather be doing.',{'size':'2x Large','material':'cotton, polyester','cut':'men’s','color':'black'},{'ls534.png'});
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LS5343XL','LS534','Go Away Annotation T-Shirt','NerdShirts','NS101','Men’s 3x Large "Go Away...Annotation" T-Shirt','Having to answer support questions when you really want to get back to coding?  Wear this to work, and let there be no question as to what you’d rather be doing.',{'size':'3x Large','material':'cotton, polyester','cut':'men’s','color':'black'},{'ls534.png'});
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LN355S','LN355','Your Face is an @Autowired @Bean T-Shirt','NerdShirts','NS102','Men’s Small "Your Face...Autowired" T-Shirt','Everyone knows that one person who overuses the "your face" jokes.',{'size':'Small','material':'cotton, polyester','cut':'men’s','color':'black'},{'ln355.png'});
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LN355M','LN355','Your Face is an @Autowired @Bean T-Shirt','NerdShirts','NS102','Men’s Medium "Your Face...Autowired" T-Shirt','Everyone knows that one person who overuses the "your face" jokes.',{'size':'Medium','material':'cotton, polyester','cut':'men’s','color':'black'},{'ln355.png'});
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LN355L','LN355','Your Face is an @Autowired @Bean T-Shirt','NerdShirts','NS102','Men’s Large "Your Face...Autowired" T-Shirt','Everyone knows that one person who overuses the "your face" jokes.',{'size':'Large','material':'cotton, polyester','cut':'men’s','color':'black'},{'ln355.png'});
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LN355XL','LN355','Your Face is an @Autowired @Bean T-Shirt','NerdShirts','NS102','Men’s Extra Large "Your Face...Autowired" T-Shirt','Everyone knows that one person who overuses the "your face" jokes.',{'size':'Extra Large','material':'cotton, polyester','cut':'men’s','color':'black'},{'ln355.png'});
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LN3552XL','LN355','Your Face is an @Autowired @Bean T-Shirt','NerdShirts','NS102','Men’s 2x Large "Your Face...Autowired" T-Shirt','Everyone knows that one person who overuses the "your face" jokes.',{'size':'2x Large','material':'cotton, polyester','cut':'men’s','color':'black'},{'ln355.png'});
INSERT INTO product(product_id,product_group,name,brand,model_number,short_desc,long_desc,specifications,images)
VALUES ('LN355XL','LN355','Your Face is an @Autowired @Bean T-Shirt','NerdShirts','NS102','Men’s 3x Large "Your Face...Autowired" T-Shirt','Everyone knows that one person who overuses the "your face" jokes.',{'size':'3x Large','material':'cotton, polyester','cut':'men’s','color':'black'},{'ln355.png'});
```

[🏠 Back to Table of Contents](#-table-of-contents)

## 4. Create your token

#### ✅ 4a. Create the token

Following the [Manage Application Tokens docs](https://docs.datastax.com/en/astra/docs/manage-application-tokens.html) create a token with `Database Admnistrator` roles.

- Go the `Organization Settings`

- Go to `Token Management`

- Pick the role `Database Admnistrator` on the select box

- Click Generate token

**👁️ Walkthrough**

![image](data/img/astra-create-token.gif?raw=true)

This is what the token page looks like. You can now download the values as a CSV. We will need those values but you can also keep this window open for use later.

![image](data/img/astra-token.png?raw=true)

Notice the clipboard icon at the end of each value.

- `clientId:` We will use it as a _username_ to contact to the Cassandra database

- `clientSecret:` We will use it as a _password_ to contact to the Cassandra database

- `appToken:` We will use it as a api token Key to interact with APIs.

#### ✅ 4b. Save your token locally

To know more about roles of each token you can have a look to [this video.](https://www.youtube.com/watch?v=TUTCLsBuUd4&list=PL2g2h-wyI4SpWK1G3UaxXhzZc6aUFXbvL&index=8)

**Note: Make sure you don't close the window accidentally or otherwise - if you close this window before you copy the values, the application token is lost forever. They won't be available later for security reasons.**

We are now set with the database and credentials.

[🏠 Back to Table of Contents](#-table-of-contents)

## 5. Setup your application

To run the application you need to provide the credentials and identifier to the application. you will have to provide 6 values in total as shown below

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/ecomdemo/workshop-ecommerce)

✅ **5a: Enter the token**

- Open the file `ecommerce/src/main/resources/application.yml` as show below.

- Replace `application-token` with values shown on the Astra token screen or picking the values from the CSV token file your dowloaded before including the AstraCS: part of the token.

> To ease the copy-paste you can use the small clipboard icons as show in the walkthrough.

```yaml
astra:
  application-token: <CHANGE_ME>
  database-id: <CHANGE_ME>
  database-region: <CHANGE_ME>
  keyspace: ecommerce
  metrics:
    enabled: false
```

✅ **5b: Enter values related to your DB**

- In Astra DB go back to home page by clicking the logo

- Select you database `workshops` in the left panel and then copy values for `cloud-region` and `database-id` (clusterID) from the details page.

[🏠 Back to Table of Contents](#-table-of-contents)

## 6. Run Unit Tests

The application is now set you should be able to interact with your DB. Let's demonstrate some capabilities.

✅ **6a: Use CqlSession**

Interaction with Cassandra are implemented in Java through drivers and the main Class is `CqlSession`.

Higher level frameworks like Spring, Spring Data, or even quarkus will rely on this object so let's make sure it is part of your Spring context with a `@SpringBootTest`.

```bash
mvn test -Dtest=com.datastax.tutorials.Test01_Connectivity
```

**👁️ Expected output**

```bash
[..init...]
Execute some Cql (CqlSession)
+ Your Keyspace: sag_ecommerce
+ Product Categories: 
Posters
Wall Art
Men’s "Go Away...Annotation" T-Shirt
Men’s "Your Face...Autowired" T-Shirt
Clothing
Cups and Mugs
Tech Accessories
Wall Decor
Coffee Mugs
Cups
Travel Mugs
Laptop Covers
Mousepads
Wrist Rests
Bigheads
Hoodies
Jackets
T-Shirts
List Databases available in your Organization (AstraClient)
+ Your OrganizationID: e195fbea-79b6-4d60-9291-063d8c9e6364
+ Your Databases: 
workshops	 : id=8c98b922-aeb0-4435-a0d5-a2788e23dff8, region=eu-central-1
sample_apps	 : id=c2d6bd3d-6112-47f6-9b66-b033e6174f0e, region=us-east-1
sdk_tests	 : id=a52f5879-3476-42d2-b5c9-81b18fc6d103, region=us-east-1
metrics	 : id=d7ded041-3cfb-4dd4-9957-e20003c3ebe2, region=us-east-1
```

✅ **6b: Working With Spring Data**

Spring Data allows Mapping `Object <=> Table` based on annotation at the java bean level. Then by convention CQL query will be executed under the hood.

```bash
mvn test -Dtest=com.datastax.tutorials.Test02_SpringData
```

**👁️ Expected output**

```bash
Categories:
- Clothing with hildren:[T-Shirts, Hoodies, Jackets]
- Cups and Mugs with hildren:[Cups, Coffee Mugs, Travel Mugs]
- Tech Accessories with hildren:[Mousepads, Wrist Rests, Laptop Covers]
- Wall Decor with hildren:[Posters, Wall Art]
```

✅ **6c: Working With Rest Controller**

`TestRestTemplate` is a neat way to test a web controller. The application will start on a random port with `@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)`

```bash
mvn test -Dtest=com.datastax.tutorials.Test03_RestController
```

**👁️ Expected output**

```bash
List Categories:
Clothing
Cups and Mugs
Tech Accessories
Wall Decor
```

[🏠 Back to Table of Contents](#-table-of-contents)

## 7. Start the backend API

```bash
mvn spring-boot:run
```

![image](data/img/swagger.png?raw=true)


[🏠 Back to Table of Contents](#-table-of-contents)

## 8. Start the frontend

```bash
cd ui
npm install
npm run start
```

![image](data/img/splash.png?raw=true)

[🏠 Back to Table of Contents](#-table-of-contents)

