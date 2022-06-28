package main.school.data.implementations;

import main.school.model.Course;
import main.school.model.Edition;
import main.school.model.Instructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EditionUtilsTest {

    @Test
    void toTextLine() {
        //long id, long idCourse, LocalDate startDate, LocalDate endDate, double cost, long idInstructor
        Course c = new Course();
        c.setId(1);
        Instructor i = new Instructor();
        i.setId(3);
        Edition e = new Edition(2, c, LocalDate.of(2022,5,27),
                LocalDate.of(2022,6,30),5.0, i);
        String expected = "2,1,2022-05-27,2022-06-30,5.00,3";
        String actual = EditionUtils.toTextLine(e);
        assertEquals(expected, actual);
    }

    @Test
    void fromTextLine() {
        String line = "2,1,2022-05-27,2022-06-30,5.00,3";
        Edition e = EditionUtils.fromTextLine(line);

        assertEquals(2, e.getId());
        assertEquals(LocalDate.of(2022,6,30), e.getEndDate());
        assertEquals(1, e.getCourse().getId());
        assertEquals(3, e.getInstructor().getId());

    }
}