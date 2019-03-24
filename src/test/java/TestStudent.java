
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import domain.Student;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class TestStudent {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final String SERIAL_NUMBER = "sn01";
    private static final String NEW_SERIAL_NUMBER = "sn02";
    private static final String NAME = "studentName";
    private static final String NEW_NAME = "studentName2";
    private static final int GROUP = 123;
    private static final int NEW_GROUP = 125;

    private Student student;

    @Before
    public void setUp()  {
        student = new Student(SERIAL_NUMBER, NAME, GROUP);
        student.setId(ID);
    }

    @After
    public void tearDown()  {
        student=null;
    }

    @Test
    public void testGetSerialNumber()  {
        assertTrue(student.getSerialNumber().isPresent());
        assertEquals("Serial numbers should be equal", Optional.ofNullable(SERIAL_NUMBER), student.getSerialNumber());
    }

    @Test
    public void testSetSerialNumber()  {
        student.setSerialNumber(NEW_SERIAL_NUMBER);
        assertTrue(student.getSerialNumber().isPresent());
        assertEquals("Serial numbers should be equal", Optional.ofNullable(NEW_SERIAL_NUMBER), student.getSerialNumber());
    }

    @Test
    public void testGetId()  {
        assertEquals("Ids should be equal", ID, student.getId());
    }

    @Test
    public void testSetId()  {
        student.setId(NEW_ID);
        assertEquals("Ids should be equal", NEW_ID, student.getId());
    }


    @Test
    public void testGetName() {
        assertTrue(student.getName().isPresent());
        assertEquals("Names should be equal", Optional.ofNullable(NAME), student.getName());
    }



    @Test
    public void testSetName() {
        student.setName(NEW_NAME);
        assertTrue(student.getName().isPresent());
        assertEquals("Names should be equal", Optional.ofNullable(NEW_NAME), student.getName());
    }



    @Test
    public void testGetGroup() throws Exception {
        assertTrue(student.getGroup().isPresent());
        assertEquals("Groups should be equal", Optional.ofNullable(GROUP), student.getGroup());
    }



    @Test
    public void testSetGroup() throws Exception {
        student.setGroup(NEW_GROUP);
        assertTrue(student.getGroup().isPresent());
        assertEquals("Serial numbers should be equal", Optional.ofNullable(NEW_GROUP), student.getGroup());
    }
    }

