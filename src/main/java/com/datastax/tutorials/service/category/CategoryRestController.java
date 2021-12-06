package com.datastax.tutorials.service.category;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Expose Rest Api to interact with prices. 
 *
 * @author Cedrick LUNVEN
 * @author Aaron PLOETZ 
 */
@RestController
@CrossOrigin(
  methods = {POST, GET, OPTIONS, PUT, DELETE, PATCH},
  maxAge = 3600,
  allowedHeaders = {"x-requested-with", "origin", "content-type", "accept"},
  origins = "*" 
)
@RequestMapping("/api/v1/categories/")
@Tag(name = "Category Service", description="Provide crud operations for Category")
public class CategoryRestController {
    
    /** Default store id. */
    public static final String DEFAULT_STORE_ID = "web";
    
    /** Inject the repository. */
    private CategoryRepository catRepo;
    
    /**
     * Injection through constructor.
     *  
     * @param repo
     *      repository
     */
    public CategoryRestController(CategoryRepository repo) {
        this.catRepo = repo;
    }
    
    /**
     * Retrieve all categories from a parent.
     * 
     * @param req
     *      current request
     * @param parent id
     *      product identifier
     * @return
     *      list of categories
     */
    @GetMapping("/{parent}")
    @Operation(
     summary = "Retrieve Categories for a product from its id",
     description= "Find **category list** from a parent from its id `SELECT * FROM category WHERE parent =?`",
     responses = {
       @ApiResponse(
         responseCode = "200",
         description = "A list of category is provided for the parent",
         content = @Content(
           mediaType = "application/json",
           schema = @Schema(implementation = Category.class, name = "Category")
         )
       ),
       @ApiResponse(
         responseCode = "404", 
         description = "Parent not found",
         content = @Content(mediaType = "")),
       @ApiResponse(
         responseCode = "400",
         description = "Invalid parameter check productId format."),
       @ApiResponse(
         responseCode = "500",
         description = "Technical Internal error.") 
    })
    public ResponseEntity<Stream<Category>> findCategoriesFromParent(
            HttpServletRequest req, 
            @PathVariable(value = "parent")
            @Parameter(name = "parent", description = "Parent identifier", example = "T-Shirts")
            String parent) {
        // Get the partition (be careful unicity is here not ensured
        List<CategoryEntity> e = catRepo.findByKeyParent(parent);
        if (e.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(e.stream().map(this::mapCategory));
    }
            
    @GetMapping("/category/{parent}/{name}")
    @Operation(
       summary = "Retrieve category for a parent and a name",
       description= "Find Category from a product and a store `SELECT * FROM category WHERE parent =? and name=?`",
       responses = {
         @ApiResponse(
           responseCode = "200",
           description = "the Category is retrieved",
           content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class, name = "Category"))),
         @ApiResponse(
           responseCode = "404", 
           description = "Parent ot name not found"),
         @ApiResponse(
           responseCode = "400",
           description = "Invalid parameter check parent or name format."),
         @ApiResponse(
           responseCode = "500",
           description = "Technical Internal error.") 
    })
    public ResponseEntity<Category> findByProductIdAndStoreId(HttpServletRequest req, 
            @PathVariable(value = "productid")
            @Parameter(name = "parent", description = "Parent identifier", example = "Clothing")
            String parent,
            @PathVariable(value = "name")
            @Parameter(name = "name", description = "Category name", example = "T-Shirts")
            String name) {
        List<CategoryEntity> categories = catRepo.findByKeyParentAndKeyName(parent, name);
        if (categories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (categories.size() > 1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(mapCategory(categories.get(0)));
    }       
     
    /**
     * Mapping Entity => REST.
     *
     * @param p
     *      entity
     * @return
     *      rest bean
     */
    private Category mapCategory(CategoryEntity c) {
        Category ca = new Category();
        ca.setParent(c.getKey().getParent());
        ca.setName(c.getKey().getName());
        ca.setId(c.getKey().getId());
        ca.setImage(c.getImage());
        ca.setChildren(c.getChildren());
        ca.setProducts(c.getProducts());
        return ca;
    }
    
}
