package com.example.backend.web;

import com.example.backend.models.DTOs.UserDetailsView;
import com.example.backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("*")
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PatchMapping(value = "/promote")
    @PreAuthorize("hasAuthority('SCOPE_OWNER')")
    public ResponseEntity<?> promoteMember(@RequestParam long userId) {
        this.adminService.promote(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/demote")
    @PreAuthorize("hasAuthority('SCOPE_OWNER')")
    public ResponseEntity<?> demoteAdmin(@RequestParam long adminId) {
        this.adminService.demote(adminId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDetailsView>> getAllAdmins() {
        List<UserDetailsView> allAdmins = this.adminService.getAllAdmins();
        return ResponseEntity.ok(allAdmins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsView> getParticularAdmin(@PathVariable(name = "id") long adminId) {
        UserDetailsView admin = this.adminService.getParticularAdmin(adminId);
        return ResponseEntity.ok(admin);
    }
}
