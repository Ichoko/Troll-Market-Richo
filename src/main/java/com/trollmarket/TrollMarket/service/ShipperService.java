package com.trollmarket.TrollMarket.service;

import com.trollmarket.TrollMarket.dao.ShipperRepository;
import com.trollmarket.TrollMarket.dto.shipper.InsertShipperDTO;
import com.trollmarket.TrollMarket.dto.shipper.ShipperGridDTO;
import com.trollmarket.TrollMarket.entity.Shipper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ShipperService {
    @Autowired
    ShipperRepository shipperRepository;

    public Page<ShipperGridDTO> findAllShipment(Integer page) {
        var pageable = PageRequest.of(page - 1, 10, Sort.by("id"));
        var hasilGrid = shipperRepository.findShipper(pageable);
        return hasilGrid;
    }

    public Shipper save(InsertShipperDTO dto) {
        Shipper shipper = new Shipper(
                dto.getId(),
                dto.getName(),
                dto.getPrice(),
                dto.getService()
        );
        var shipperBaru = shipperRepository.save(shipper);
        return shipperBaru;
    }

    public InsertShipperDTO getUpdate(Long id){
        Shipper entity = shipperRepository.findById(id).get();
        InsertShipperDTO insertShipperDTO = new InsertShipperDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getService()
        );
        return insertShipperDTO;
    }
    public void delete (Long id){
        shipperRepository.deleteById(id);
    }
    public void stopService(Long id) {
        Shipper shipper = shipperRepository.findById(id).get();
        shipper.setService(false);
        shipperRepository.save(shipper);
    }
}
