package domain;

import java.util.Optional;

public class Student extends BaseEntity<Long>{
    private String serialNumber;
    private String name;
    private int group;

    public Student() {
    }

    public Student(String serialNumber, String name, int group) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.group = group;
    }


    public Optional<String> getSerialNumber() {
        return Optional.ofNullable(serialNumber);
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }


    public void setName(String name) {
        this.name = name;
    }

    public Optional<Integer> getGroup() {

        return Optional.ofNullable(group);
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (group != student.group) return false;
        if (!serialNumber.equals(student.serialNumber)) return false;
        return name.equals(student.name);

    }

    @Override
    public int hashCode() {
        int result = serialNumber.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + group;
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", group=" + group +
                "} " + super.toString();
    }
}
