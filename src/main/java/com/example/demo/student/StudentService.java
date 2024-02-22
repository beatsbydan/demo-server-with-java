package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    public void addNewStudent(Student student){
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email is taken.");
        }
        studentRepository.save(student);
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email){
         Student currentStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student does not exist"));

        if(name != null && name.isEmpty() && !Objects.equals(currentStudent.getName(), name)){
            currentStudent.setName(name);
        }
        if(email != null && email.isEmpty() && !Objects.equals(currentStudent.getEmail(), email)){
            Optional<Student> studentWithCurrentEmail = studentRepository.findStudentByEmail(email);
            if(studentWithCurrentEmail.isPresent()){
                throw new IllegalStateException("Email is taken,");
            }
            currentStudent.setEmail(email);
        }
    }
    public void deleteStudent(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);
        if(!studentExists){
            throw new IllegalStateException("Student does not exist");
        }
        studentRepository.deleteById(studentId);
    }
}
