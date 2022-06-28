package main.school.ui;

import java.time.LocalDate;
import java.util.*;

import main.school.data.DataException;
import main.school.model.*;
import main.school.services.AbstractSchoolService;

public class Console {
    private Scanner sc;
    private AbstractSchoolService schoolService;

    public Console(AbstractSchoolService ss) {
        sc = new Scanner(System.in);
        this.schoolService = ss;
    }
    public Console() {
        sc = new Scanner(System.in);

    }
    //    InMemorySchoolRepository schoolRepository = new InMemorySchoolRepository();
    public void start (){
        try {
            run();
        } catch (DataException e) {
            System.out.println(e.getMessage());
            System.out.println("Closing program");
        }
    }
    public void run() throws DataException {
        while (true) {
            menu();
            String input = sc.nextLine();
            switch (input) {
                case "0":
                    printAllCourses();
                    break;
                case "1":
                    retrieveCourseEditionFromCourseId();
                    break;
                case "2":
                    retrieveCourseFromTitleLike();
                    break;
                case "3":
                    retrieveInstructorsFromSectorAndLevel();
                    break;
                case "4":
                    retrieveInstructorByAgeAndDoubleSpecialized();
                    break;
                case "5":
                    addInstructor();
                    break;
                case "6":
                    assignInstructor ();
                    break;
                case "7":
                    addNewCourse ();
                    break;
                case "8":
                    printAllInstructors();
                    break;
                case "9":
                    addNewEdition();
                    break;
                case "exit":
                    return;

                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
    // long idCourse, LocalDate startDate, LocalDate endDate, double cost, long idInstructor
    private void addNewEdition() throws DataException {
        long idCourse = readInt("Please insert Course id: ");
        LocalDate startDate = readDate("Please insert a start date: ");
        LocalDate endDate = readDate( "Please insert a end date: ");
        double cost = readDouble("Please insert a cost: ");
        long idInstructor = readInt("Please insert Instructor id: ");
        Course c = new Course();
        c.setId(idCourse);
        Instructor i = new Instructor();
        i.setId(idInstructor);
        Edition e = new Edition(0L, c, startDate, endDate, cost, i);
        schoolService.addEdition(e);
    }

    private void printAllInstructors() throws DataException {
        for (Instructor i : schoolService.getInstructorRepository().getAll()
            //for (Instructor i : schoolService.getAllInstructors()){...} renderebbe il servizio + rumoroso
                //dovendo scrivere per ogni istruzione CRUD un metodo
        ) {
            System.out.println(i);
        }
    }

    private void addNewCourse() throws DataException {
        String title = readLine("Please insert Course title:");
        int hours = readInt("Please insert Course hours:");
        Sector sector = readSector();
        Level level = readLevel();
        Course c = new Course(title, hours, sector, level);
        this.schoolService.getCourseRepository().addCourse(c);
        this.schoolService.commit();
    }
    private double readDouble (String prompt) {
        System.out.println(prompt);
        return Double.parseDouble(sc.nextLine());
    }
    private LocalDate readDate (String prompt) {
        System.out.println(prompt);
        return LocalDate.parse(sc.nextLine());
    }
    private String readLine (String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
    private int readInt (String prompt) {
        System.out.println(prompt);
        int i = sc.nextInt();
        sc.nextLine();
        return i;
    }
    private Sector readSector () {
        System.out.println("Please insert sector (GRAPHICS, OFFICE, DEVELOPMENT)");

        String line = sc.nextLine();
        return Sector.valueOf(line);
    }
    private Level readLevel () {
        System.out.println("Please insert level (BASIC, ADVANCED, GURU)");
        String line = sc.nextLine();
        return Level.valueOf(line);
    }
    private void assignInstructor () throws DataException {
        System.out.println("Please insert id Instructor: ");
        long instructorId = sc.nextLong();
        System.out.println("Please insert course edition id: ");
        long editionId = sc.nextLong();
        sc.nextLine(); //serve per eliminare il return rimasto in Input buffer
        try{
            schoolService.addOrReplaceInstructorToCourseEdition(editionId, instructorId);
            schoolService.commit();//dopo aver fatto qualcosa vuole confermare di aver apportato la modifica
            System.out.println("Instructor assigned.");
        }catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
        }
    }
    private void printAllCourses() throws DataException {
        for (Course course : schoolService.getCourseRepository().getAll()) {
            System.out.println(course.toString());
        }
    }
    private void retrieveCourseEditionFromCourseId() throws DataException {
        long id = 0L; //costante long
        boolean isValidId = false;
        do {
            System.out.println("Insert course id (long) to search editions:");
            if (sc.hasNextLong()) {
                id = sc.nextLong();
                sc.nextLine();
                isValidId = true;
            } else {
                sc.nextLine();
                System.out.println("Enter a valid Long value");
            }
        } while (!isValidId);
        for (Edition courseEdition : schoolService.getCourseEditionRepository().getEditionsFromCourseId(id)) {
            System.out.println(courseEdition.toString());
        }

    }

    private void retrieveCourseFromTitleLike() throws DataException {
        System.out.println("Insert word to find relative courses:");
        String keyword = sc.next();
        for (Course course : schoolService.getCourseRepository().getCoursesByTitleLike(keyword)) {
            System.out.println(course.toString());
        }

    }

    private void retrieveInstructorsFromSectorAndLevel() throws DataException {
        System.out.println("Insert 0 for Graphics");
        System.out.println("Insert 1 for Office");
        System.out.println("Insert 2 for Development");
        Sector sector = null;
        switch (sc.next()) {
            case "0":
                sector = Sector.GRAPHICS;
                break;
            case "1":
                sector = Sector.OFFICE;
                break;
            case "2":
                sector = Sector.DEVELOPMENT;
                break;

            default:
                System.out.println("Invalid input, try again you dumb bitch");
                retrieveInstructorsFromSectorAndLevel();
                return ;
        }
        System.out.println("Insert 0 for Basic");
        System.out.println("Insert 1 for Advanced");
        System.out.println("Insert 2 for Guru");
        Level level = null;
        switch (sc.next()) {
            case "0":
                level = Level.BASIC;
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
                return ;
        }

        for (Instructor instructor : schoolService.getCourseEditionRepository().getInstructorFromSectorAndLevel(sector, level)) {
            System.out.println(instructor);
        }
    }

    private void retrieveInstructorByAgeAndDoubleSpecialized() {
        System.out.println("what is the minimum instractor age: ");
        int age = sc.nextInt();
        sc.nextLine();
        for (Instructor instructor: schoolService.getInstructorRepository().findByAgeGreaterThenAndMoreOneSpecialization(age)) {
            System.out.println(instructor);
        }

    }

    private void addInstructor() throws DataException {
        System.out.println("Please insert Instructor name: ");
        String name = sc.nextLine();
        System.out.println("Please insert Instructorn lastname: ");
        String lastname = sc.nextLine();
        System.out.println("Please insert Date of Born Instructor 'yyyy-mm-dd': ");
        String dateString = sc.nextLine();
        LocalDate dob = LocalDate.parse(dateString);
        System.out.println("Please insert Instructor email: ");
        String email = sc.nextLine();
        boolean hasMoreSpecialization = true;
        Set<Sector> sectorsSet = new HashSet<>();
        outer: do {
            System.out.println("Please insert Specialization (g: graphics, d: development, o: office): , q: quit");
            String answer = sc.nextLine();
            Sector s = null;
            switch (answer) {
                case "g":
                    s = Sector.GRAPHICS;
                    break;
                case "d":
                    s = Sector.DEVELOPMENT;
                    break;
                case "o":
                    s = Sector.OFFICE;
                    break;
                case "q":
                    break outer;
                default:
                    System.out.println("Invalid response");
                    continue;
            }
            boolean wasAbsent = sectorsSet.add(s);
            if (!wasAbsent) {
                System.out.println("You can't add the same specialization twiece.");
            }
        } while (sectorsSet.size() < Sector.values().length);
        Instructor i = new Instructor(name, lastname, dob, email, new ArrayList<>(sectorsSet));
        schoolService.getInstructorRepository().addInstructor(i);
        schoolService.commit();
    }
    private void menu() {
        System.out.println("Type 0 to print all courses");
        System.out.println("Type 1 to retrieve all course editions for a given course id");
        System.out.println("Type 2 to retrieve all courses that contain a given word in the title");
        System.out.println("Type 3 to retrieve all instructors that teach in a given sector at a given level");
        System.out.println("Type 4 to print list of instructors who are born after a certain date and " +
                           "are specialised in two sectors");
        System.out.println("Type 5 to add a new instructor");
        System.out.println("Type 6 to assign an instructor to a course edition");
        System.out.println("Type 7 to add a new Course");
        System.out.println("Type 8 to show all Instructors");
        System.out.println("Type 9 to add new Edition");
        System.out.println("Type exit to close the console");
    }

}
