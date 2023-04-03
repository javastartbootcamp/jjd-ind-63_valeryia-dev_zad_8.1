package pl.javastart.task.model;

public class Lecturer extends Person {
    private int id;
    private String degree;

    public Lecturer() {
    }

    public Lecturer(int id, String degree, String firstName, String lastName) {
        this.id = id;
        this.degree = degree;
        setFirstName(firstName);
        setLastName(lastName);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String printInfo() {
        return " [" + degree + "]" +  super.printInfo();
    }
}
