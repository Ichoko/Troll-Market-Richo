package com.trollmarket.TrollMarket.dao;

import com.trollmarket.TrollMarket.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,String> {
}
