package com.example.springbootdemo;
// 🧠 Keeps this class in the same package as others so Spring Boot can find it easily.

import org.springframework.beans.factory.annotation.Autowired;
// 🧠 Lets Spring automatically connect (inject) the Repository into this class.

import org.springframework.stereotype.Service;
// 🧠 Marks this class as a "Service" — it handles business logic between controller and repository.

import java.util.List;
import java.util.Optional;
// 🧠 'List' is used for returning multiple students.
// 🧠 'Optional' helps safely handle "maybe student found, maybe not" cases.

@Service
// 🧠 Tells Spring Boot this is a service class (a business logic layer).

public class StudentService {

    @Autowired  
    private StudentRepository studentRepository;
    // 🧠 @Autowired = dependency injection.
    // Spring Boot automatically creates a StudentRepository object here.
    // You don’t use 'new' keyword — Spring manages it for you.

    // CREATE 🟢
    public Student addStudent(Student student) {
        return studentRepository.save(student);
        // 🧠 save() is a built-in method from JpaRepository.
        // It automatically does: INSERT INTO students (...) VALUES (...)
    }

    // READ ALL 🟣
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
        // 🧠 findAll() runs: SELECT * FROM students;
    }

    // READ ONE 🟣
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
        // 🧠 findById() runs: SELECT * FROM students WHERE id = ?;
        // Optional helps handle “student not found” cases safely.
    }

    // UPDATE 🟡
    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                    student.setCourse(updatedStudent.getCourse());
                    return studentRepository.save(student);
                    // 🧠 If student exists, update fields and save again.
                    // save() here runs: UPDATE students SET ... WHERE id = ?
                })
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // DELETE 🔴
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
        // 🧠 deleteById() runs: DELETE FROM students WHERE id = ?;
    }
}
