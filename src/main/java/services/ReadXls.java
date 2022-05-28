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

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

public class ReadXls {

    private static String filePath = "src/main/resources/universityInfo.xlsx";

    private ReadXls() {
    }

    public static ArrayList<Student> StudentsFromXls() {
        XSSFWorkbook workbook = getXssfWorkbook();
        ArrayList<Student> students = new ArrayList<>();
        if (workbook.getSheet("Студенты") != null) {
            XSSFSheet sheet = workbook.getSheet("Студенты");
            Iterator<Row> rowIterator = sheet.iterator();

            //switch first row
            Row row = rowIterator.next();

            while (rowIterator.hasNext()) {
                Student student = new Student();
                row = rowIterator.next();

                try {
                    student.setUniversityId(row.getCell(0).getStringCellValue());
                } catch (Exception e) {
                    if (row.getCell(0).getCellType().equals(NUMERIC)) {
                        student.setUniversityId(String.valueOf(row.getCell(0).getNumericCellValue()));
                    } else
                        student.setUniversityId("default");
                    e.printStackTrace();
                }

                try {
                    student.setFullName(row.getCell(1).getStringCellValue());
                } catch (Exception e) {
                    student.setFullName("default");
                    e.printStackTrace();
                }

                try {
                    student.setCurrentCourseNumber((int) row.getCell(2).getNumericCellValue());
                } catch (Exception e) {
                    //set default nr
                    student.setCurrentCourseNumber(-1);
                    e.printStackTrace();
                }

                try {
                    student.setAvgExamScore((float) row.getCell(3).getNumericCellValue());
                } catch (Exception e) {
                    //set default Score
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
        if (workbook.getSheet("Университеты") != null) {
            XSSFSheet sheet = workbook.getSheet("Университеты");
            Iterator<Row> rowIterator = sheet.iterator();

            //switch first row
            Row row = rowIterator.next();

            while (rowIterator.hasNext()) {
                University university = new University();
                row = rowIterator.next();

                try {
                    university.setId(row.getCell(0).getStringCellValue());
                } catch (Exception e) {
                    if (row.getCell(0).getCellType().equals(NUMERIC)) {
                        university.setId(String.valueOf(row.getCell(0).getNumericCellValue()));
                    } else {
                        university.setId("default");
                    }
                    e.printStackTrace();
                }

                try {
                    university.setFullName(row.getCell(1).getStringCellValue());
                } catch (Exception e) {
                    university.setFullName("default");
                    e.printStackTrace();
                }

                try {
                    university.setShortName(row.getCell(2).getStringCellValue());
                } catch (Exception e) {
                    university.setShortName("default");
                    e.printStackTrace();
                }

                try {
                    university.setYearOfFoundation((int) row.getCell(3).getNumericCellValue());
                } catch (Exception e) {
                    //set default value
                    university.setYearOfFoundation(-1);
                    e.printStackTrace();
                }
                try {
                    university.setMainProfile(StudyProfile.valueOf(row.getCell(4).getStringCellValue()));
                } catch (IllegalArgumentException e) {
                    //set default value
                    university.setMainProfile(StudyProfile.DEFAULT);
                    e.printStackTrace();
                }

                universities.add(university);
            }
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
