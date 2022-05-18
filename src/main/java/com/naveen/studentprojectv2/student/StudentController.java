package com.naveen.studentprojectv2.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudentDetails() {
        return studentService.getStudentDetails();
    }
    @GetMapping("/{studentId}")
    public Student getStudentDetailsById
            (@PathVariable Long studentId) {
        return studentService.getStudentDetailsbyId(studentId);
    }

    @PostMapping("/createstudent")
    public String addNewStudents(@RequestBody Student student) {

        return studentService.addNewStudentDetails(student);
    }
    @PutMapping ("/updatestudent")
    public String updateStudents(@RequestBody Student student) {

        return studentService.updateStudentDetails(student);
    }
    @DeleteMapping("/deletestudent/{studentId}")
    public String deleteStudentById
            (@PathVariable Long studentId){
        return studentService.deleteStudentDetailsById(studentId);
    }
    //Calling External APIs using RestTemplate
    @GetMapping("/learn")
    public String accessGoogleMaps(){
        String url="https://www.w3schools.com/";
        RestTemplate restTemplate=new RestTemplate();
        String result=restTemplate.getForObject(url,String.class);
        return result;
    }

}
