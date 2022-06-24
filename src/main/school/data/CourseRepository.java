package main.school.data;

import main.school.model.Course;
import main.school.model.CourseEdition;

import java.util.List;

public interface CourseRepository {
    void addCourseToRepo(Course course)throws DataException;
    void addCourseEditionToRepo(CourseEdition CourseEdition)throws DataException;
    List<Course> getCoursesByTitleLike(String title) throws DataException;
    List<Course> getAllCourses() throws DataException;
}
