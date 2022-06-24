package main.school.ui;

import java.util.Scanner;

import main.school.data.InMemorySchoolRepository;
import main.school.model.Course;
import main.school.model.CourseEdition;
import main.school.model.Instructor;
import main.school.model.Level;
import main.school.model.Sector;

public class Console {
    public Scanner sc = new Scanner(System.in);
    InMemorySchoolRepository schoolRepository = new InMemorySchoolRepository();

    public void run() {
        while (true) {
            menu();
            String input = sc.next();
            switch (input) {
                case "0":
                    printAllCourses();
                    break;
                case "1":
                    retrieveCourseEditionFromCourseId();
                    break;
                case "2":
                    retrieveCourseFromString();
                    break;
                case "3":
                    retrieveInstructorsFromSectorAndLevel();
                    break;
                case "4":

                    break;
                case "5":

                    break;
                case "6":

                    break;

                case "exit":

                    return;

                default:
                    break;
            }
        }
    }

    private void printAllCourses() {
        for (Course course : schoolRepository.getAllCourses()) {
            System.out.println(course.toString());
        }
    }

    private void retrieveCourseEditionFromCourseId() {
        Long id = (long) 0;
        boolean isValidId = false;
        do {
            System.out.println("Insert course id (long) to search editions:");
            if (sc.hasNextLong()) {
                id = sc.nextLong();
                isValidId = true;
            } else {
                sc.nextLine();
                System.out.println("Enter a valid Long value");
            }
        } while (!isValidId);
        for (CourseEdition courseEdition : schoolRepository.getEditionsFromCourseId(id)) {
            System.out.println(courseEdition.toString());
        }

    }

    private void retrieveCourseFromString() {
        System.out.println("Insert word to find relative courses:");
        String keyword = sc.next();
        for (Course course : schoolRepository.getCourseFromString(keyword)) {
            System.out.println(course.toString());
        }

    }

    private void retrieveInstructorsFromSectorAndLevel() {
        System.out.println("Insert 0 for Graphics");
        System.out.println("Insert 1 for Office");
        System.out.println("Insert 2 for Development");
        Sector sector = Sector.DEVELOPMENT;
        switch (sc.next()) {
            case "0":
                sector = Sector.GRAPHICS;
                break;
            case "1":
                sector = Sector.OFFICE;
                break;
            case "2":
                break;

            default:
                System.out.println("Invalid input, try again you dumb bitch");
                retrieveInstructorsFromSectorAndLevel();
        }
        System.out.println("Insert 0 for Basic");
        System.out.println("Insert 0 for Advanced");
        System.out.println("Insert 0 for Guru");
        Level level = Level.BASIC;
        switch (sc.next()) {
            case "0":
                break;
            case "1":
                level = Level.ADVANCED;
                break;
            case "2":
                level = Level.GURU;
                break;

            default:
                System.out.println("Invalid input, try again you dumb bitch");
                retrieveInstructorsFromSectorAndLevel();
        }

        for (Instructor instructor : schoolRepository.getInstructorFromSectorAndLevel(sector, level)) {
            System.out.println(instructor.toString());
        }

    }

    private void retrieveInstructorByAgeAndDoubleSpecialized() {
        System.out.println("Insert a valid date of birth: ");
    }

    private void addInstructor() {

    }

    private void assignInstructorToCourseEdition() {
    }

    private void menu() {
        System.out.println("Type 0 to print all courses");
        System.out.println("Type 1 to retrieve all course editions for a given course id");
        System.out.println("Type 2 to retrieve all courses that contain a given word in the title");
        System.out.println("Type 3 to retrieve all instructors that teach in a given sector at a given level");
        System.out.println(
                "Type 4 to print list of instructors who are born after a certain date and are specialised in two sectors");
        System.out.println("Type 5 to add a new instructor");
        System.out.println("Type 6 to assign an instructor to a course edition");
        System.out.println("Type exit to close the console");
    }

}
