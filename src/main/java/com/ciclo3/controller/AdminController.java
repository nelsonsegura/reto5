package com.ciclo3.controller;

import com.ciclo3.model.Admin;
import com.ciclo3.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Admin")
@CrossOrigin(origins = "*")

public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public List<Admin> getAllAdmin(){
        return (List<Admin>) adminService.getAllAdmin();
    }
    @GetMapping("/{id}")
    public Optional<Admin> getAdminById(@PathVariable Integer id){
        return adminService.getAdminById(id);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin saveAdmin(@RequestBody Admin a){
        return adminService.saveAdmin(a);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED )
    public Admin updateAdmin(@RequestBody Admin a) {
        return adminService.updateAdmin(a);
    };

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteAdmin(@PathVariable Integer id){
        return adminService.deleteAdmin(id);
    }
}
