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
    }

}
