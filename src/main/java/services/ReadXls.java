package services;

import models.Student;
import models.StudyProfile;
import models.University;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadXls {

    private static String filePath = "src/main/resources/universityInfo.xlsx";

    private ReadXls() {
    }

    public static ArrayList<Student> StudentsFromXls() {
        XSSFWorkbook workbook = getXssfWorkbook();
        ArrayList<Student> students = new ArrayList<>();
        if (workbook.getSheet("Студенты")!=null) {
            XSSFSheet sheet = workbook.getSheet("Студенты"); //TODO jāizdomā kas notiek ja šādas lapas nav
            Iterator<Row> rowIterator = sheet.iterator();

            //switch first row
            Row row = rowIterator.next();

            while (rowIterator.hasNext()) {
                Student student = new Student();
                row = rowIterator.next();

                student.setUniversityId(row.getCell(0).getStringCellValue());
                student.setFullName(row.getCell(1).getStringCellValue());
                try {
                    student.setCurrentCourseNumber((int) row.getCell(2).getNumericCellValue());
                } catch (Exception e) {
                    //in case of problems we set the default value
                    student.setCurrentCourseNumber(-1);
                    e.printStackTrace();
                }
                try {
                    student.setAvgExamScore((float) row.getCell(3).getNumericCellValue());
                } catch (Exception e) {
                    //in case of problems we set the default value
                    student.setAvgExamScore(-1);
                    e.printStackTrace();
                }

                students.add(student);
            }
        }
        return students;
    }

    public static ArrayList<University> UniversitiesFromXls() {
        XSSFWorkbook workbook = getXssfWorkbook();
        ArrayList<University> universities = new ArrayList<>();

        XSSFSheet sheet = workbook.getSheet("Университеты");//TODO jāizdomā kas notiek ja šādas lapas nav
        Iterator<Row> rowIterator = sheet.iterator();

        //switch first row
        Row row = rowIterator.next();

        while (rowIterator.hasNext()) {
            University university = new University();
            row = rowIterator.next();

            university.setId(row.getCell(0).getStringCellValue());
            university.setFullName(row.getCell(1).getStringCellValue());
            university.setShortNam(row.getCell(2).getStringCellValue());
            try {
                university.setYearOfFoundation((int) row.getCell(3).getNumericCellValue());
            } catch (Exception e) {
                //in case of problems we set the default value
                university.setYearOfFoundation(-1);
                e.printStackTrace();
            }
            try {
                university.setMainProfile(StudyProfile.valueOf(row.getCell(4).getStringCellValue()));
            } catch (IllegalArgumentException e) {
                //in case of problems we set the default value
                university.setMainProfile(StudyProfile.DEFAULT);
                e.printStackTrace();
            }

            universities.add(university);
        }
        return universities;
    }

    private static XSSFWorkbook getXssfWorkbook() {
        XSSFWorkbook workbook = null;
        try {
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            workbook = new XSSFWorkbook(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
