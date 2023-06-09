package com.coderhouse.java.services;

import com.coderhouse.java.middlewares.ApiException;
import com.coderhouse.java.models.Product;
import com.coderhouse.java.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAllByDeletedAtIsNull();
    }

    public Product getOne(Long clientId) {
        return findProductOrFail(clientId);
    }

    public Product findProductOrFail(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ApiException("Product #" + productId + " not found", HttpStatus.NOT_FOUND));
    }

    public Product createOne(Product product) {
        assertRequest(product);
        checkIfProductExists(product);
        Product instance = Product.createWith(product.getTitle(), product.getDescription(), product.getSku(), product.getPrice(), product.getStock());
        return productRepository.save(instance);
    }

    private void checkIfProductExists(Product product) {
        boolean exists = productRepository.findBySku(product.getSku()).isPresent();
        if (exists) throw new ApiException("Product already exists", HttpStatus.BAD_REQUEST);
    }

    public Product updateOne(Product product, Long clientId) {
        assertRequest(product);
        Product instance = findProductOrFail(clientId);
        instance.updateWith(product.getTitle(), instance.getDescription(), product.getSku(), product.getPrice(), product.getStock());
        return productRepository.save(instance);
    }

    private static void assertRequest(Product product) {
        if (!product.hasTitle() || !product.hasDescription() || !product.hasSku() || !product.hasPrice() || !product.hasStock())
            throw new ApiException("Properties body missing", HttpStatus.BAD_REQUEST);
    }

    public void deleteOne(Long clientId) {
        Product product = findProductOrFail(clientId);
        product.setDeletedAt(new Date());
        productRepository.save(product);
    }

}
