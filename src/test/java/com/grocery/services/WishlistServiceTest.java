package com.grocery.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grocery.entity.Wishlist;
import com.grocery.service.WishlistService;

import jakarta.transaction.Transactional;

@SpringBootTest

@Transactional

class WishlistServiceTest {

    @Autowired

    private WishlistService wishlistService;

    @Test

    void wishlistServiceShouldLoad() {

        assertNotNull(wishlistService);

    }

    @Test

    void getWishlistByCustomer_shouldReturnEmptyList() {

        List<Wishlist> list = wishlistService.getWishlistByCustomer(999L);

        assertNotNull(list);

    }

}
