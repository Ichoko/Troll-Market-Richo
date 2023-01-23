package com.trollmarket.TrollMarket.service;

import com.trollmarket.TrollMarket.dao.ProductRepository;
import com.trollmarket.TrollMarket.dto.product.ProductGridDTO;
import com.trollmarket.TrollMarket.dto.shipper.InsertShipperDTO;
import com.trollmarket.TrollMarket.entity.Product;
import com.trollmarket.TrollMarket.entity.Shipper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Page<ProductGridDTO> findAllProductContinue(int page, String name, String cat, String desc) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id"));
        return productRepository.findAllProductContinue(pageable, name, cat, desc);
    }

    public ProductGridDTO getDetail(Long id){
        var hasilGrid = productRepository.getOneDetail(id);
        return hasilGrid;
    }

    public Page<ProductGridDTO> findAllProductBySeller(Integer page,String sellerId) {
        Pageable pageable = PageRequest.of(page - 1,10,Sort.by("id"));
        return productRepository.findAllProductBySeller(sellerId,pageable);
    }

    public ProductGridDTO getUpdate(Long id){
        Product entity = productRepository.findById(id).get();
        ProductGridDTO productGridDTO = new ProductGridDTO(
                entity.getId(),
                entity.getSellerId(),
                entity.getName(),
                entity.getCategoryName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getDiscontinue()
        );
        return productGridDTO;
    }

    public Product save(ProductGridDTO dto) {
        Product product = new Product(
                dto.getId(),
                dto.getSellerId(),
                dto.getName(),
                dto.getCategory(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getDiscontinue()
        );
        var newProduct = productRepository.save(product);
        return newProduct;
    }

    public void delete (Long id){
        productRepository.deleteById(id);
    }

    public void discontinue(Long id) {
        Product product = productRepository.findById(id).get();
        product.setDiscontinue(true);
        productRepository.save(product);
    }
  }
