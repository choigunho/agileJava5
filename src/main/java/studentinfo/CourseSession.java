package studentinfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CourseSession implements Comparable<CourseSession> {
    private static int count;
    private String department;
    private String number;
    private ArrayList<Student> students = new ArrayList<Student>();
    private Date startDate;
    private int numberOfCredits;

    private CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
    }

    private static void incrementCount() {
        ++count;
    }

    public static int getCount() {
        return count;
    }

    public static void resetCount() {
        count = 0;
    }

    public static CourseSession create(String department, String number, Date startDate) {
        incrementCount();
        return new CourseSession(department, number, startDate);
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
        student.addCredits(numberOfCredits);
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

    public ArrayList<Student> getAllStudents() {
        return students;
    }


    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    @Override
    public int compareTo(CourseSession that) {
        int compare = this.getDepartment().compareTo(that.getDepartment());
        if (compare == 0) {
            compare = this.getNumber().compareTo(that.getNumber());
        }
        return compare;
    }
}
