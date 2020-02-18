import org.junit.jupiter.api.Test;
import studentinfo.CourseSession;
import report.RosterReporter;
import studentinfo.DateUtil;
import studentinfo.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RosterReporterTest {

    @Test
    void testRosterReport() {
        CourseSession session = CourseSession.create("ENGL", "101", DateUtil.createDate(2003, 1, 6));

        session.enroll(new Student("A"));
        session.enroll(new Student("B"));

        String rosterReport = new RosterReporter(session).getReport();
        System.out.println(rosterReport);
        assertEquals(
                RosterReporter.ROSTER_REPORT_HEADER +
                        "A" + RosterReporter.NEWLINE +
                        "B" + RosterReporter.NEWLINE +
                        RosterReporter.ROSTER_REPORT_FOOTER + "2" +
                        RosterReporter.NEWLINE, rosterReport);
    }

}
