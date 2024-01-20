package com.flz.service;

import com.flz.exception.ResourceNotFoundException;
import com.flz.model.Student;
import com.flz.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    /*

    getAllStudent
    getOneStudent
    createStudent
    deleteStudent
    updateOneStudent

    */

    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    public ResponseEntity<Student> getOneStudent(Long id) throws ResourceNotFoundException {
        //return studentRepository.findById(id).get();
        Student student=studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found ID: "+id));
        return ResponseEntity.ok().body(student);
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public Map<String,Boolean> deleteOneStudent(Long id) throws ResourceNotFoundException{
        Student student=studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found ID: "+ id));
        studentRepository.deleteById(id);
        Map<String,Boolean> response=new HashMap<>();
        response.put("Deleted ID: "+ id ,Boolean.TRUE);

        return response;
    }

    public ResponseEntity<Student> updateOneStudent(Student studentInfo) throws ResourceNotFoundException{
        Student student=studentRepository.findById(studentInfo.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found ID: "+studentInfo.getId()));
        return ResponseEntity.ok(studentRepository.save(student));

    }

}
