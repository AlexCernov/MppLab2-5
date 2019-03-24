package domain;

import java.util.Optional;
import domain.Student;

public class Problem extends BaseEntity<Long>{
    private int problemNo;
    private String problemStatement;
    private int problemPoints;

    public Problem(){}

    public Problem(int problemNo, String problemStatement, int problemPoints){
        this.problemNo = problemNo;
        this.problemStatement = problemStatement;
        this.problemPoints = problemPoints;
    }

    public Optional<Integer> getProblemNo() {   return Optional.ofNullable(problemNo);}

    public void setProblemNo( int nProblemNo )  {   this.problemNo = nProblemNo;}

    public Optional<String> getProblemStatement()   {   return Optional.ofNullable(problemStatement);}

    public void setProblemStatement ( String nProblemStatement) {   this.problemStatement = nProblemStatement;}

    public Optional<Integer> getProblemPoints() {   return Optional.ofNullable(problemPoints);}

    public void setAssignedToStudent( int newProblemPoints) {   this.problemPoints = newProblemPoints;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Problem problem = (Problem) o;

        if (problemNo != problem.problemNo) return false;
        if (problemPoints != problem.problemPoints) return false;
        return problemStatement.equals(problem.problemStatement);
    }

    @Override
    public String toString() {
        return "Problem{" +
                "problemNo=" + problemNo +
                ", problemStatement='" + problemStatement + '\'' +
                ", problemPoints=" + problemPoints + '\'' +
                "} " + super.toString();
    }
}
