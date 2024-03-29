package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Student;
import com.app.service.StudentService;


@CrossOrigin(origins="*")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	//Single select
	@GetMapping("/student/{prn}")
	public Optional<Student> getStudentDetials(@PathVariable long prn)
	{
		return studentService.SingleSelect(prn);
	}
	
	//Update Information
	@PutMapping("/student/update/{prn}")
	public ResponseEntity<?> UpdStudent(@PathVariable long prn,@RequestParam("email") String email,@RequestParam("address") String address,@RequestParam("mobile") long mobile)
	{
		return studentService.updateStudent(prn,email,address,mobile);
	}
	
//	@PostMapping("/ins")
//	public StudentStatus insBook(@RequestBody Student insertStudent)
//	{
//		return studentService.insertBook(insertStudent);
//	}
//	
//	@DeleteMapping("/del")
//	public StudentStatus delBook(@RequestParam long id)
//	{
//		return studentService.deleteStudent(id);
//	}
	
	
}
