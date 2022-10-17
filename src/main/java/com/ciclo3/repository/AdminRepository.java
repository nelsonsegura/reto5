package com.ciclo3.repository;

import com.ciclo3.model.Admin;
import com.ciclo3.repository.crud.AdminCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class AdminRepository {
    @Autowired
    private AdminCrudRepository adminCrudRepository;

    public List<Admin> getAllAdmin(){
        return (List<Admin>) adminCrudRepository.findAll();
    }
    public Optional<Admin> getAdminById(Integer id){
        return adminCrudRepository.findById(id);
    }
    public Admin saveAdmin(Admin a){
        return adminCrudRepository.save(a);
    }
    public void deleteAdmin(Admin a){
        adminCrudRepository.delete(a);
    }
}
