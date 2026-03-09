package com.star.cafe_billing.controller;

import com.star.cafe_billing.dto.ProductRequest;
import com.star.cafe_billing.dto.ProductResponse;
import com.star.cafe_billing.entity.Product;
import com.star.cafe_billing.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 🔥 OLD JSON API (DO NOT REMOVE - backward compatible)
    @PostMapping
    public Product createProduct(
            @RequestBody ProductRequest request){

        return productService.createProduct(request);
    }

    // 🔥 NEW IMAGE UPLOAD API
    @PostMapping("/with-image")
    public Product createProductWithImage(
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Long categoryId,
            @RequestParam Long shopId,
            @RequestParam Long subProductId,
            @RequestParam MultipartFile image){

        return productService.createProductWithImage(
                name,
                price,
                categoryId,
                shopId,
                subProductId,
                image
        );
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product){

        return productService.updateProduct(id,product);
    }

    @GetMapping("/shop/{shopId}")
    public List<Product> getProductsByShop(@PathVariable Long shopId){
        return productService.getProductsByShop(shopId);
    }

    @GetMapping("/shop/{shopId}/category/{categoryId}")
    public List<Product> getProductsByCategory(
            @PathVariable Long shopId,
            @PathVariable Long categoryId){

        return productService
                .getProductsByCategory(shopId,categoryId);
    }

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

    // 🔍 NEW SEARCH API (FOR "OTHERS → SEARCH PRODUCTS")
    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam Long shopId,
            @RequestParam String keyword){

        return productService.searchProducts(shopId, keyword);
    }

    @DeleteMapping("/{id}")
    public void disableProduct(@PathVariable Long id){
        productService.disableProduct(id);
    }

    @GetMapping("/favourites")
    public List<ProductResponse> getFavourites(
            @RequestParam Long shopId){

        return productService.getFavouriteProducts(shopId);
    }

    @PatchMapping("/{id}/favourite")
    public void toggleFavourite(@PathVariable Long id){

        productService.toggleFavourite(id);
    }

    @GetMapping("/sub-product")
    public List<Product> getProductsBySubProduct(

            @RequestParam Long shopId,
            @RequestParam Long subProductId
    ) {

        return productService.getProductsBySubProduct(
                shopId,
                subProductId
        );

    }
}