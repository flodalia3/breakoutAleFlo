package main.school.data.implementations;

import main.school.model.*;

import java.time.LocalDate;
import java.util.Locale;

public class EditionUtils {

    public static String toTextLine(Edition e) { // %.2f dammelo con 2 cifre decimali
        return String.format(Locale.ENGLISH,"%d,%d,%s,%s,%.2f,%d", e.getId(), e.getCourse().getId(), e.getStartDate(), e.getEndDate(),
                             e.getCost(),e.getInstructor().getId());
        //long id, long idCourse, LocalDate startDate, LocalDate endDate, double cost, long idInstructor
    }
    public static Edition fromTextLine (String line) {
        var sArray = line.split(",");//leggo dal file
        Course course = new Course();
        course.setId(Long.parseLong(sArray[1])); //creo un corso settando solo l'id
        Instructor instructor = new Instructor();
        instructor.setId(Long.parseLong(sArray[5])); //creo un Instructor settando solo l'id
        return new Edition(Long.parseLong(sArray[0]), course, LocalDate.parse(sArray[2]), LocalDate.parse(sArray[3]),
                            Double.parseDouble(sArray[4]), instructor); //creo l'edition
    }
}
