package fundmental;

public class Teacher extends Person {
    public String getClassToTeach() {
        return classToTeach;
    }

    public void setClassToTeach(String classToTeach) {
        this.classToTeach = classToTeach;
    }

    private String classToTeach;

    public Teacher(String name, String birthYear, String classToTeach) {
        super(name, birthYear);
        this.classToTeach = classToTeach;
    }

}
