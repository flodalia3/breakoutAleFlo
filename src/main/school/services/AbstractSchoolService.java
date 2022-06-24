package main.school.services;
import main.school.data.*;
import main.school.model.CourseEdition;
import main.school.model.EntityNotFoundException;

public interface AbstractSchoolService {
    CoursEditionRepository getCourseEditionRepository();
    InstructorRepository getInstructorRepository ();
    CourseRepository getCourseRepository ();
    void addOrReplaceInstructorToCourseEdition (long courseEditionId, long instructorID) throws DataException, EntityNotFoundException;

}
