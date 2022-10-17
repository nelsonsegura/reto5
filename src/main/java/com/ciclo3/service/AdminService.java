package com.ciclo3.service;

import com.ciclo3.model.Admin;
import com.ciclo3.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmin() {
        return (List<Admin>) adminRepository.getAllAdmin();
    }

    public Optional<Admin> getAdminById(Integer id) {
        return adminRepository.getAdminById(id);
    }

    public Admin saveAdmin(Admin a) {
        if (a.getId() == null) {
            return adminRepository.saveAdmin(a);
        } else {
            Optional<Admin> ad = adminRepository.getAdminById(a.getId());
            if (ad.isEmpty()) {
                return adminRepository.saveAdmin(a);
            } else {
                return a;
            }
        }
    }

    public Admin updateAdmin(Admin admin) {
        if (admin.getId() != null) {
            Optional<Admin> e = adminRepository.getAdminById(admin.getId());
            if (!e.isEmpty()) {
                if (admin.getName()!= null) {
                    e.get().setName(admin.getName());
                }
                if (admin.getEmail()!= null) {
                    e.get().setEmail(admin.getEmail());
                }
                if (admin.getPassword()!= null) {
                    e.get().setPassword(admin.getPassword());
                }

                adminRepository.saveAdmin(e.get());
                return e.get();
            }
        }
        return admin;
    }

    public boolean deleteAdmin(Integer id) {
        Boolean d = getAdminById(id).map(admin -> {
            adminRepository.deleteAdmin(admin);
            return true;
        }).orElse(false);
        return d;
    }
}
