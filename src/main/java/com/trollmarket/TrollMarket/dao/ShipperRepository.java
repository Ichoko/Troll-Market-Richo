package com.trollmarket.TrollMarket.dao;

import com.trollmarket.TrollMarket.dto.shipper.ShipperGridDTO;
import com.trollmarket.TrollMarket.entity.Shipper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipperRepository extends JpaRepository<Shipper,Long> {

//    @Query("""
//            SELECT new com.trollmarket.TrollMarket.dto.shipper.ShipperGridDTO(
//            s.id,s.name,s.price,s.service,COUNT(od.id))
//            FROM OrderDetail AS od
//                FULL JOIN od.shipment as s
//            GROUP BY s.id,s.name,s.price,s.service
//            """)
//    Page<ShipperGridDTO> findAllShipment(Pageable pageable);

    @Query("""
            SELECT new com.trollmarket.TrollMarket.dto.shipper.ShipperGridDTO(
                        ship.id,
                        ship.name,
                        ship.price,
                        CASE WHEN ship.service = 1 THEN 'YES' ELSE 'NO' END
                        )
            FROM Shipper AS ship
            """)
    Page<ShipperGridDTO> findShipper(Pageable pageable);

//    @Query(
//            """
//            SELECT *
//            FROM Shipment
//            WHERE service = 1
//            """
//    )
//    List<Shipper> findAllShipmentService();

}
