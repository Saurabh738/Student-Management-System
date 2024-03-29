package com.app.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.DAO.AdminProfileDao;
import com.app.helper.HealperAdminProfile;
import com.app.model.Admin;




@Transactional
@Service
public class AdminProfileService {

	@Autowired
	private AdminProfileDao adminProfileDao;
	
	
	//Excel to db
	public void save(MultipartFile file) {

        try {
            List<Admin> admin = HealperAdminProfile.convertExcelToListOfAdmins(file.getInputStream());
            this.adminProfileDao.saveAll(admin);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
	
	
	//List of all Admins
	public List<Admin> getAll(){
		return adminProfileDao.findAll();
	}
	
	
	
	//Registration of new admin
//	public AdminStatus insertAdmin(Admin insertedadmin)
//	{
//		AdminStatus adminStatus = new AdminStatus(0,"Insert failed",insertedadmin);
//		if(!adminProfileDao.existsById(insertedadmin.getAdminId()))
//		{
//			adminProfileDao.save(insertedadmin);
//			adminStatus.setStatuscode(1);
//			adminStatus.setMsg("Insert Successfull");
//		}
//		return adminStatus;
//	}
	
	public ResponseEntity<?> insertAdmin(Admin insertedadmin)
	{

		if(!adminProfileDao.existsById(insertedadmin.getAdminId()))
		{
			adminProfileDao.save(insertedadmin);	
			return ResponseEntity.status(HttpStatus.OK).body("Inserted successfully");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insert Failed");
	}
	
	//update admin
//	public AdminStatus updateAdmin(Admin updatedadmin)
//	{
//		AdminStatus adminStatus = new AdminStatus(0,"Update Failed",updatedadmin);
//		if(adminProfileDao.existsById(updatedadmin.getAdminId()))
//		{
//			adminProfileDao.save(updatedadmin);
//			adminStatus.setStatuscode(1);
//			adminStatus.setMsg("update Succesfull");
//		}
//		return adminStatus;
//	}
	
	public ResponseEntity<?> updateAdmin(int aid,String aname,String arole,String aemail)
	{

		if(adminProfileDao.existsById(aid))
		{
			Admin a = adminProfileDao.findByadminId(aid);
			a.setAdminName(aname);
			a.setRole(arole);
			a.setEmail(aemail);
			adminProfileDao.save(a);	
			return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Upadate Failed");
	}
//	
//	
//	
	//Forget PAssword
	public ResponseEntity<?> updateAdminPassword(int aid,String apass)
	{

		if(adminProfileDao.existsById(aid))
		{
			Admin a = adminProfileDao.findByadminId(aid);
			a.setPassword(apass);
			adminProfileDao.save(a);	
			return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Upadate Failed");
	}
}
