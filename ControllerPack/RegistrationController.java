package ControllerPack;

import ModelPack.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationController {

    private StudentModel studentModel;
    private SubjectModel subjectModel;
    private RegisteredSubjectModel registeredSubjectModel;

    // listener สำหรับ update view
    public interface RegistrationListener {
        void onRegistrationUpdated();
    }

    private RegistrationListener listener;
    public void setRegistrationListener(RegistrationListener listener) {
        this.listener = listener;
    }

    public RegistrationController() {
        studentModel = new StudentModel();
        subjectModel = new SubjectModel();
        registeredSubjectModel = new RegisteredSubjectModel();
    }

    public List<Student> getAllStudents() {
        return studentModel.getAllStudents();
    }

    public List<Subject> getAllSubjects() {
        return subjectModel.getAllSubjects();
    }

    public List<RegisteredSubject> getRegisteredSubjects() {
        return registeredSubjectModel.getAllRegisteredSubjects();
    }

    public boolean isRegistered(String studentId, String subjectId) {
        return registeredSubjectModel.getAllRegisteredSubjects()
                .stream()
                .anyMatch(r -> r.getStudentId().equals(studentId) && r.getSubjectId().equals(subjectId));
    }

    public List<Subject> getAvailableSubjects(String studentId) {
        return subjectModel.getAllSubjects().stream()
                .filter(s -> !isRegistered(studentId, s.getSubjectId()))
                .collect(Collectors.toList());
    }

    //update database
    public boolean registerSubject(String studentId, String subjectId) {
        if(isRegistered(studentId, subjectId)) return false;
        registeredSubjectModel.getAllRegisteredSubjects().add(new RegisteredSubject(studentId, subjectId));
        subjectModel.getAllSubjects().stream()
                .filter(s -> s.getSubjectId().equals(subjectId))
                .findFirst()
                .ifPresent(Subject::incrementCurrentStudents);

        saveRegisteredSubjectsToCSV("MockDataBase/registeredSubjects.csv");
        if(listener != null) listener.onRegistrationUpdated();
        return true;
    }

    private void saveRegisteredSubjectsToCSV(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("studentId,subjectId\n");
            for (RegisteredSubject r : registeredSubjectModel.getAllRegisteredSubjects()) {
                bw.write(r.getStudentId() + "," + r.getSubjectId() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}