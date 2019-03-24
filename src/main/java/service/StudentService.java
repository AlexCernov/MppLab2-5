package service;

import domain.Student;
import domain.Problem;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class StudentService {
    private Repository<Long, Student> repository;

    public StudentService(Repository<Long, Student> repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) throws ValidatorException {
        repository.save(student);
    }

    public void deleteStudent(Long id) throws ValidatorException {
        repository.delete(id);
    }

    public void updateStudent(Student student) throws ValidatorException{
        repository.update(student);
    }

    public Set<Student> getAllStudents() {
        Iterable<Student> students = repository.findAll();
        return StreamSupport.stream(students.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Student> filterStudentsByName(String s) {
        Iterable<Student> students = repository.findAll();

        Set<Student> filteredStudents = StreamSupport.stream(students.spliterator(), false)
                 .filter(student -> student.getName().toString().contains(s)).collect(Collectors.toSet());
        return filteredStudents;
    }

    public Set<Student> filterStudentsByGroup(String g) {
        Iterable<Student> students = repository.findAll();

        Set<Student> filteredStudents = StreamSupport.stream(students.spliterator(), false)
                .filter(student -> student.getGroup().toString().contains(g)).collect(Collectors.toSet());

        return filteredStudents;

    }
}

