package main.school.data.abstractions;

import main.school.data.DataException;
import main.school.model.Instructor;
import main.school.model.Level;
import main.school.model.Sector;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InstructorRepository {

    boolean instructorExists(long idInstructor);
    List<Instructor> getInstructorsBornAfter(LocalDate date)throws DataException;

    void addInstructor(Instructor instructor) throws DataException;


    //scatoletta che forse contiene o meno un Instructor
    Optional<Instructor> findInstructorById (long instructorId);

    List<Instructor> findByAgeGreaterThenAndMoreOneSpecialization (int age);

}
