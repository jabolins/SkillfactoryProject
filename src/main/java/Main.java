import Models.Student;
import Models.StudyProfile;
import Models.University;

public class Main {
    public static void main(String[] args) {

        Student student = new Student("Jon Lenon", "1", 2, 7);

        University university = new University("1", "Rīga Stradiņš University", "RSU",1969,StudyProfile.MEDICINE );

        System.out.println(student);
        System.out.println(university);
    }
}
