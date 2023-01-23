package com.trollmarket.TrollMarket.controller;

import com.trollmarket.TrollMarket.dto.shipper.InsertShipperDTO;
import com.trollmarket.TrollMarket.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/shipper")
public class ShipperController {
    @Autowired
    ShipperService shipperService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page , Model model){
        var grid =shipperService.findAllShipment(page);
        var totalPage = shipperService.findAllShipment(1).getTotalPages();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("shipper",grid);
        return "shipper/shipper-index";
    }
    //method untuk mengeluarkan form untuk nantinya diisi di VIEW(Get Request)
    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) Long id,Model model){
        try{
            InsertShipperDTO insertShipperDTO = new InsertShipperDTO();
            model.addAttribute("type","insert");
            if (id != null){
                insertShipperDTO = shipperService.getUpdate(id);
                model.addAttribute("type","update");
            }
            model.addAttribute("dto",insertShipperDTO);
            return "shipper/upsert-shipper";
        }catch (Exception exception){
            return "redirect:/error/serverError";
        }
    }
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("dto") InsertShipperDTO dto, BindingResult bindingResult, Model model){
        try {
            if(bindingResult.hasErrors()){
                return "shipper/upsert-shipper";// akan mengembalikan upsert kembali jika salah(sesuai validasi)
            }
            shipperService.save(dto);
            return "redirect:/shipper/index";
// harus jalankan reques(get mapping) dulu sebelum ke index supaya data diolah dan baru diarahkan ke view agar data tidak kosng
        }catch (Exception exception){
            return "redirect:/error/serverError";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            shipperService.delete(id);
            return "redirect:/shipper/index";
        } catch (Exception exception) {
            String errorText = String.format("Jenis Exception: %s", exception.getCause().getCause());
            redirectAttributes.addAttribute("message", errorText);
            return "redirect:/error/serverError";
        }
    }
    @GetMapping("/stopService")
    public String service(@RequestParam Long id){
        shipperService.stopService(id);
        return "redirect:/shipper/index";
    }
}
