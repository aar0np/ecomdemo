<!--- STARTEXCLUDE --->
## 🔥 Building an ECommerce Platform 🔥

[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/datastaxdevs/workshop-streaming-game)
[![License Apache2](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Discord](https://img.shields.io/discord/685554030159593522)](https://discord.com/widget?id=685554030159593522&theme=dark)

<img src="img/splash.png?raw=true" align="right" width="400px"/>

## 📋 Table of contents


1. [Create your Database](#1-create-astra-db-instance)
2. [Create your schema](#5-create-a-table)
3. [Create a token](#5-create-a-table)
4. [Setup your Database](#5-create-a-table)
5. [Start the Backend API](#5-create-a-table)

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

[🏠 Back to Table of Contents](#-table-of-content)

## 2. Create your schema

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

## 3. Create your token

Following the [Manage Application Tokens docs](https://docs.datastax.com/en/astra/docs/manage-application-tokens.html) create a token with `Database Admnistrator` roles.

- Go the `Organization Settings`

- Go to `Token Management`

- Pick the role `Database Admnistrator` on the select box

- Click Generate token

**👁️ Walkthrough**

![image](img/astra-create-token.gif?raw=true)

This is what the token page looks like. You can now download the values as a CSV. We will need those values but you can also keep this window open for use later.

![image](img/astra-token.png?raw=true)

Notice the clipboard icon at the end of each value.

- `clientId:` We will use it as a _username_ to contact to the Cassandra database

- `clientSecret:` We will use it as a _password_ to contact to the Cassandra database

- `appToken:` We will use it as a api token Key to interact with APIs.

#### ✅ 6b. Copy your token in your clipboard

To know more about roles of each token you can have a look to [this video.](https://www.youtube.com/watch?v=TUTCLsBuUd4&list=PL2g2h-wyI4SpWK1G3UaxXhzZc6aUFXbvL&index=8)

**Note: Make sure you don't close the window accidentally or otherwise - if you close this window before you copy the values, the application token is lost forever. They won't be available later for security reasons.**

We are now set with the database and credentials. Let's start coding with Spring !

[🏠 Back to Table of Contents](#-table-of-content)




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

    CREATE TABLE category (
        name TEXT,
        id UUID,
        image TEXT,
        parent TEXT,
        children LIST<TEXT>,
        products LIST<TEXT>,
        PRIMARY KEY (parent,name,id));
