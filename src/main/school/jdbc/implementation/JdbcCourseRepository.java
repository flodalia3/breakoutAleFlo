package main.school.jdbc.implementation;

import main.school.data.DataException;
import main.school.data.abstractions.CourseRepository;
import main.school.data.abstractions.JdbcRepository;
import main.school.model.Course;

import java.util.Optional;

public class JdbcCourseRepository extends JdbcRepository implements CourseRepository {

    @Override
    public void addCourse(Course course) throws DataException {

    }

    @Override
    public Iterable<Course> getCoursesByTitleLike(String title) throws DataException {
        return null;
    }

    @Override
    public Iterable<Course> getAll(boolean orderByTitle) throws DataException {
        return null;
    }

    @Override
    public Optional<Course> findByID(long idCourse) {
        return Optional.empty();
    }

    @Override
    public void clear() {

    }
}
