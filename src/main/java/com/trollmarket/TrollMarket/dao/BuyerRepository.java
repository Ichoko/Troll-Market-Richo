package com.trollmarket.TrollMarket.dao;

import com.trollmarket.TrollMarket.dto.profile.OrderDetailGridDTO;
import com.trollmarket.TrollMarket.dto.profile.ProfileGridDTO;
import com.trollmarket.TrollMarket.entity.Buyer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BuyerRepository extends JpaRepository<Buyer, String> {

    @Query(
            """
              SELECT b 
              FROM Buyer as b
              WHERE b.account.username = :username
            """
    )
    Buyer findByUsername(@Param("username") String username);

    @Query(
            """
            SELECT ordet 
            FROM OrderDetail AS ordet
            """
    )
    Page<OrderDetailGridDTO> findAllTransactionBuyer(Pageable pageable, @Param("id") String username);


    @Query("""
            SELECT new com.trollmarket.TrollMarket.dto.profile.ProfileGridDTO(
            byr.name,acc.role,byr.address,byr.balance
            )
            FROM Buyer as byr
            INNER JOIN byr.account as acc
            where acc.username = :username
            """)
    ProfileGridDTO findProfileByUsername(@Param("username")String username);

//    @Query("""
//            SELECT buy.id
//            FROM Buyer AS buy
//            WHERE buy.username = :username
//            """)
//    public Long getIdByUsername(@Param("username") String username);
}
