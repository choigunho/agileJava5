import org.junit.jupiter.api.Test;
import studentinfo.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testCreate() {
        final String firstStudentName = "Jane Doe";
        Student firstStudent = new Student(firstStudentName);
        assertEquals(firstStudentName, firstStudent.getName());

        final String SecondStudentName = "Joe Blow";
        Student secondStudent = new Student(SecondStudentName);
        assertEquals(SecondStudentName, secondStudent.getName());
        assertEquals(firstStudentName, firstStudent.getName());
    }

    @Test
    void testFullTime() {
        Student student = new Student("a");
        assertFalse(student.isFullTime());
    }

    @Test
    void testCredits() {
        Student student = new Student("a");
        assertEquals(0, student.getCredits());
        student.addCredits(3);
        assertEquals(3, student.getCredits());
        student.addCredits(4);
        assertEquals(7, student.getCredits());
    }

    @Test
    void testStudentStatus() {
        Student student = new Student("a");
        assertEquals(0, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(3);
        assertEquals(3, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(4);
        assertEquals(7, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(5);
        assertEquals(12, student.getCredits());
        assertTrue(student.isFullTime());
    }

    @Test
    void testInState() {
        Student student = new Student("a");
        assertFalse(student.isInState());
        student.setState(Student.IN_STATE);
        assertTrue(student.isInState());
        student.setState("MD");
        assertFalse(student.isInState());
    }

}
