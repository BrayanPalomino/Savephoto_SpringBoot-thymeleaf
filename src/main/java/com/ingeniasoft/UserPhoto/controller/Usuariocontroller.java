package com.ingeniasoft.UserPhoto.controller;

import com.ingeniasoft.UserPhoto.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.ingeniasoft.UserPhoto.repository.IUsuariorepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class Usuariocontroller {

    @Autowired
    private IUsuariorepository iUsuariorepository;

    @GetMapping("/")
    public String form(Model model){
        model.addAttribute("usuarios", new Usuario());
        return "form";
    }

    @PostMapping("/")
    public String guardar(@RequestParam(name = "file", required = false) MultipartFile foto, Usuario usuario, RedirectAttributes flash){
        if(!foto.isEmpty()){
            String ruta = "C://Temp//uploads";
            try {
                byte[] bytes = foto.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//"+ foto.getOriginalFilename());
                Files.write(rutaAbsoluta, bytes);
                usuario.setFoto(foto.getOriginalFilename());


            }catch (Exception e){

            }

            iUsuariorepository.save(usuario);
            flash.addFlashAttribute("success", "Foto subida!!");

        }
        return "redirect:/";
    }

    @GetMapping("/listar")
        public  String listar(Model model){
        model.addAttribute("usuarios", iUsuariorepository.findAll());
        return "listar";

    }


}
