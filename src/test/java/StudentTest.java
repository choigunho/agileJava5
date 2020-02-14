import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest extends junit.framework.TestCase {

    @Test
    void testCreate() {
        Student student = new Student("Jane Doe");
        assertNotNull(student);
        String studentName = student.getName();
        assertEquals("Jane Doe", studentName);

        Student secondStudent = new Student("Joe Blow");
        String secondStudentName = secondStudent.getName();
        assertEquals("Joe Blow", secondStudentName);
    }

}
