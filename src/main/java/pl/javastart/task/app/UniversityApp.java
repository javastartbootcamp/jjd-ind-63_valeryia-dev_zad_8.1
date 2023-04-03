package pl.javastart.task.app;

import pl.javastart.task.model.Group;
import pl.javastart.task.model.Lecturer;
import pl.javastart.task.model.Student;

import java.util.Arrays;

public class UniversityApp {
    private int lecturerNumber = 0;
    private static final int MAX_LECTURER_AMOUNT = 30;
    private int groupNumber = 0;
    private static final int MAX_GROUP_AMOUNT = 10;
    private final Lecturer[] lecturers = new Lecturer[MAX_LECTURER_AMOUNT];
    private final Group[] groups = new Group[MAX_GROUP_AMOUNT];

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    /**
     * Tworzy prowadzącego zajęcia.
     * W przypadku gdy prowadzący z zadanym id już istnieje, wyświetlany jest komunikat:
     * "Prowadzący z id [id_prowadzacego] już istnieje"
     *
     * @param id        - unikalny identyfikator prowadzącego
     * @param degree    - stopień naukowy prowadzącego
     * @param firstName - imię prowadzącego
     * @param lastName  - nazwisko prowadzącego
     */

    public void createLecturer(int id, String degree, String firstName, String lastName) {
        if (lecturerIdExist(id) == false && !arrayLectureNumberBiggerArrayLength()) {
            lecturers[lecturerNumber] = new Lecturer(id, degree, firstName, lastName);
            lecturerNumber++;
        } else {
            System.out.println("Prowadzący z id " + id + " już istnieje");
        }

    }

    private boolean arrayLectureNumberBiggerArrayLength() {
        return (lecturerNumber > lecturers.length);
    }

    private boolean lecturerIdExist(int id) {
        boolean lecturerIdExist = false;
        for (int i = 0; lecturers[i] != null; i++) {
            if (lecturers[i].getId() == id) {
                lecturerIdExist = true;
                break;
            }
        }
        return lecturerIdExist;
    }

    /**
     * Tworzy grupę zajęciową.
     * W przypadku gdy grupa z zadanym kodem już istnieje, wyświetla się komunikat:
     * "Grupa [kod grupy] już istnieje"
     * W przypadku gdy prowadzący ze wskazanym id nie istnieje wyświetla się komunikat:
     * "Prowadzący o id [id prowadzacego] nie istnieje"
     *
     * @param code       - unikalny kod grupy
     * @param name       - nazwa przedmiotu (np. "Podstawy programowania")
     * @param lecturerId - identyfikator prowadzącego. Musi zostać wcześniej utworzony za pomocą metody {@link #createLecturer(int, String, String, String)}
     */
    public void createGroup(String code, String name, int lecturerId) {
        if (groupCodeExist(code) == false && lecturerIdExist(lecturerId) == true && !arrayGroupNumberBiggerArrayLength()) {
            groups[groupNumber] = new Group(code, name, lecturerId);
            groupNumber++;
        } else if (groupCodeExist(code) == true) {
            System.out.println("Grupa z kodem " + code + " juz istnieje");
        } else if (lecturerIdExist(lecturerId) == false) {
            System.out.println("Prowadzący o id " + lecturerId + " nie istnie je");
        }
    }

    private boolean arrayGroupNumberBiggerArrayLength() {
        return (groupNumber > groups.length);
    }

    private boolean groupCodeExist(String code) {
        boolean groupCodeExist = false;
        for (int i = 0; groups[i] != null; i++) {
            if (groups[i].getCode().equals(code)) {
                groupCodeExist = true;
                break;
            }
        }
        return groupCodeExist;
    }

    /**
     * Dodaje studenta do grupy zajęciowej.
     * W przypadku gdy grupa zajęciowa nie istnieje wyświetlany jest komunikat:
     * "Grupa [kod grupy] nie istnieje
     *
     * @param index     - unikalny numer indeksu studenta
     * @param groupCode - kod grupy utworzonej wcześniej za pomocą {@link #createGroup(String, String, int)}
     * @param firstName - imię studenta
     * @param lastName  - nazwisko studenta
     */
    public void addStudentToGroup(int index, String groupCode, String firstName, String lastName) {
        Group group = getGroup(groupCode);
        if (groupCodeExist(groupCode) == true) {
            group.addStudent(index, groupCode, firstName, lastName);
        } else {
            System.out.println("Grupa " + groupCode + " nie istnieje");
        }

    }

    private Group getGroup(String groupCode) {
        Group group = new Group();
        for (int i = 0; groups[i] != null; i++) {
            if (groups[i].getCode().equals(groupCode)) {
                group = groups[i];
                break;
            }
        }
        return group;
    }

