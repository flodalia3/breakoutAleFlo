package main.school.data;
// Classi che gestiscono la persistenza dei dati (upgrade delle DataAccesObject)
// separa le funzionalità attribuite ad una classe (Single Responsability Principle)
import java.time.LocalDate;
import java.util.*;

import main.school.model.*;

public class InMemorySchoolRepository {
    private HashMap<Long, Course> repoCourses = new HashMap<>();
    private HashMap<Long, Instructor> repoInstructors = new HashMap<>();
    private HashMap<Long, CourseEdition> repoEditions = new HashMap<>();

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<Course>(repoCourses.values());
    }

    @Override
    public List<CourseEdition> getEditionsFromCourseId(long idCourse) {
        List<CourseEdition> editionsOfCourse = new ArrayList<>();
        for (CourseEdition aCourseEdition : repoEditions.values()) {
            if (aCourseEdition.getCourse().getId() == idCourse) {
                editionsOfCourse.add(aCourseEdition);
            }
        }
        return editionsOfCourse;
    }

    @Override
    public List<Course> getCourseFromString(String string) {
        List<Course> courses = new ArrayList<>();
        for (Course aCourse : repoCourses.values()) {
            if (aCourse.getTitle().toLowerCase().contains(string.toLowerCase())) {
                courses.add(aCourse);
            }
        }
        return courses;
    }

    @Override
    public List<Instructor> getInstructorFromSectorAndLevel(Sector sector, Level level) {
        List<Instructor> instructors = new ArrayList<>();
        for (CourseEdition aCourseEdition : repoEditions.values()) {
            if (aCourseEdition.getCourse().getLevel().equals(level)
                    && aCourseEdition.getCourse().getSector().equals(sector)) {
                instructors.add(aCourseEdition.getInstructor());
            }

        }
        return instructors;
    }

    @Override
    public List<Instructor> getInstructorsBornAfter(LocalDate date) {
        List<Instructor> instructors = new ArrayList<>();
        for (Instructor instructor : repoInstructors.values()) {
            if (instructor.isBornAfter(date) && instructor.isSpecializedInMultipleSectors())
                instructors.add(instructor);
        }
        return instructors;
    }



    @Override
    public void addInstructor(Instructor instructor) {
        repoInstructors.put(instructor.getId(), instructor);
    }

    @Override
    public void addOrReplaceInstructor(long courseEditionId, long instructorId) throws EntityNotFoundException {
        Optional<Instructor> oi = findInstructorById(instructorId);
        if (oi.isEmpty()) {
            throw new EntityNotFoundException(String.format("Instructor with id %d does not exist", instructorId), instructorId);
        }
        Optional<CourseEdition> oce = findCourseEditionById(courseEditionId);
        if(oce.isEmpty()) {
            throw new EntityNotFoundException(String.format("CourseEdition with id %d does not exist", courseEditionId), courseEditionId);
        }
        Instructor i = oi.get(); //estrae dall'Optional
        CourseEdition ce = oce.get();
        ce.setInstructor(i);
    }

    private boolean instructorExists(long idInstructor) {
        Instructor instructor = repoInstructors.get(idInstructor);
        return instructor!=null;
    }

    @Override
    public void addCourseToRepo(Course course) {
        repoCourses.put(course.getId(), course);
    }

    @Override
    public void addInstructorToRepo(Instructor Instructor) {
        repoInstructors.put(Instructor.getId(), Instructor);
    }

    @Override
    public void addCourseEditionToRepo(CourseEdition CourseEdition) {
        repoEditions.put(CourseEdition.getId(), CourseEdition);
    }

    @Override
    public Optional<Instructor> findInstructorById(long instructorId) {
        Instructor i = repoInstructors.get(instructorId);
        return i!=null?Optional.of(i):Optional.empty();
    }

    @Override
    public Optional<CourseEdition> findCourseEditionById(long courseEditionId) {
        CourseEdition ce = repoEditions.get(courseEditionId);
        return ce!=null?Optional.of(ce):Optional.empty();
    }

}
