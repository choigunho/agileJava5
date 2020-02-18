import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import studentinfo.CourseSession;
import studentinfo.DateUtil;
import studentinfo.Student;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CourseSessionTest {

    private static final int CREDITS = 3;
    private static CourseSession session;
    private static Date startDate;

    @BeforeAll
    static void setUp() {
        startDate = DateUtil.createDate(2003, 1, 6);
//        session = CourseSession.create("ENGL", "101", startDate);
        session = createCourseSession();
    }

    @Test
    void testCreate() {
        assertEquals("ENGL", session.getDepartment());
        assertEquals("101", session.getNumber());
        assertEquals(0, session.getNumberOfStudents());
    }

    @Test
    void testEnrollStudents() {
        Student student1 = new Student("Cain DiVoe");
        session.enroll(student1);
        assertEquals(CREDITS, student1.getCredits());
        assertEquals(1, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));

        Student student2 = new Student("Coralee DeVaughn");
        session.enroll(student2);
        assertEquals(2, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));
        assertEquals(student2, session.get(1));
    }

    @Test
    void testCourseDates() {
        Date sixteenWeeksOut = DateUtil.createDate(2003, 4, 25);
        assertEquals(sixteenWeeksOut, session.getEndDate());
    }

    @Test
    void testCount() {
        CourseSession.resetCount();
        createCourseSession();
        assertEquals(1, CourseSession.getCount());
        createCourseSession();
        assertEquals(2, CourseSession.getCount());
    }

    @Test
    void testComparable() {
        final Date date = new Date();
        CourseSession sessionA = CourseSession.create("CMSC", "101", date);
        CourseSession sessionB = CourseSession.create("ENGL", "101", date);
        assertTrue(sessionA.compareTo(sessionB) < 0);
        assertTrue(sessionB.compareTo(sessionA) > 0);

        CourseSession sessionC = CourseSession.create("CMSC", "101", date);
        assertEquals(0, sessionA.compareTo(sessionC));

        CourseSession sessionD = CourseSession.create("CMSC", "210", date);
        assertTrue(sessionC.compareTo(sessionD) < 0);
        assertTrue(sessionD.compareTo(sessionC) > 0);
    }

    private static CourseSession createCourseSession() {
        CourseSession session = CourseSession.create("ENGL", "101", startDate);
        session.setNumberOfCredits(CourseSessionTest.CREDITS);
        return session;
    }

}