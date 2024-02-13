package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CartRepository cartRepository;

    public CheckoutServiceImpl(CartRepository cartRepository, CustomerRepository customerRepository){
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Customer customer = purchase.getCustomer();

        Cart cart = purchase.getCart();

        cart.setCustomer(customer);
        cart.setPackage_price(cart.getPackage_price());
        cart.setParty_size(cart.getParty_size());

        Set<CartItem> cartItems = purchase.getCartItems();
        for (CartItem cartItem : cartItems) {
            cartItem.setCart(cart);
        }
        cart.setCartItem(cartItems);

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);
        cart.setStatus(StatusType.ordered);
        cartRepository.save(cart);



        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setOrderTrackingNumber(orderTrackingNumber);
        return purchaseResponse;
    }
    private String generateOrderTrackingNumber(){
        return UUID.randomUUID().toString();
    }
}
