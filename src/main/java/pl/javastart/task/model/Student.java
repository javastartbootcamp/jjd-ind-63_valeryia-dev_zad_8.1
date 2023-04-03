package pl.javastart.task.model;

public class Student extends Person {
    private int index;
    private double grade;
    private  String groupCode;

    public Student(int index, String  groupCode, String firstName, String lastName) {
        this.index = index;
        this.groupCode = groupCode;
        setFirstName(firstName);
        setLastName(lastName);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Override
    public String printInfo() {
        return "[" + index + "]" +   super.printInfo();
    }

    public String printGradeInfo() {
        return  getFirstName() + " "  + getLastName() + ": " + index + super.printInfo() + grade;
    }

    public  void addGrade(double grade) {
        setGrade(grade);
    }
}
