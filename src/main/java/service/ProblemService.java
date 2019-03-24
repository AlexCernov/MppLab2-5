package service;

import domain.Problem;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class ProblemService {
    private Repository<Long,Problem> repository;

    public ProblemService(Repository<Long, Problem> repository) {
        this.repository = repository;
    }

    public void addProblem(Problem problem) throws ValidatorException {
        repository.save(problem);
    }

    public void deleteProblem(Long id) throws ValidatorException {
        repository.delete(id);
    }

    public void updateProblem(Problem problem) throws ValidatorException{
        repository.update(problem);
    }

    public Set<Problem> getAllProblems() {
        Iterable<Problem> students = repository.findAll();
        return StreamSupport.stream(students.spliterator(), false).collect(Collectors.toSet());
    }
/*
    public Set<Problem> filterStudentsByName(String s) {
        Iterable<Problem> students = repository.findAll();

        Set<Problem> filteredStudents = StreamSupport.stream(students.spliterator(), false)
                .filter(student -> student.getName().toString().contains(s)).collect(Collectors.toSet());
        return filteredStudents;
    }

    public Set<Student> filterStudentsByGroup(String g) {
        Iterable<Student> students = repository.findAll();

        Set<Student> filteredStudents = StreamSupport.stream(students.spliterator(), false)
                .filter(student -> student.getGroup().toString().contains(g)).collect(Collectors.toSet());

        return filteredStudents;

    }
    */
}

