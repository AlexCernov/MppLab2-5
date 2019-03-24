package domain.validators;

import domain.Student;

import java.util.HashMap;
import java.util.Optional;

public class StudentValidator implements Validator<Student> {
    private String errorMessage;
    @Override
    public void validate(Student entity) throws ValidatorException {
        this.errorMessage = "";
        HashMap<String, String> userMessages = new HashMap<String, String>() {
            {
                put("name", "Student's name is too short.\n");
                put("serialNumber", "Student's serial number is not right.\n");
                put("group","Student's group is not right.\n");
            }
        };

        Optional<Student> opt = Optional.of(entity);
        opt.ifPresent(
                currentStudent -> {
                    currentStudent.getSerialNumber().ifPresentOrElse(
                            serialNumber -> {
                                if ( serialNumber.length() != 4 )
                                    this.errorMessage += userMessages.get("serialNumber");
                            },
                            () -> this.errorMessage += "Student's serial number is null.\n"
                    );
                    currentStudent.getName().ifPresentOrElse(
                            name -> {
                                if ( name.length() < 3 )
                                    this.errorMessage += userMessages.get("name");
                            },
                            () -> this.errorMessage += "Student's name is null.\n"
                    );
                    currentStudent.getGroup().ifPresentOrElse(
                            group -> {
                                if ( group < 100 || group > 999 )
                                    this.errorMessage += userMessages.get("group");
                            },
                            () -> this.errorMessage += "Student's group is null.\n"
                    );


                }
        );

        if ( !this.errorMessage.equals("") )
            throw new ValidatorException(this.errorMessage);
    }
    }

