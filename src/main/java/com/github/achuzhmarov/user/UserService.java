package com.github.achuzhmarov.user;

import com.github.achuzhmarov.common.exception.DataNotFoundException;
import com.github.achuzhmarov.product.Product;
import com.github.achuzhmarov.product.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final UserAuthorizationService userAuthorizationService;

    public UserService(UserRepository userRepository,
                       ProductRepository productRepository,
                       UserAuthorizationService userAuthorizationService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.userAuthorizationService = userAuthorizationService;
    }

    @Transactional
    public void setFavProduct(Long productId) {
        AppUser currentAppUser = userAuthorizationService.getCurrentUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Product", productId));

        currentAppUser.setFavProduct(product);
        userRepository.save(currentAppUser);
    }
}
