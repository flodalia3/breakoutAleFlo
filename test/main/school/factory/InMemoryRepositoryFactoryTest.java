package main.school.factory;

import main.school.data.abstractions.CourseRepository;
import main.school.data.abstractions.EditionRepository;
import main.school.data.abstractions.InstructorRepository;
import main.school.data.implementations.InMemoryCourseRepository;
import main.school.data.implementations.InMemoryEditionRepository;
import main.school.data.implementations.InMemoryInstructorRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositoryFactoryTest {
    RepositoryAbstractFactory r = new InMemoryRepositoryFactory();
    @Test
    public void inMemoryFactoryShouldCreateInMemCourseRepo(){
        CourseRepository cr = r.createCourseRepository();
        assertEquals(InMemoryCourseRepository.class, cr.getClass());
    }

    @Test
    public void inMemoryFactoryShouldCreateInMemEditionRepo(){
        EditionRepository er = r.createEditionRepository();
        assertEquals(InMemoryEditionRepository.class, er.getClass());
    }

    @Test
    public void inMemoryFactoryShouldCreateInMemInstructorRepo(){
        InstructorRepository ir = r.createInstructorRepository();
        assertEquals(InMemoryInstructorRepository.class, ir.getClass());
    }
}