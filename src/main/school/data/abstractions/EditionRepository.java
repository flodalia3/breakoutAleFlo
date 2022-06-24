package main.school.data.abstractions;

import main.school.data.DataException;
import main.school.model.Edition;
import main.school.model.Instructor;
import main.school.model.Level;
import main.school.model.Sector;

import java.util.List;
import java.util.Optional;

public interface EditionRepository {

    public List<Instructor> getInstructorFromSectorAndLevel(Sector sector, Level level) throws DataException;
    void addCourseEdition(Edition CourseEdition)throws DataException;
    List<Edition> getEditionsFromCourseId(long idCourse) throws DataException;
    Optional<Edition> findCourseEditionById (long courseEditionId);

}
