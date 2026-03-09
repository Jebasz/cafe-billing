package com.star.cafe_billing.service;

import com.star.cafe_billing.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User updateUser(Long id, User user);

    List<User> getUsersByShop(Long shopId);

    void disableUser(Long id);

}