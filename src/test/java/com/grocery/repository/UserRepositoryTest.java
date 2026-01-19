package com.grocery.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.grocery.entity.User;

@DataJpaTest

class UserRepositoryTest {

    @Autowired

    private UserRepository userRepository;

    @Test

    void shouldSaveAndFindUserByEmail() {

        User user = new User();

        user.setName("Test User");

        user.setEmail("test@gmail.com");

        user.setPassword("12345");

        user.setPhone("9999999999");

        user.setRole("CUSTOMER");

        user.setStatus("ACTIVE");

        userRepository.save(user);

        Optional<User> foundUser =

                userRepository.findByEmail("test@gmail.com");

        assertThat(foundUser).isPresent();

        assertThat(foundUser.get().getEmail())

                .isEqualTo("test@gmail.com");

    }

    @Test

    void shouldReturnEmptyWhenEmailNotExists() {

        Optional<User> user =

                userRepository.findByEmail("notfound@gmail.com");

        assertThat(user).isEmpty();

    }

}