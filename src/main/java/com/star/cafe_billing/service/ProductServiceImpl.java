package com.star.cafe_billing.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.star.cafe_billing.dto.ProductRequest;
import com.star.cafe_billing.dto.ProductResponse;
import com.star.cafe_billing.entity.Category;
import com.star.cafe_billing.entity.Product;
import com.star.cafe_billing.entity.Shop;
import com.star.cafe_billing.entity.SubProduct;
import com.star.cafe_billing.repository.CategoryRepository;
import com.star.cafe_billing.repository.ProductRepository;
import com.star.cafe_billing.repository.ShopRepository;
import com.star.cafe_billing.repository.SubProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;
    private final SubProductRepository subProductRepository;

    private final Cloudinary cloudinary;

    // CREATE PRODUCT (JSON API)
    @Override
    public Product createProduct(ProductRequest request){

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        Shop shop = shopRepository
                .findById(request.getShopId())
                .orElseThrow(() ->
                        new RuntimeException("Shop not found"));

        SubProduct subProduct = subProductRepository
                .findById(request.getSubProductId())
                .orElseThrow(() ->
                        new RuntimeException("SubProduct not found"));

        Product product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        product.setShop(shop);
        product.setSubProduct(subProduct);
        product.setActive(true);

        return productRepository.save(product);
    }

    // CREATE PRODUCT WITH IMAGE (CLOUDINARY)
    @Override
    public Product createProductWithImage(
            String name,
            Double price,
            Long categoryId,
            Long shopId,
            Long subProductId,
            MultipartFile image
    ) {

        try {

            Category category = categoryRepository
                    .findById(categoryId)
                    .orElseThrow(() ->
                            new RuntimeException("Category not found"));

            Shop shop = shopRepository
                    .findById(shopId)
                    .orElseThrow(() ->
                            new RuntimeException("Shop not found"));

            SubProduct subProduct = subProductRepository
                    .findById(subProductId)
                    .orElseThrow(() ->
                            new RuntimeException("SubProduct not found"));

            // Upload image to Cloudinary
            Map uploadResult = cloudinary.uploader().upload(
                    image.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "cafe-products"
                    )
            );

            String imageUrl = uploadResult.get("secure_url").toString();

            Product product = new Product();

            product.setName(name);
            product.setPrice(price);
            product.setCategory(category);
            product.setShop(shop);
            product.setSubProduct(subProduct);
            product.setActive(true);

            // Save Cloudinary URL
            product.setImageUrl(imageUrl);

            return productRepository.save(product);

        } catch (IOException e) {

            throw new RuntimeException("Image upload failed");

        }
    }

    // UPDATE PRODUCT
    @Override
    public Product updateProduct(Long id, Product product) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());
        existing.setSubProduct(product.getSubProduct());

        return productRepository.save(existing);
    }

    // GET PRODUCTS BY SHOP
    @Override
    public List<Product> getProductsByShop(Long shopId) {
        return productRepository.findByActiveTrueAndShopId(shopId);
    }

    // GET PRODUCTS BY CATEGORY
    @Override
    public List<Product> getProductsByCategory(Long shopId, Long categoryId) {

        return productRepository
                .findByCategoryIdAndShopId(categoryId, shopId);
    }

    // DISABLE PRODUCT
    @Override
    public void disableProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setActive(false);

        productRepository.save(product);
    }

    // PRICE FILTER
    @Override
    public List<Product> getProducts(
            Long shopId,
            Long categoryId,
            Double minPrice,
            Double maxPrice
    ){

        return productRepository
                .findByShopIdAndCategoryIdAndPriceBetweenAndActiveTrue(
                        shopId,
                        categoryId,
                        minPrice,
                        maxPrice
                );
    }

    // FAVOURITES
    @Override
    public List<ProductResponse> getFavouriteProducts(Long shopId) {

        List<Product> products =
                productRepository
                        .findByShopIdAndFavouriteTrueAndActiveTrue(shopId);

        return products.stream()
                .map(this::mapToResponse)
                .toList();
    }

    // TOGGLE FAVOURITE
    @Override
    public void toggleFavourite(Long productId) {

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        product.setFavourite(!product.getFavourite());

        productRepository.save(product);
    }

    // SEARCH PRODUCTS
    @Override
    public List<Product> searchProducts(Long shopId, String keyword) {

        return productRepository
                .findByShopIdAndNameContainingIgnoreCaseAndActiveTrue(
                        shopId,
                        keyword
                );
    }

    // GET PRODUCTS BY SUB PRODUCT
    @Override
    public List<Product> getProductsBySubProduct(
            Long shopId,
            Long subProductId
    ) {

        return productRepository
                .findByShopIdAndSubProductIdAndActiveTrue(
                        shopId,
                        subProductId
                );
    }

    private ProductResponse mapToResponse(Product product){

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .favourite(product.getFavourite())
                .build();
    }
}