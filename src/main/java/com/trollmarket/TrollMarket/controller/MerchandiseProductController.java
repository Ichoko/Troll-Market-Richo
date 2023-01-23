package com.trollmarket.TrollMarket.controller;

import com.trollmarket.TrollMarket.dto.product.ProductGridDTO;
import com.trollmarket.TrollMarket.dto.shipper.InsertShipperDTO;
import com.trollmarket.TrollMarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/merchandise")
public class MerchandiseProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/index")
    public String merchandise(@RequestParam(defaultValue = "1") Integer page,
                              Model model, Authentication authentication){

        Page<ProductGridDTO> ProductGridDTO = productService.findAllProductBySeller(page, authentication.getName());
        int totalPage = ProductGridDTO.getTotalPages();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("dto",ProductGridDTO);
        return "merchandise/merchandise-index";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) Long id,Model model){
        try{

            ProductGridDTO productGridDTO = new ProductGridDTO();

            model.addAttribute("type","insert");
            if (id != null){
                productGridDTO = productService.getUpdate(id);
                model.addAttribute("type","update");
            }
            model.addAttribute("dto",productGridDTO);
            return "merchandise/upsert-product";
        }catch (Exception exception){
            return "redirect:/error/serverError";
        }
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("dto") ProductGridDTO dto, BindingResult bindingResult, Model model){
//        try {
            if(bindingResult.hasErrors()){
                return "merchandise/upsert-product";// akan mengembalikan upsert kembali jika salah(sesuai validasi)
            }
            productService.save(dto);
            return "redirect:/merchandise/index";
// harus jalankan reques(get mapping) dulu sebelum ke index supaya data diolah dan baru diarahkan ke view agar data tidak kosng
//        }catch (Exception exception){
//            return "redirect:/error/serverError";
//        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            productService.delete(id);
            return "redirect:/merchandise/index";
        } catch (Exception exception) {
            String errorText = String.format("Jenis Exception: %s", exception.getCause().getCause());
            redirectAttributes.addAttribute("message", errorText);
            return "redirect:/error/serverError";
        }
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(required = true) Long id,
                         Model model){
        try {
            var grid = productService.getDetail(id);
            model.addAttribute("grid", grid);
            return "merchandise/product-detail";
        }catch (Exception exception){
            return "redirect:/error/serverError";
        }
    }
    @GetMapping("/discontinue")
    public String service(@RequestParam Long id){
        productService.discontinue(id);
        return "redirect:/merchandise/index";
    }
}
