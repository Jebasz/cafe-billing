package com.star.cafe_billing.config;

import com.star.cafe_billing.entity.Role;
import com.star.cafe_billing.entity.Shop;
import com.star.cafe_billing.entity.User;
import com.star.cafe_billing.repository.ShopRepository;
import com.star.cafe_billing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StartupInitializer implements CommandLineRunner {

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {

        // Create default shop
        Shop shop;

       if(shopRepository.count() == 0){

            shop = Shop.builder()
                    .shopName("Main Coffee Shop")
                    .address("Default Address")
                    .phone("0000000000")
                    .build();

            shopRepository.save(shop);

        }else{

            shop = shopRepository.findAll().get(0);
        }


        // Create default admin
        if(userRepository.findByUsername("admin").isEmpty()){

            User admin = User.builder()
                    .name("Jebastin")
                    .username("JebastinK")
                    .password("Cafe@123")
                    .role(Role.ADMIN)
                    .shop(shop)
                    .build();

            userRepository.save(admin);

            System.out.println("DEFAULT ADMIN CREATED");

        }

    }
}