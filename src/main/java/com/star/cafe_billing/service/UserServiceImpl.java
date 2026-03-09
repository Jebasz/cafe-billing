package com.star.cafe_billing.service;


import com.star.cafe_billing.entity.User;
import com.star.cafe_billing.repository.ShopRepository;
import com.star.cafe_billing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;

    @Override
    public User createUser(User user) {

        shopRepository.findById(user.getShop().getId())
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {

        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setName(user.getName());
        existing.setUsername(user.getUsername());
        existing.setRole(user.getRole());

        return userRepository.save(existing);
    }

    @Override
    public List<User> getUsersByShop(Long shopId) {

        return userRepository.findByShopId(shopId);

    }

    @Override
    public void disableUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(false);

        userRepository.save(user);
    }
}