    /**
     * Wyświetla informacje o grupie w zadanym formacie.
     * Oczekiwany format:
     * Kod: [kod_grupy]
     * Nazwa: [nazwa przedmiotu]
     * Prowadzący: [stopień naukowy] [imię] [nazwisko]
     * Uczestnicy:
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * W przypadku gdy grupa nie istnieje, wyświetlany jest komunikat w postaci: "Grupa [kod] nie znaleziona"
     *
     * @param groupCode - kod grupy, dla której wyświetlić informacje
     */

    public void printGroupInfo(String groupCode) {
        Group group = getGroup(groupCode);
        group.getInfo(getLecturer(group.getLecturerId()), group);

    }

    private Lecturer getLecturer(int id) {
        Lecturer lecturer = new Lecturer();
        for (int i = 0; lecturers[i] != null; i++) {
            if (lecturers[i].getId() == id) {
                lecturer = lecturers[i];
                break;
            }
        }
        return lecturer;
    }

    /**
     * Dodaje ocenę końcową dla wskazanego studenta i grupy.
     * Student musi być wcześniej zapisany do grupy za pomocą {@link #addStudentToGroup(int, String, String, String)}
     * W przypadku, gdy grupa o wskazanym kodzie nie istnieje, wyświetlany jest komunikat postaci:
     * "Grupa pp-2022 nie istnieje"
     * W przypadku gdy student nie jest zapisany do grupy, wyświetlany jest komunikat w
     * postaci: "Student o indeksie 179128 nie jest zapisany do grupy pp-2022"
     * W przypadku gdy ocena końcowa już istnieje, wyświetlany jest komunikat w postaci:
     * "Student o indeksie 179128 ma już wystawioną ocenę dla grupy pp-2022"
     *
     * @param grade - ocena
     */

    public void addGrade(int studentIndex, String groupCode, double grade) {
        if (groupCodeExist(groupCode) == true) {
            Group group = getGroup(groupCode);
            int studentNumber = Integer.MAX_VALUE;
            for (int i = 0; group.getStudents()[i] != null; i++) {
                if (group.getStudents()[i].getIndex() == studentIndex) {
                    if (studentHaveGrade(group.getStudents()[i]) == false) {
                        group.getStudents()[i].addGrade(grade);
                    } else {
                        System.out.println("Student o  " + studentIndex + " ma już wystawioną ocenę dla grupy " + groupCode);
                    }
                    studentNumber = i;
                    break;
                }
            }
            if (studentNumber == Integer.MAX_VALUE) {
                System.out.println("Student o indeksie " + studentIndex + " nie jest zapisany do grupy " + groupCode);
            }
        } else {
            System.out.println(" Grupa" + groupCode + "nie istnieje");
        }
    }

    private boolean studentHaveGrade(Student student) {
        return (student.getGrade() != 0);
    }

    /**
     * Wyświetla wszystkie oceny studenta.
     * Przykładowy wydruk:
     * Podstawy programowania: 5.0
     * Programowanie obiektowe: 5.5
     *
     * @param index - numer indesku studenta dla którego trzeba wyświetlić oceny
     */
    public void printGradesForStudent(int index) { // двойная петля
        for (int i = 0; groups[i] != null; i++) {
            for (int j = 0; groups[i].getStudents()[j] != null; j++) {
                if (groups[i].getStudents()[j].getIndex() == index) {
                    System.out.println(groups[i].getSubjectName() + ": " + groups[i].getStudents()[j].getGrade());
                }
            }
        }
    }

    /**
     * Wyświetla oceny studentów dla wskazanej grupy.
     * Przykładowy wydruk:
     * 179128 Marcin Abacki: 5.0
     * 179234 Dawid Donald: 4.5
     * 189521 Anna Kowalska: 5.5
     *
     * @param groupCode - kod grupy, dla której wyświetlić oceny
     */
    public void printGradesForGroup(String groupCode) {
        Group group = getGroup(groupCode);
        group.getStudentsGradeInfo();
    }

    /*
     * Wyświetla wszystkich studentów. Każdy student powinien zostać wyświetlony tylko raz.
     * Każdy student drukowany jest w nowej linii w formacie [nr_indeksu] [imie] [nazwisko]
     * Przykładowy wydruk:
     * 179128 Marcin Abacki
     * 179234 Dawid Donald
     * 189521 Anna Kowalska
     */
    private Student[] getStudentArray() { // jak prawidłowo odsortować
        int maxNumber = 100;
        Student[] students = new Student[maxNumber];
        int studentNumber = 0;
        int temp = 0;
        for (int i = 0; groups[i] != null; i++) {
            for (int j = 0; groups[i].getStudents()[j] != null; j++) {
                {
                    if (!groups[i].getStudents()[j].equals(students[temp])) {
                        students[studentNumber] = groups[i].getStudents()[j];
                        studentNumber++;
                    } else {
                        temp++;
                    }
                }
            }
        }
        return students;
    }

    public void printAllStudents() {
        Student[] array = getStudentArray();
        for (int i = 0; array[i] !=  null; i++) {
            System.out.println(array[i].printInfo());
        }
    }
}



