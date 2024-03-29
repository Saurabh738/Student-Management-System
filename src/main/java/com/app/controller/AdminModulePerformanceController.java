package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.app.DAO.ModulePerformanceDAO;
import com.app.DAO.SubjectDAO;
import com.app.helper.Helper;
import com.app.model.ModulePerformance;
import com.app.model.Subject;
import com.app.service.AdminModulePerformanceService;

@RestController
@CrossOrigin("*")
public class AdminModulePerformanceController {
	@Autowired
    private AdminModulePerformanceService modulepPerformanceService;
	
	@Autowired
	private ModulePerformanceDAO modulePerformanceDAO;
	
	@Autowired
	private SubjectDAO subjectDAO;

    @PostMapping("/admin/moduleperformance/upload")
    public ResponseEntity<?> upload(@RequestParam("subject_id") int subject_id ,@RequestParam("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            //true
            this.modulepPerformanceService.save(subject_id,file);
            return ResponseEntity.status(HttpStatus.OK).body("Uploaded the file successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }


    @GetMapping("/admin/moduleperformance/{subject_id}")
    public List<ModulePerformance> getAllModulePerformance(@PathVariable int subject_id) {
        return this.modulepPerformanceService.getAllData(subject_id);
    }
    
    @GetMapping("/admin/getonemoduleperformance/{pm_id}")
    public ModulePerformance getOneModulePerformance(@PathVariable int pm_id) {
        return this.modulepPerformanceService.getOneData(pm_id);
    }
    
    
    @PutMapping("/admin/moduleperformance/update/{pm_id}")
	public ResponseEntity<?> updatemoduleperformance(@PathVariable int pm_id,@RequestParam("prn") long prn,@RequestParam("lab_exam") int lab_exam,@RequestParam("internal") int internal,@RequestParam("ccee") int ccee)
	{
		return modulepPerformanceService.updatemoduleperformance(pm_id,prn,lab_exam,internal,ccee);
	}
    
    @GetMapping("/admin/moduleperformance")
    public List<ModulePerformance> getAllModulePerformanceData() {
        return this.modulePerformanceDAO.findAll();
    }
    
//    @PostMapping("/admin/moduleperformance/updatedata")
//    public ResponseEntity<?> update(@RequestParam("subject_id") int subject_id ,@RequestParam("file") MultipartFile file) {
//        if (Helper.checkExcelFormat(file)) {
//            //true
//            this.modulepPerformanceService.save(subject_id,file);
//            return ResponseEntity.status(HttpStatus.OK).body("Uploaded the file successfully");
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
//    }
    
//    @DeleteMapping("/admin/deletemoduleperformance/{pm_id}")
//    public ResponseEntity<?> deleteMP(@PathVariable("pm_id") int pm_id) {
//		// TODO Auto-generated method stub        
//		if(modulePerformanceDAO.existsById(pm_id)) {
//			modulePerformanceDAO.deleteById(pm_id);
//			return ResponseEntity.status(HttpStatus.OK).body("Module Performance deleted succesfully");
//		}
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Module Performance deleted failed");
//	}
    
    @DeleteMapping("/admin/deletemoduleperformance/{subject_id}")
    public ResponseEntity<?> deleteMP(@PathVariable("subject_id") int subject_id) {
    	Subject s = subjectDAO.findBySubjectId(subject_id);
    	List<ModulePerformance> m = modulePerformanceDAO.findBySubject(s);
    	if(m!=null)
    	{
    		modulePerformanceDAO.deleteAll(m);
    		return ResponseEntity.status(HttpStatus.OK).body("Module Performance deleted succesfully");
    	}
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Module Performance deleted failed");
	}
}