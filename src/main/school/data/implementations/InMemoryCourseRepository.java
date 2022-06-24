package main.school.data.implementations;

import main.school.data.DataException;
import main.school.data.abstractions.CourseRepository;
import main.school.model.Course;
import main.school.model.Edition;
import main.school.model.Level;
import main.school.model.Sector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryCourseRepository implements CourseRepository {

    private static long courseId;

    private static HashMap<Long, Course> repoCourses = new HashMap<>();

    @Override
    public void addCourse(Course course) throws DataException {
        course.setId(++courseId);
        repoCourses.put(course.getId(), course);
    }



    @Override
    public List<Course> getCoursesByTitleLike(String title) throws DataException {
        List<Course> courses = new ArrayList<>();
        for (Course aCourse : repoCourses.values()) {
            if (aCourse.getTitle().toLowerCase().contains(title.toLowerCase())) {
                courses.add(aCourse);
            }
        }
        return courses;
    }

    @Override
    public List<Course> getAllCourses() throws DataException {
        return new ArrayList<Course>(repoCourses.values());
    }
}
