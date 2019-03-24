
import domain.Student;
import domain.validators.StudentValidator;
import org.junit.Before;
import org.junit.Test;
import repository.InMemoryRepository;
import repository.Repository;

import java.awt.print.Book;
import java.util.Optional;

import static org.junit.Assert.*;

public class TestRepository {
    private Repository<Long, Student> studentsRepo;

    @Before
    public void setUp() throws Exception {
        studentsRepo = new InMemoryRepository<Long, Student>(new StudentValidator());
        Student firststud = new Student("1010", "Cardas Andra", 921);
        firststud.setId((long) 10);
        Student secondstud = new Student("1011", "Cernov Alex", 921);
        secondstud.setId((long) 11);

        studentsRepo.save(firststud);
        studentsRepo.save(secondstud);


    }


    
    @Test
    public void save() {
        Student newStud = new Student("1234","Chelarescu Bogdan",921);
        newStud.setId((long) 12);
        studentsRepo.save(newStud);
        assertTrue(newStud.getGroup().isPresent());
        //this.studentsRepo.findAll().forEach(t -> assertEquals("Should all have the same genres", t.getName(), Optional.ofNullable("Chelarescu Bogdan")));


    }


    @Test
    public void findOne() {
        Student aux = this.studentsRepo.findOne((long) 10).get();
        assertEquals("Should be equal", "Cardas Andra", aux.getName().orElse("n/a"));


    }

   @Test
    public void findAll() {
        this.studentsRepo.findAll().forEach(t -> assertEquals("The authors should be the same", Optional.of(t.getGroup().orElse(0)),Optional.of(921)));

    }


    @Test
    public void delete() {
        this.studentsRepo.delete((long) 11);
        this.studentsRepo.findAll().forEach(t -> assertEquals("The authors should be the same", t.getName().orElse("n/a"), "Cardas Andra"));

    }

    @Test
    public void update() {
        Student thirdStudent = new Student("1012","Bogdan",921);
        thirdStudent.setId((long) 12);
        studentsRepo.save(thirdStudent);
        Student student_one = new Student("1013","Bogdan",921);
        student_one.setId((long) 12);
        this.studentsRepo.delete((long)10);
        this.studentsRepo.delete((long)11);
        this.studentsRepo.update(student_one);
        this.studentsRepo.findAll().forEach(t -> assertEquals("Trebuie sa fie Bogdan", t.getName().orElse("n/a"), "Bogdan"));

    }
}


