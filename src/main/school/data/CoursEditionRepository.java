package main.school.data;

import main.school.model.CourseEdition;

import java.util.List;
import java.util.Optional;

public interface CoursEditionRepository {

    List<CourseEdition> getEditionsFromCourseId(long idCourse) throws DataException;
    Optional<CourseEdition> findCourseEditionById (long courseEditionId);

}
