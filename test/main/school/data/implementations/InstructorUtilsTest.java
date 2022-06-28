package main.school.data.implementations;

import main.school.model.Instructor;
import main.school.model.Sector;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*; // import statico significa che importo uno o più metodi statici della
                                                  // classe Assertions
//import static java.lang.System.out; avendo fatto questo potremmo scrivere out.println invece di System.out.println...
class InstructorUtilsTest {

    @Test
    void fromTextLine() {
        String line1 = "1,prova,jUnit,2022-06-28,prova,0";
        Instructor i1 = InstructorUtils.fromTextLine(line1);
        assertEquals(1, i1.getId());
        assertEquals("jUnit", i1.getLastname());
        assertEquals(LocalDate.of(2022,6,28), i1.getDob());
        assertEquals(0, i1.getSpecialization().size());
        String line2 = "1,prova,jUnit,2022-06-28,prova,3,GRAPHICS,DEVELOPMENT,OFFICE";

        Instructor i2 = InstructorUtils.fromTextLine(line2);
        assertEquals(1, i2.getId());
        assertEquals("jUnit", i2.getLastname());
        assertEquals(LocalDate.of(2022,6,28), i2.getDob());
        assertEquals(3, i2.getSpecialization().size());
        assertEquals(Sector.GRAPHICS, i2.getSpecialization().get(0));
        assertEquals(Sector.DEVELOPMENT, i2.getSpecialization().get(1));
        assertEquals(Sector.OFFICE, i2.getSpecialization().get(2));
    }

    @Test
    void toTextLine() {
        //long id, String name, String lastname, LocalDate dob, String email, List<Sector> specialization
        Instructor i1 = new Instructor(1L, "prova", "jUnit", LocalDate.of(2022, 6, 28),"prova", new ArrayList<Sector>());
        String expected = "1,prova,jUnit,2022-06-28,prova,0";
        String actual = InstructorUtils.toTextLine(i1);
        assertEquals(expected, actual);
        Instructor i2 = new Instructor(1L, "prova", "jUnit", LocalDate.of(2022, 6, 28),
                                    "prova", new ArrayList<Sector>(List.of(Sector.GRAPHICS, Sector.OFFICE)));
        String expected2 = "1,prova,jUnit,2022-06-28,prova,2,GRAPHICS,OFFICE";
        String actual2 = InstructorUtils.toTextLine(i2);
        assertEquals(expected2, actual2);
    }
}