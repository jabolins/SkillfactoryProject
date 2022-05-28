import models.Student;
import models.University;
import services.ReadXls;

import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {

       ArrayList<Student> students= ReadXls.StudentsFromXls();
        students.forEach((e)-> System.out.println(e));

        ArrayList<University> universities=ReadXls.UniversitiesFromXls();
        universities.forEach((e)->System.out.println(e));

    }
}
