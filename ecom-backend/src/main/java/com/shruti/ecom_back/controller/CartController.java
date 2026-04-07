package com.shruti.ecom_back.controller;

import com.shruti.ecom_back.dto.CartResponse;
import com.shruti.ecom_back.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartResponse getCart() {
        return cartService.getCart();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long productId,
                                            @RequestParam int quantity) {

        cartService.addToCart(productId, quantity);
        return ResponseEntity.ok("Product added to cart");
    }

    @PutMapping("/update")
    public String updateQuantity(@RequestParam Long cartItemId,
                                 @RequestParam int quantity) {

        cartService.updateQuantity(cartItemId, quantity);
        return "Cart updated";
    }

    @DeleteMapping("/remove/{cartItemId}")
    public String removeItem(@PathVariable Long cartItemId) {

        cartService.removeItem(cartItemId);
        return "Item removed from cart";
    }

    @DeleteMapping("/clear")
    public String clearCart() {

        cartService.clearCart();
        return "Cart cleared";
    }

}