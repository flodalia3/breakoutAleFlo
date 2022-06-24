package main.school.services;

import main.school.data.CoursEditionRepository;
import main.school.data.CourseRepository;
import main.school.data.DataException;
import main.school.data.InstructorRepository;
import main.school.model.CourseEdition;
import main.school.model.EntityNotFoundException;
import main.school.model.Instructor;

import java.util.Optional;

public class SchoolService implements AbstractSchoolService{
    private CoursEditionRepository editionRepo;
    private CourseRepository courseRepo;
    private InstructorRepository instructorRepo;

    // stiamo facendo iniezione delle dipendenze per realizzare il pattern Inversione delle Dipendenze
    // guardare video sui design pattern SOLID dello "Zio Bob", in particolare "D" come Dependency Inversion
    // spiegare lunedì...
    public SchoolService(CourseRepository courseRepo, CoursEditionRepository coursEditionRepo, InstructorRepository instructorRepo) {
        this.courseRepo = courseRepo;
        this.courseRepo = courseRepo;
        this.instructorRepo = instructorRepo;
    }

    @Override
    public CoursEditionRepository getCourseEditionRepository() {
        return editionRepo;
    }

    @Override
    public InstructorRepository getInstructorRepository() {
        return instructorRepo;
    }

    @Override
    public CourseRepository getCourseRepository() {
        return courseRepo;
    }

    @Override
    public void addOrReplaceInstructorToCourseEdition(long courseEditionId, long instructorID) throws DataException, EntityNotFoundException {
        Optional<CourseEdition> oce = editionRepo.findCourseEditionById(courseEditionId);
        if(oce.isEmpty()) {
            throw new EntityNotFoundException(String.format("CourseEdition with id %d not found.", courseEditionId), courseEditionId);
        }
        Optional<Instructor> oi = instructorRepo.findInstructorById(instructorID);
        if(oi.isEmpty()) {
            throw new EntityNotFoundException(String.format("Instructor with id %d not found.", instructorID), instructorID);
        }
    }
}
