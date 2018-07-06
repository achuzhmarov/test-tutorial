package com.github.achuzhmarov.product;

import com.github.achuzhmarov.common.exception.DataNotFoundException;
import com.github.achuzhmarov.user.AppUser;
import com.github.achuzhmarov.user.UserAuthorizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final UserAuthorizationService userAuthorizationService;

    public ProductService(ProductRepository productRepository, UserAuthorizationService userAuthorizationService) {
        this.productRepository = productRepository;
        this.userAuthorizationService = userAuthorizationService;
    }

    @Transactional(readOnly = true)
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new DataNotFoundException("Product", productId));
    }

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(new Product(product));
    }

    @Transactional
    public Product updateProduct(Long productId, Product product) {
        Product dbProduct = productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Product", productId));

        dbProduct.setPrice(product.getPrice());
        dbProduct.setDiscount(product.getDiscount());
        dbProduct.setName(product.getName());
        dbProduct.setAdvertised(product.isAdvertised());

        return productRepository.save(dbProduct);
    }

    @Transactional(readOnly = true)
    public BigDecimal calculateBonusPoints(Map<Long, Long> productQuantities) {
        List<Product> products = productRepository.findAllById(productQuantities.keySet());
        AppUser currentUser = userAuthorizationService.getCurrentUser();

        return products.stream()
            .map(p -> calculateBonusPoints(currentUser, p, productQuantities.get(p.getId())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateBonusPoints(AppUser user, Product product, Long quantity) {
        if (product.getDiscount() != null) {
            return BigDecimal.ZERO;
        }

        boolean isFavorite = user.getFavProduct().equals(product);
        List<BigDecimal> multipliers = new ArrayList<>();

        if (isFavorite) {
            if (user.isPremium()) {
                multipliers.add(new BigDecimal(8));
            } else {
                multipliers.add(new BigDecimal(5));
            }
        }

        if (product.isAdvertised()) {
            multipliers.add(new BigDecimal(3));
        }

        if (user.isPremium()) {
            multipliers.add(new BigDecimal(2));
        }

        BigDecimal resultMultiplier = multipliers.stream()
            .sorted(Comparator.reverseOrder())
            .limit(2)
            .reduce(BigDecimal.ONE, BigDecimal::multiply);

        return product.getPrice()
            .multiply(new BigDecimal(quantity))
            .multiply(resultMultiplier)
            .divide(BigDecimal.TEN, RoundingMode.HALF_UP);
    }
}
