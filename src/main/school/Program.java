package main.school;

import main.school.data.abstractions.CourseRepository;
import main.school.data.abstractions.EditionRepository;
import main.school.data.abstractions.InstructorRepository;
import main.school.data.implementations.InMemoryCourseRepository;
import main.school.data.implementations.InMemoryEditionRepository;
import main.school.data.implementations.InMemoryInstructorRepository;
import main.school.factory.InMemoryRepositoryFactory;
import main.school.factory.RepositoryAbstractFactory;
import main.school.ui.Console;

public class Program {

    public static void main(String[] args) {

        //CourseRepository cr = new InMemoryCourseRepository();
        //EditionRepository er = new InMemoryEditionRepository();
        //InstructorRepository ir = new InMemoryInstructorRepository();

        Console.chooseFactories();
        Console c = new Console();
        //c.chooseService();

        c.start();
    }
}
