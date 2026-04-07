package com.shruti.ecom_back.service;

import com.shruti.ecom_back.dto.CartItemResponse;
import com.shruti.ecom_back.dto.CartResponse;
import com.shruti.ecom_back.model.Cart;
import com.shruti.ecom_back.model.User;
import com.shruti.ecom_back.repository.CartRepository;
import com.shruti.ecom_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemService cartItemService;

    private User getCurrentUser() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Cart getUserCart(User user) {

        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    public CartResponse getCart() {

        User user = getCurrentUser();
        Cart cart = getUserCart(user);

        List<CartItemResponse> items = cartItemService.getCartItems(cart);

        int totalItems = items.stream()
                .mapToInt(CartItemResponse::getQuantity)
                .sum();

        BigDecimal totalPrice = items.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartResponse.builder()
                .cartId(cart.getId())
                .items(items)
                .totalItems(totalItems)
                .totalPrice(totalPrice)
                .build();
    }


    public void addToCart(Long productId, int quantity) {

        if (quantity < 1) {
            throw new RuntimeException("Quantity must be at least 1");
        }

        User user = getCurrentUser();
        Cart cart = getUserCart(user);

        cartItemService.addToCart(cart, productId, quantity);
    }

    public void clearCart() {

        User user = getCurrentUser();
        Cart cart = getUserCart(user);

        cart.getItems().clear();

        cartRepository.save(cart);
    }

    public void updateQuantity(Long cartItemId, int quantity) {

        if (quantity < 1) {
            cartItemService.removeItem(cartItemId);
            return;
        }

        cartItemService.updateQuantity(cartItemId, quantity);
    }

    public void removeItem(Long cartItemId) {
        cartItemService.removeItem(cartItemId);
    }

}
