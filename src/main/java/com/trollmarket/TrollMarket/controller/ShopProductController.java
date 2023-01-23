package com.trollmarket.TrollMarket.controller;

import com.trollmarket.TrollMarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shop")
public class ShopProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public String shop(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "", required = false) String name,
                       @RequestParam(defaultValue = "", required = false) String cat,
                       @RequestParam(defaultValue = "", required = false) String desc,
                       Model model){


        var grid = productService.findAllProductContinue(page,name,cat,desc);
        var totalPages = grid.getTotalPages();

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("products", grid);
        model.addAttribute("name",name);
        model.addAttribute("cat",cat);
        model.addAttribute("desc",desc);
//        model.addAttribute("cart", new CartDTO());
        return "shop/shop-index";
    }
    @GetMapping("/detail")
    public String detail(@RequestParam(required = true) Long id,
                         Model model){
        try {
            var grid = productService.getDetail(id);
            model.addAttribute("grid", grid);
            return "shop/product-detail";
        }catch (Exception exception){
            return "redirect:/error/serverError";
        }
    }

}
