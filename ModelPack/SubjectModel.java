package ModelPack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectModel {
    private String csvPath = "MockDataBase/Subjects.csv";

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                subjects.add(new Subject(
                        data[0],
                        data[1],
                        Integer.parseInt(data[2]),
                        data[3],
                        data[4],
                        Integer.parseInt(data[5]),
                        Integer.parseInt(data[6])
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    // อัพเดตจำนวนคนปัจจุบัน (บันทึกกลับ CSV) – สามารถทำได้ภายหลัง
}