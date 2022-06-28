package main.school.data.abstractions;

import main.school.data.DataException;
import main.school.model.Instructor;

import java.time.LocalDate;
import java.util.Optional;

public interface InstructorRepository {

    boolean instructorExists(long idInstructor);
    Iterable<Instructor> getInstructorsBornAfter(LocalDate date)throws DataException;
    void addInstructor(Instructor instructor) throws DataException;
    Iterable<Instructor> getAll() throws DataException;
    //scatoletta che forse contiene o meno un Instructor
    Optional<Instructor> findById(long instructorId);
    Iterable<Instructor> findByAgeGreaterThenAndMoreOneSpecialization (int age);
    void clear ();

}
