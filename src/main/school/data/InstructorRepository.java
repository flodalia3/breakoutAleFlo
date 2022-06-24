package main.school.data;

import main.school.model.Instructor;
import main.school.model.Level;
import main.school.model.Sector;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InstructorRepository {

    List<Instructor> getInstructorFromSectorAndLevel(Sector sector, Level level) throws DataException;

    List<Instructor> getInstructorsBornAfter(LocalDate date)throws DataException;

    void addInstructor(Instructor instructor) throws DataException;

    void addInstructorToRepo(Instructor Instructor)throws DataException;

    //scatoletta che forse contiene o meno un Instructor
    Optional<Instructor> findInstructorById (long instructorId);

}
