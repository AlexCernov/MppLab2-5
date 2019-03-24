package domain;

import java.util.Optional;
import domain.Problem;
import domain.Student;

public class Grade extends BaseEntity<Long>{
    private int value;
    private Problem forProblem;
    private Student toStudent;

    public Grade(){}

    public Grade(int value, Problem forProblem, Student toStudent){
        this.value = value;
        this.forProblem = forProblem;
        this.toStudent = toStudent;
    }

    public Optional<Integer> getValue() {   return Optional.ofNullable(value);}

    public void setValue( int nValue )  {   this.value = nValue;}

    public Optional<Problem> getForProblem()   {   return Optional.ofNullable(forProblem);}

    public void setForProblem ( Problem nForProblem) {   this.forProblem = nForProblem;}

    public Optional<Student> getToStudent() {   return Optional.ofNullable(toStudent);}

    public void settoStudent( Student newToStudent) {   this.toStudent = newToStudent;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade = (Grade) o;

        if (value != grade.value) return false;
        if (!toStudent.equals(grade.toStudent)) return false;
        return forProblem.equals(grade.forProblem);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "value=" + value +
                ", forProblem='" + forProblem.toString() + '\'' +
                ", toStudent='" + toStudent.toString() + '\'' +
                "} " + super.toString();
    }
}
