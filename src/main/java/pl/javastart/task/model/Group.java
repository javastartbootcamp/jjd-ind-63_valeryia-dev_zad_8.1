package pl.javastart.task.model;

import java.util.Arrays;
import java.util.Stack;

public class Group {
    private String code;
    private String subjectName;
    private int lecturerId;
    private int studentNumber = 0;
    public static final int MAX_STUDENT_SIZE = 25;
    private Student[] students = new Student[MAX_STUDENT_SIZE];

    public Group() {
    }

    public Group(String code, String subjectName, int lecturerId) {
        this.code = code;
        this.subjectName = subjectName;
        this.lecturerId = lecturerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public void addStudent(int studentIndex, String groupCode, String firstName, String lastName) {
        if (studentNumber < students.length) {
            students[studentNumber] = new Student(studentIndex, groupCode, firstName, lastName);
            studentNumber++;
        }
    }

    public void getInfo(Lecturer lecturer, Group group) {
        System.out.println("Kod: " + code + "\nNazwa przedmiotu: " + subjectName + "\nProwadzący: " + lecturer.printInfo() + "\nUczęstnicy: ");
        group.getStudentsInfo();
    }

    public void getStudentsInfo() {
        for (int i = 0; students[i] != null; i++) {
            System.out.print(students[i].printInfo());
            System.out.println();
        }
    }

    public void getStudentsGradeInfo() {
        for (int i = 0; students[i] != null; i++) {
            System.out.print(students[i].printGradeInfo());
            System.out.println();
        }
    }
}

