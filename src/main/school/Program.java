package main.school;

import main.school.data.DataException;
import main.school.data.abstractions.CourseRepository;
import main.school.data.abstractions.EditionRepository;
import main.school.data.abstractions.InstructorRepository;
import main.school.data.implementations.InMemoryCourseRepository;
import main.school.data.implementations.InMemoryEditionRepository;
import main.school.data.implementations.InMemoryInstructorRepository;
import main.school.model.*;
import main.school.services.AbstractSchoolService;
import main.school.services.InMemorySchoolService;
import main.school.services.TextFileSchoolService;
import main.school.ui.Console;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        //vuoi lavorare solo in memoria o salvare i dati su file di testo m: memoria, f: file

        //legge la risposta
        //se scelgo m l'applicazione lavora solo in memoria usando solo schoolService
        //se scelgo file fa quello che stà facendo ora lavorando con TexFileSchoolService
        //dobbiamo capirne il perchè di Dependency Injection!!!

        CourseRepository cr = new InMemoryCourseRepository();
        EditionRepository er = new InMemoryEditionRepository();
        InstructorRepository ir = new InMemoryInstructorRepository();
        //AbstractSchoolService asc = new SchoolService(cr, er, ir);


        Course c1 = new Course("ciccio",120, Sector.GRAPHICS, Level.ADVANCED);
        Course c2 = new Course("pippo",200, Sector.OFFICE, Level.GURU);


        Instructor i1 = new Instructor("caio", "asdknais",
                                        LocalDate.of(1995,02,25),
                                        "huhisa", new ArrayList<Sector>(List.of(Sector.OFFICE, Sector.GRAPHICS)));

        Edition e1 = new Edition(0L, c1, LocalDate.of(1975,05,20),
                                    LocalDate.of(2000,05,20),
                                   500.0, i1);

        try {
            AbstractSchoolService asc = null;
            System.out.println("Choose M: to work in memory, F: to work in file.");
            Scanner s = new Scanner(System.in);
            String response = s.nextLine();
            //s.close(); tenta di chiudere la sorgente di input da cui leggeva
            if (response.equals("M")) {
                asc = new InMemorySchoolService(cr, er, ir);
            } else if (response.equals("F")) {
                asc = new TextFileSchoolService(cr, er, ir);
            } else {
                System.out.println("Moron I said M o F try again!!");
                return;
            }


            //cr.addCourse(c1);
            //cr.addCourse(c2);

//            ir.addInstructor(i1);
//
//            er.addCourseEdition(e1);

            Console c = new Console(asc);
            c.start();
        } catch (DataException e) {
            System.out.println("Unable to initialize data.");
            System.out.println(e.getMessage());
            return;
        }
    }
}
