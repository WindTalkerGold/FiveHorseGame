package fundmental;

import java.util.Calendar;
import java.util.ArrayList;

public class Student extends Person {
    private final ArrayList<Teacher> teachers;

    public Student(String name, String birthYear) {
        super(name, birthYear);
        teachers = new ArrayList<Teacher>();
    }

    public void addTeacher(Teacher teacher) {
        if(!teachers.contains(teacher)) {
            teachers.add(teacher);
        }
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public ArrayList<Teacher> getAllTeachers() {
        return teachers;
    }

    public String getClassWithOldestTeacher() {
        Teacher oldestTeacher = null;
        for(Teacher teacher : teachers) {
            if(oldestTeacher == null) {
                oldestTeacher = teacher;
                continue;
            }

            if(teacher.isOlderThan(oldestTeacher)) {
                oldestTeacher = teacher;
            }
        }

        return oldestTeacher == null ? null : oldestTeacher.getClassToTeach();
    }

    public String getTeachersAverageAge() {
        if(teachers.size() == 0)
            return "N/A";

        double totalAge = 0;
        for(Person person : teachers) {
            totalAge += person.getAge();
        }
        // 思考这里为什么用double？

        double averageAge = totalAge /  teachers.size();
        return Double.toString(averageAge);
    }
}
