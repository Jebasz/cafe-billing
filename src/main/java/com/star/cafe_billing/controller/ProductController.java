package com.star.cafe_billing.controller;

import com.star.cafe_billing.dto.ProductRequest;
import com.star.cafe_billing.dto.ProductResponse;
import com.star.cafe_billing.entity.Product;
import com.star.cafe_billing.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // OLD JSON API (DO NOT REMOVE - backward compatible)
    @PostMapping
    public Product createProduct(
            @RequestBody ProductRequest request){

        return productService.createProduct(request);
    }

    // NEW IMAGE UPLOAD API (Cloudinary compatible)
    @PostMapping(
            value = "/with-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Product createProductWithImage(

            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Long categoryId,
            @RequestParam Long shopId,
            @RequestParam Long subProductId,
            @RequestParam MultipartFile image

    ){

        return productService.createProductWithImage(
                name,
                price,
                categoryId,
                shopId,
                subProductId,
                image
        );
    }

    // UPDATE PRODUCT
    @PutMapping("/{id}")
    public Product updateProduct(

            @PathVariable Long id,
            @RequestBody Product product

    ){

        return productService.updateProduct(id, product);
    }

    // GET PRODUCTS BY SHOP
    @GetMapping("/shop/{shopId}")
    public List<Product> getProductsByShop(@PathVariable Long shopId){
        return productService.getProductsByShop(shopId);
    }

    // GET PRODUCTS BY CATEGORY
    @GetMapping("/shop/{shopId}/category/{categoryId}")
    public List<Product> getProductsByCategory(

            @PathVariable Long shopId,
            @PathVariable Long categoryId

    ){

        return productService.getProductsByCategory(
                shopId,
                categoryId
        );
    }

    // FILTER PRODUCTS
    @GetMapping
    public List<Product> getProducts(

            @RequestParam Long shopId,
            @RequestParam Long categoryId,
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice

    ){

        return productService.getProducts(
                shopId,
                categoryId,
                minPrice,
                maxPrice
        );
    }

    // SEARCH PRODUCTS
    @GetMapping("/search")
    public List<Product> searchProducts(

            @RequestParam Long shopId,
            @RequestParam String keyword

    ){

        return productService.searchProducts(shopId, keyword);
    }

    // DISABLE PRODUCT
    @DeleteMapping("/{id}")
    public void disableProduct(@PathVariable Long id){
        productService.disableProduct(id);
    }

    // GET FAVOURITES
    @GetMapping("/favourites")
    public List<ProductResponse> getFavourites(

            @RequestParam Long shopId

    ){

        return productService.getFavouriteProducts(shopId);
    }

    // TOGGLE FAVOURITE
    @PatchMapping("/{id}/favourite")
    public void toggleFavourite(@PathVariable Long id){

        productService.toggleFavourite(id);
    }

    // GET PRODUCTS BY SUB PRODUCT
    @GetMapping("/sub-product")
    public List<Product> getProductsBySubProduct(

            @RequestParam Long shopId,
            @RequestParam Long subProductId

    ){

        return productService.getProductsBySubProduct(
                shopId,
                subProductId
        );

    }

}