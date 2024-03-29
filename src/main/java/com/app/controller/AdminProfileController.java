package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.helper.HealperAdminProfile;
import com.app.model.Admin;
import com.app.service.AdminProfileService;



@CrossOrigin(origins="*")
@RestController
public class AdminProfileController {

	@Autowired
	private AdminProfileService adminProfileService;
	
	//Excel to Database
    @PostMapping("/admin/adminDeatils/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (HealperAdminProfile.checkExcelFormat(file)) {
            //true

            this.adminProfileService.save(file);

            return ResponseEntity.status(HttpStatus.OK).body("Uploaded the file successfully");


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }
	
	
	
	
		//Single select
		@GetMapping("/admins")
		public List<Admin> multiselect()
		{
			return adminProfileService.getAll();
		}
		
		//Insert New Admin
		@PostMapping("/admin/insertadmin")
		public ResponseEntity<?> insertAdmin(@RequestBody Admin insertAdmin)
		{
			return adminProfileService.insertAdmin(insertAdmin);
		}
		
		
		//Update Information
		@PutMapping("/admin/update/{aid}")
		public ResponseEntity<?> UpdStudent(@PathVariable int aid,@RequestParam("aname") String aname,@RequestParam("arole") String arole,@RequestParam("aemail") String aemail)
		{
			return adminProfileService.updateAdmin(aid,aname,arole,aemail);
		}
	
		//Forget Password
		@PutMapping("/admin/updpassword/{aid}")
		public ResponseEntity<?> UpdStudentPass(@PathVariable int aid,@RequestParam("apass") String apass)
		{
			return adminProfileService.updateAdminPassword(aid,apass);
		}
		

}
