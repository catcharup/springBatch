package com.spring.batch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.batch.model.StudentDTO;

@RestController
public class DummyRestService {
	
	@RequestMapping(path="/students",produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
	public List<StudentDTO> getStudentDetails(){
		List<StudentDTO> studentList = new ArrayList<StudentDTO>();
		studentList.add(new StudentDTO("test@gmail.com", "Arup", "Btech"));
		studentList.add(new StudentDTO("test2@gmail.com", "Nilabja", "B.ED"));
		studentList.add(new StudentDTO("test3@gmail.com", "Chimbi", "Primary"));
		return studentList;
	}

}
