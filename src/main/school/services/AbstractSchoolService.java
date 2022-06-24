package main.school.services;
import main.school.data.*;
import main.school.data.abstractions.EditionRepository;
import main.school.data.abstractions.CourseRepository;
import main.school.data.abstractions.InstructorRepository;
import main.school.model.EntityNotFoundException;

public interface AbstractSchoolService {
    EditionRepository getCourseEditionRepository();
    InstructorRepository getInstructorRepository ();
    CourseRepository getCourseRepository ();
    void addOrReplaceInstructorToCourseEdition (long courseEditionId, long instructorID) throws DataException, EntityNotFoundException;

}
