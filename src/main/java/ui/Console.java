package ui;

import domain.Problem;
import domain.Student;
import domain.validators.ValidatorException;
import service.StudentService;
import service.ProblemService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Console {
    private StudentService studentService;
    private ProblemService problemService;

    public Console(StudentService studentService, ProblemService problemService) {
        this.studentService = studentService;
        this.problemService = problemService;
    }

    private static void printMenu() {
        System.out.println("--Manage students--");
        System.out.println("1.Print all students.");
        System.out.println("2.Add a student.");
        System.out.println("3.Filter students by name.");
        System.out.println("4.Filter students by gorup.");
        System.out.println("5.Delete student");
        System.out.println("6.Update student");
        System.out.println("x.Assign problem to student");
        System.out.println("--Manage lab assignments--");
        System.out.println("7.Add lab assignmentt");
        System.out.println("8.Delete lab assignment");
        System.out.println("9.Update lab assignment");
        System.out.println("10.Print all assignments");
        System.out.println("--Manage students' grades--");
        System.out.println("11. Assign grade to student");
        System.out.println("0.Exit");
    }


    public void run() {
        boolean ok = true;
        while (ok) {
            printMenu();
            System.out.println("Please select an option:");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            Long input = null;

            try {
                Optional<String> IDOpt = inputValue(bufferedReader, "Option:");
                input = Long.parseLong(IDOpt.orElseThrow(() -> new ValidatorException("You must provide an option in ")));
                switch (Math.toIntExact(input)) {
                    case 1: {
                        printAllStudents();
                        break;
                    }
                    case 2: {
                        addStudent();
                        break;
                    }
                    case 4: {
                        filterStudents2();
                        break;
                    }
                    case 5: {
                        deleteStudent();
                        break;
                    }
                    case 3: {
                        filterStudents();
                        break;
                    }
                    case 6: {
                        updateStudent();
                        break;
                    }
                    case 7: {
                        addAssignment();
                        break;
                    }
                    case 8: {
                        deleteAssignment();
                        break;
                    }
                    case 9: {
                        updateAssignment();
                        break;
                    }
                    case 10: {
                        printAllAssignments();
                        break;
                    }
                    default: {
                        ok = false;
                        break;
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private void addStudent() {
        Student student = null;
        try {
            student = readStudent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (student == null || student.getId() < 0) {
            return;
        }
        try {
            studentService.addStudent(student);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

    }

    private void deleteStudent() throws IOException {
        System.out.println("Please give the id of the student you want to delete:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Long ID = null;

        try {
            Optional<String> IDOpt = inputValue(bufferedReader, "ID");
            ID = Long.parseLong(IDOpt.orElseThrow(() -> new ValidatorException("ID is required in order to save a student.")));
            studentService.deleteStudent(ID);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateStudent() {
        Student student = null;
        try {
            student = readStudent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (student == null || student.getId() < 0) {
            return;
        }
        try {
            studentService.updateStudent(student);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }


    private Optional<String> inputValue(BufferedReader bufferedReader, String property) throws IOException {
        System.out.println(property + ": ");
        String line = bufferedReader.readLine();
        return line.equals("") ? Optional.empty() : Optional.of(line);
    }

    private Student readStudent() throws IOException {
        System.out.println("Read student {id,serialNumber, name, group}");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Long ID = null;

        try {
            Optional<String> IDOpt = inputValue(bufferedReader, "ID");
            ID = Long.parseLong(IDOpt.orElseThrow(() -> new ValidatorException("ID is required in order to save a student.")));


        } catch (IOException e) {
            e.printStackTrace();
        }

        Optional<String> serialNrOpt = inputValue(bufferedReader, "Serial Number");
        String serialNumber = serialNrOpt.orElseThrow(() -> new ValidatorException("Serial Number is required in order to save a student."));

        Optional<String> nameOpt = inputValue(bufferedReader, "Name");
        String name = nameOpt.orElseThrow(() -> new ValidatorException("Name is required in order to save a student."));

        Integer group;

        try {
            Optional<String> groupOpt = inputValue(bufferedReader, "Group");
            group = Integer.parseInt(groupOpt.orElseThrow(() -> new ValidatorException("Group is required in order to save a student.")));
        } catch (NumberFormatException e) {
            throw new ValidatorException("Invalid format. The group is an integer.");
        }

        Student student = new Student(serialNumber, name, group);
        student.setId(ID);

        return student;

    }


    private void printAllStudents() {
        Set<Student> students = studentService.getAllStudents();
        students.stream().forEach(System.out::println);
    }

    private void filterStudents() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Optional<String> filterBy = inputValue(bufferedReader, "Names containing ");
            String input = filterBy.orElseThrow(() -> new ValidatorException("String required in order to filter students."));
            Set<Student> students = studentService.filterStudentsByName(input);
            Optional<Set<Student>> opt = Optional.ofNullable(students);
            opt.filter(o -> !o.isEmpty()).ifPresentOrElse(set -> printSet(set), () -> System.out.println("There are no students with their name containing " + input + "."));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printSet(Set<Student> set) {
        set.forEach(o -> System.out.println(o));
    }


    private void filterStudents2() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Optional<String> filterBy = inputValue(bufferedReader, "In group ");
            String input = filterBy.orElseThrow(() -> new ValidatorException("String required in order to filter students."));
            Set<Student> students = studentService.filterStudentsByGroup(input);
            Optional<Set<Student>> opt = Optional.ofNullable(students);
            opt.filter(o -> !o.isEmpty()).ifPresentOrElse(set -> printSet(set), () -> System.out.println("There is no " + input + " group."));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void addAssignment() {
        Problem problem = null;
        try {
            problem = readProblem();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (problem == null || problem.getId() < 0) {
            return;
        }
        try {
            problemService.addProblem(problem);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

    }

    private void deleteAssignment() throws IOException {
        System.out.println("Please give the id of the student you want to delete:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Long ID = null;

        try {
            Optional<String> IDOpt = inputValue(bufferedReader, "ID");
            ID = Long.parseLong(IDOpt.orElseThrow(() -> new ValidatorException("ID is required in order to save a problem.")));
            problemService.deleteProblem(ID);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateAssignment() {
        Problem problem = null;
        try {
            problem = readProblem();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (problem == null || problem.getId() < 0) {
            return;
        }
        try {
            problemService.updateProblem(problem);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }


    private Problem readProblem() throws IOException {
        System.out.println("Read problem {id,number, statement, points}");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Long ID = null;

        try {
            Optional<String> IDOpt = inputValue(bufferedReader, "ID");
            ID = Long.parseLong(IDOpt.orElseThrow(() -> new ValidatorException("ID is required in order to save a student.")));


        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer number;

        try {
            Optional<String> groupOpt = inputValue(bufferedReader, "Number");
            number = Integer.parseInt(groupOpt.orElseThrow(() -> new ValidatorException("Group is required in order to save a student.")));
        } catch (NumberFormatException e) {
            throw new ValidatorException("Invalid format. The group is an integer.");
        }
        Optional<String> nameOpt = inputValue(bufferedReader, "Statement");
        String name = nameOpt.orElseThrow(() -> new ValidatorException("Name is required in order to save a student."));

        Integer group;

        try {
            Optional<String> groupOpt = inputValue(bufferedReader, "Points");
            group = Integer.parseInt(groupOpt.orElseThrow(() -> new ValidatorException("Group is required in order to save a student.")));
        } catch (NumberFormatException e) {
            throw new ValidatorException("Invalid format. The group is an integer.");
        }

        Problem problem = new Problem(number, name, group);
        problem.setId(ID);

        return problem;

    }


    private void printAllAssignments() {
        Set<Problem> problems = problemService.getAllProblems();
        problems.stream().forEach(System.out::println);
    }
}
