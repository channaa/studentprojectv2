package com.naveen.studentprojectv2.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.naveen.studentprojectv2.studentexception.DataNotFoundException;
import com.naveen.studentprojectv2.studentexception.UnableToDeleteException;
import com.naveen.studentprojectv2.studentexception.UnableToUpdateException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService  {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    //To read a proprty file from a path and load it to properties object

    public List<Student> getStudentDetails() {
        return studentRepository.saveAll(List.of(new Student(
                1L,
                "Naveen Kumar",
                "naveenkumar.chandrasekhar@gmail.com",
                LocalDate.of(1991, Month.JANUARY, 29)
        )));

    }

    public Student getStudentDetailsbyId(Long id) {
        Optional<Student> studentById = studentRepository.findStudentById(id);
        if(studentById.isPresent()){
            return studentById.get();
        }
        else{
            throw new DataNotFoundException();
        }
    }

    public String addNewStudentDetails(Student student) {

        Optional<Student> studentByEmail=studentRepository.findStudentByEmail(student.getEmail());
        if(!studentByEmail.isPresent()){
             studentRepository.save(student);
             return "student details has been added";
         }else{
             throw new UnableToUpdateException();
         }

    }

    public String updateStudentDetails(Student student) {

        Optional<Student> studentById=studentRepository.findStudentById(student.getId());
        if(studentById.isPresent()){
            student.setName(student.getName());
            studentRepository.save(student);
            return "student details updated successfully";
        }else{
            throw new UnableToUpdateException();
        }
    }

    public String deleteStudentDetailsById(Long id) {
        Optional<Student> studentById = studentRepository.findStudentById(id);
        if(studentById.isPresent()){
            studentRepository.delete(studentById.get());
            return "student details deleted successfully";
        }else{
            throw new UnableToDeleteException();
        }

    }
}
