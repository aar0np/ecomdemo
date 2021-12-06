
# category data
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

# price data
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

# product data
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
