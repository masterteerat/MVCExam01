package ModelPack;

public class RegisteredSubject {
    private String studentId;
    private String subjectId;


    public RegisteredSubject(String studentId, String subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }


    public String getStudentId() { 
        return studentId; 
    }
    public String getSubjectId() { 
        return subjectId; 
    }
}