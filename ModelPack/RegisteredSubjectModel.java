package ModelPack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegisteredSubjectModel {
    private String csvPath = "MockDataBase/RegisteredSubjects.csv";
    
    public List<RegisteredSubject> getAllRegisteredSubjects() {
        List<RegisteredSubject> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line = br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                list.add(new RegisteredSubject(data[0], data[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getPath(){
        return csvPath;
    }
}