package ModelPack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    private String csvPath = "MockDataBase/Students.csv";
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                students.add(new Student(
                        data[0], data[1], data[2], data[3], data[4], data[5], data[6]
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public String getPath(){
        return csvPath;
    }

}