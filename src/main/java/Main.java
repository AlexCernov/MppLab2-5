import domain.Problem;
import domain.Student;
import domain.validators.ProblemValidator;
import domain.validators.StudentValidator;
import domain.validators.Validator;
import repository.ProblemRepository;
import repository.Repository;
import repository.InMemoryRepository;
import service.ProblemService;
import service.StudentService;
import ui.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class Main {

    public static void main(String args[]) {
        //in-memory repo

         Validator<Student> studentValidator = new StudentValidator();
         Validator<Problem> problemValidator = new ProblemValidator();
        Repository<Long, Student> studentRepository = new InMemoryRepository<>(studentValidator);
         Repository<Long, Problem> problemRepository = new ProblemRepository<>(problemValidator);
         StudentService studentService = new StudentService(studentRepository);
         ProblemService problemService = new ProblemService(problemRepository);
         Console console = new Console(studentService,problemService);
         console.run();


    }
}
