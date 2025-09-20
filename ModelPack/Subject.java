package ModelPack;

public class Subject {
    private String subjectId;
    private String subjectName;
    private int credits;
    private String teacherName;
    private String prerequisiteId;
    private int maxStudents;
    private int currentStudents;

    public Subject(String subjectId, String subjectName, int credits, String teacherName,
                   String prerequisiteId, int maxStudents, int currentStudents) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credits = credits;
        this.teacherName = teacherName;
        this.prerequisiteId = prerequisiteId.isEmpty() ? null : prerequisiteId;
        this.maxStudents = maxStudents;
        this.currentStudents = currentStudents;
    }

    // Getters
    public String getSubjectId() { return subjectId; }
    public String getSubjectName() { return subjectName; }
    public int getCredits() { return credits; }
    public String getTeacherName() { return teacherName; }
    public String getPrerequisiteId() { return prerequisiteId; }
    public int getMaxStudents() { return maxStudents; }
    public int getCurrentStudents() { return currentStudents; }

    // Update number of registered students
    public void incrementCurrentStudents() { currentStudents++; }
    public void decrementCurrentStudents() { 
        if(currentStudents > 0) currentStudents--; 
    }
}