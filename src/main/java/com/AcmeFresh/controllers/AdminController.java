package com.AcmeFresh.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AcmeFresh.DTO.LoginDTO;
import com.AcmeFresh.DTO.RegisteringDTO;
import com.AcmeFresh.modelEntity.AcmeFreshProduce;
import com.AcmeFresh.modelEntity.Admin;
import com.AcmeFresh.modelEntity.AdminSession;
import com.AcmeFresh.services.serviceInterfaces.AdminService;
import com.AcmeFresh.services.serviceInterfaces.LoginService;


@RestController
@RequestMapping("/homepage/admin")
public class AdminController {

	@Autowired
	private LoginService loginService;

	@Autowired
	AdminService adminService;

	@PostMapping("/login")
	public ResponseEntity<AdminSession> loginAdmin(@RequestBody LoginDTO dto) {
		return new ResponseEntity<>(loginService.loginAdmin(dto), HttpStatus.ACCEPTED);
	}

	@PostMapping("/register")
	public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin) {
		return new ResponseEntity<>(adminService.adminRegister(admin), HttpStatus.ACCEPTED);

	}

	@PatchMapping("/update/{username}")
	public ResponseEntity<Admin> updateAdminPassword(@Valid @RequestBody LoginDTO dto, @PathVariable("username") String username,
			@RequestParam String key) {
		return new ResponseEntity<>(adminService.updatePassword(dto, username, key), HttpStatus.ACCEPTED);
	}

	@PutMapping("/update/{username}")
	public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin, @PathVariable("username") String username,
			@RequestParam String key) {
		return new ResponseEntity<>(adminService.update(admin, username, key), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteaccount")
	public ResponseEntity<String> deleteAdmin(@Valid @RequestBody LoginDTO dto, @RequestParam String key) {
		return new ResponseEntity<>(adminService.deleteByUsername(dto, key), HttpStatus.ACCEPTED);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logoutAdmin(@RequestParam String key) {
		return new ResponseEntity<>(adminService.logoutAdmin(key), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/addnewproduct")
	public ResponseEntity<String> addNewProduct(@Valid @RequestBody AcmeFreshProduce produce, String key) {
		return new ResponseEntity<>(adminService.addInhouseProducts(produce, key), HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/updatepriceofproduct/{id}")
	public ResponseEntity<String> updatePriceByProductId(@RequestParam Double price , @PathVariable("id") Integer id,
			@RequestParam String key) {
		return new ResponseEntity<>(adminService.updatePriceOfProductById(id, price, key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateproduct")
	public ResponseEntity<String> updateProduct(@Valid @RequestBody AcmeFreshProduce produce, @RequestParam String key) {
		return new ResponseEntity<>(adminService.updateProduct(produce, key), HttpStatus.ACCEPTED);
	}
	
	

	

}
