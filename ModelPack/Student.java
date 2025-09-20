package ModelPack;

public class Student {
    private String studentId;
    private String prefix;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String school;
    private String email;

    public Student(String studentId, String prefix, String firstName, String lastName,
                   String birthDate, String school, String email) {
        this.studentId = studentId;
        this.prefix = prefix;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.school = school;
        this.email = email;
    }

    //Getters
    public String getStudentId() { return studentId; }
    public String getPrefix() { return prefix; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getBirthDate() { return birthDate; }
    public String getSchool() { return school; }
    public String getEmail() { return email; }
}