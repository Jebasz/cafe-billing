package com.star.cafe_billing.repository;

import com.star.cafe_billing.entity.PriceFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PriceFilterRepository extends JpaRepository<PriceFilter, Long> {

    List<PriceFilter> findByShopId(Long shopId);

}
