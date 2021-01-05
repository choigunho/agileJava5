package report;


import studentinfo.CourseSession;

import java.util.ArrayList;
import java.util.Collections;

import static report.ReportConstant.NEWLINE;

public class CourseReport {
    ArrayList<CourseSession> sessions = new ArrayList<CourseSession>();

    public void add(CourseSession session) {
        sessions.add(session);
    }

    public String text() {
        Collections.sort(sessions);
        StringBuilder builder = new StringBuilder();
        for(CourseSession session: sessions) {
            builder.append(
                    session.getDepartment() + " " +
                            session.getNumber() + NEWLINE
            );
        }
        return builder.toString();
    }
}
