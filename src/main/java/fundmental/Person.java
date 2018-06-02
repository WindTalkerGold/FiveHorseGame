package fundmental;

import java.util.Calendar;

public class Person {
    public Person(String name, String birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public String getbirthYear() {
        return birthYear;
    }

    public int getAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - new Integer(birthYear);
        // 注意int类型与String类型的转化
    }

    public boolean isOlderThan(Person another) {
        return this.getAge() > another.getAge();
    }

    private String name;
    private String birthYear;
}
