package com.shruti.ecom_back.service;

import com.shruti.ecom_back.dto.CartItemResponse;
import com.shruti.ecom_back.exception.BadRequestException;
import com.shruti.ecom_back.model.Cart;
import com.shruti.ecom_back.model.CartItem;
import com.shruti.ecom_back.model.Product;
import com.shruti.ecom_back.repository.CartItemRepository;
import com.shruti.ecom_back.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public void addToCart(Cart cart, Long productId, int quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException("Product not found"));


        for (CartItem existingItem : cart.getItems()) {
            if (existingItem.getProduct().getId().equals(productId)) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                cartItemRepository.save(existingItem);
                return;
            }
        }

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);


        cart.getItems().add(item);

        cartItemRepository.save(item);
    }

    // Convert CartItems to DTOs
    public List<CartItemResponse> getCartItems(Cart cart) {

        return cart.getItems()
                .stream()
                .map(item -> CartItemResponse.builder()
                        .cartItemId(item.getId())
                        .productId(item.getProduct().getId())
                        .productTitle(item.getProduct().getTitle())
                        .imageUrl(item.getProduct().getImageUrl())
                        .price(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .build())
                .toList();
    }

    // Update quantity
    public void updateQuantity(Long cartItemId, int quantity) {

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new BadRequestException("Cart item not found"));

        item.setQuantity(quantity);

        cartItemRepository.save(item);
    }


    public void removeItem(Long cartItemId) {

        cartItemRepository.deleteById(cartItemId);
    }

}
