package studentinfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CourseSession {
    private String department;
    private String number;
    private ArrayList<Student> students = new ArrayList<Student>();
    private Date startDate;

    public CourseSession(String department, String number) {
        this.department = department;
        this.number = number;
    }

    public CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    public int getNumberOfStudents() {
        return students.size();
    }

    public void enroll(Student student) {
        students.add(student);
    }

    public Student get(int index) {
        return students.get(index);
    }

    /**
     *
     * @return Date the last date of the course session
     */
    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        // weeks * days per week -3 days
        int numberOfDays = 16 * 7 - 3;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    ArrayList<Student> getAllStudents() {
        return students;
    }

//    public String getRosterReport() {
//        StringBuilder buffer = new StringBuilder();
//
//        buffer.append(ROSTER_REPORT_HEADER);
//
//        for(Student student: students) {
//            buffer.append(student.getName());
//            buffer.append(NEWLINE);
//        }
//
//        buffer.append(ROSTER_REPORT_FOOTER + students.size() + NEWLINE);
//
//        return buffer.toString();
//    }

}
