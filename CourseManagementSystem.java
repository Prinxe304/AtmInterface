
import java.util.ArrayList;

public class CourseManagementSystem {

    // Course Class
    static class Course {
        private String courseCode;
        private String title;
        private String description;
        private int capacity;
        private int enrolledCount;
        private String schedule;

        public Course(String courseCode, String title, String description, int capacity, String schedule) {
            this.courseCode = courseCode;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.enrolledCount = 0;
            this.schedule = schedule;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getEnrolledCount() {
            return enrolledCount;
        }

        public String getSchedule() {
            return schedule;
        }

        public boolean isAvailable() {
            return enrolledCount < capacity;
        }

        public void enrollStudent() {
            if (isAvailable()) {
                enrolledCount++;
            }
        }

        public void removeStudent() {
            if (enrolledCount > 0) {
                enrolledCount--;
            }
        }

        public void displayCourseDetails() {
            System.out.println("Course Code: " + courseCode);
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Capacity: " + capacity);
            System.out.println("Enrolled: " + enrolledCount);
            System.out.println("Schedule: " + schedule);
            System.out.println("Available Slots: " + (capacity - enrolledCount));
        }
    }

    static class Student {
        private String studentId;
        private String name;
        private ArrayList<Course> registeredCourses;

        public Student(String studentId, String name) {
            this.studentId = studentId;
            this.name = name;
            this.registeredCourses = new ArrayList<>();
        }

        public String getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        public ArrayList<Course> getRegisteredCourses() {
            return registeredCourses;
        }

        public void registerCourse(Course course) {
            if (course.isAvailable()) {
                registeredCourses.add(course);
                course.enrollStudent();
                System.out.println(name + " successfully registered for " + course.getTitle());
            } else {
                System.out.println("No available slots for " + course.getTitle());
            }
        }

        public void dropCourse(Course course) {
            if (registeredCourses.contains(course)) {
                registeredCourses.remove(course);
                course.removeStudent();
                System.out.println(name + " successfully dropped " + course.getTitle());
            } else {
                System.out.println("Not registered for " + course.getTitle());
            }
        }

        public void displayRegisteredCourses() {
            System.out.println(name + "'s Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course.getTitle() + " (" + course.getCourseCode() + ")");
            }
        }
    }

    private ArrayList<Course> courseCatalog;
    private ArrayList<Student> studentList;

    public CourseManagementSystem() {
        courseCatalog = new ArrayList<>();
        studentList = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courseCatalog.add(course);
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (Course course : courseCatalog) {
            if (course.isAvailable()) {
                course.displayCourseDetails();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        CourseManagementSystem cms = new CourseManagementSystem();

        cms.addCourse(new Course("CS101", "Intro to Computer Science", "Learn the basics of computer science.", 30, "Mon, Wed, Fri - 9:00 AM"));
        cms.addCourse(new Course("MATH202", "Calculus II", "Advanced mathematics course.", 25, "Tue, Thu - 10:00 AM"));

        Student student1 = new Student("S001", "John Doe");

        cms.addStudent(student1);

        cms.displayAvailableCourses();

        student1.registerCourse(cms.courseCatalog.get(0));

        student1.displayRegisteredCourses();

        student1.dropCourse(cms.courseCatalog.get(0));

        student1.displayRegisteredCourses();

        cms.displayAvailableCourses();
    }
}
