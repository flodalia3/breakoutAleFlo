package main.school.data;

import main.school.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class JDBCSchoolRepository {

    @Override
    public List<Course> getAllCourses() throws DataException {
        try {
            Connection c = DriverManager.getConnection("");
        } catch (SQLException e) {
            throw new DataException("Error to right DB", e);// "e" è la causa vera
        }
        return null;
    }

    @Override
    public List<CourseEdition> getEditionsFromCourseId(long idCourse) {
        return null;
    }

    @Override
    public List<Course> getCourseFromString(String string) {
        return null;
    }

    @Override
    public List<Instructor> getInstructorFromSectorAndLevel(Sector sector, Level level) {
        return null;
    }

    @Override
    public List<Instructor> getInstructorsBornAfter(LocalDate date) {
        return null;
    }

    @Override
    public void addInstructor(Instructor instructor) {

    }

    @Override
    public void addOrReplaceInstructor(long courseEditionId, long idInstructor){

    }

    @Override
    public HashMap<Long, CourseEdition> getRepoEditions() {
        return null;
    }

    @Override
    public HashMap<Long, Instructor> getRepoInstructors() {
        return null;
    }

    @Override
    public HashMap<Long, Course> getRepoCourses() {
        return null;
    }

    @Override
    public void addCourseToRepo(Course course) {

    }

    @Override
    public void addInstructorToRepo(Instructor Instructor) {

    }

    @Override
    public void addCourseEditionToRepo(CourseEdition CourseEdition) {

    }
}
