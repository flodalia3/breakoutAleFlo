package main.school.data.implementations;

import main.school.data.DataException;
import main.school.data.abstractions.CourseRepository;
import main.school.model.Course;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCourseRepositoryTest {

    @Test
    void getAll_returns_courses_ordered_by_id_when_input_is_false() {
        CourseRepository mr = new InMemoryCourseRepository();
        Course c1 = new Course();
        c1.setId(1L);
        Course c2 = new Course();
        c2.setId(2L);
        Course c3 = new Course();
        c3.setId(3L);
        try {
            mr.addCourse(c3);
            mr.addCourse(c2);
            mr.addCourse(c1);
            Iterable<Course> ic = mr.getAll(false);
            int id = 1;
            for(Course c : ic) {
                assertEquals(id, c.getId());
                id++;
            }

        } catch (DataException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void getAll_returns_courses_ordered_by_title_when_input_is_true() {
        CourseRepository mr = new InMemoryCourseRepository();
        String[] titles = new String[]{"JavaForDummies","PerlIn4Hours","PythonForUncles"} ;
        Course c1 = new Course();
        c1.setId(1L);
        c1.setTitle(titles[2]);
        Course c2 = new Course();
        c2.setId(2L);
        c2.setTitle(titles[0]);
        Course c3 = new Course();
        c3.setId(3L);
        c3.setTitle(titles[1]);
        try {
            mr.addCourse(c3);
            mr.addCourse(c2);
            mr.addCourse(c1);
            Iterable<Course> ic = mr.getAll(true);
            int pos = 0;
            for(Course c : ic) {
                assertEquals(titles[pos], c.getTitle());
                pos++;
            }

        } catch (DataException e) {
            fail(e.getMessage());
        }
    }
}