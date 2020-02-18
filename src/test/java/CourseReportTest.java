import org.junit.jupiter.api.Test;
import report.CourseReport;
import studentinfo.CourseSession;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
//import static report.RosterReporter.NEWLINE;
import static report.ReportConstant.NEWLINE;

public class CourseReportTest {

    @Test
    void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(CourseSession.create("ENGL", "101", date));
        report.add(CourseSession.create("CZEC", "200", date));
        report.add(CourseSession.create("ITAL", "410", date));
        report.add(CourseSession.create("CZEC", "220", date));
        report.add(CourseSession.create("ITAL", "330", date));

        assertEquals(
                "CZEC 200" + NEWLINE +
                        "CZEC 220" + NEWLINE +
                        "ENGL 101" + NEWLINE +
                        "ITAL 330" + NEWLINE +
                        "ITAL 410" + NEWLINE,
                report.text()
        );
    }


}
