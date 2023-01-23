package com.trollmarket.TrollMarket.dao;

import com.trollmarket.TrollMarket.dto.product.ProductGridDTO;
import com.trollmarket.TrollMarket.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("""
            SELECT new com.trollmarket.TrollMarket.dto.product.ProductGridDTO(
            pro.id, pro.sellerId,pro.name,pro.categoryName,pro.description,pro.price,pro.discontinue
            )
            FROM Product as pro WHERE pro.discontinue = 0
            AND pro.name LIKE %:name% AND pro.categoryName LIKE %:category% AND pro.description LIKE %:desc%
          """
    )
    Page<ProductGridDTO> findAllProductContinue(Pageable pageable,
                                                @Param("name") String name,
                                                @Param("category") String category,
                                                @Param("desc") String desc);


    @Query("""
            SELECT new com.trollmarket.TrollMarket.dto.product.ProductGridDTO(
            pro.id, pro.sellerId,pro.name,pro.categoryName,pro.description,pro.price,pro.discontinue
            )
            FROM Product as pro WHERE pro.discontinue = 0 AND
            pro.id = :id
          """
    )
    public ProductGridDTO getOneDetail(@Param("id") Long id);
//
//
    @Query("""
            SELECT new com.trollmarket.TrollMarket.dto.product.ProductGridDTO(
            pro.id, pro.sellerId,pro.name,pro.categoryName,pro.description,pro.price,pro.discontinue)
            FROM Product as pro
            WHERE pro.sellerId = :username
            """)
    Page<ProductGridDTO> findAllProductBySeller(@Param("username") String username,
                                         Pageable pageable);
}
