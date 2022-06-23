package main.school.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.school.model.Course;
import main.school.model.CourseEdition;
import main.school.model.Instructor;
import main.school.model.Level;
import main.school.model.Sector;

public class SchoolRepository {
    private HashMap<Long, Course> repoCourses = new HashMap<>();
    private HashMap<Long, Instructor> repoInstructors = new HashMap<>();
    private HashMap<Long, CourseEdition> repoEditions = new HashMap<>();

    public List<Course> getAllCourses() {
        return new ArrayList<Course>(getRepoCourses().values());
    }

    public List<CourseEdition> getEditionsFromCourseId(long idCourse) {
        List<CourseEdition> editionsOfCourse = new ArrayList<>();
        for (CourseEdition aCourseEdition : getRepoEditions().values()) {
            if (aCourseEdition.getCourse().getId() == idCourse) {
                editionsOfCourse.add(aCourseEdition);
            }
        }
        return editionsOfCourse;
    }

    public List<Course> getCourseFromString(String string) {
        List<Course> courses = new ArrayList<>();
        for (Course aCourse : getRepoCourses().values()) {
            if (aCourse.getTitle().toLowerCase().contains(string.toLowerCase())) {
                courses.add(aCourse);
            }
        }
        return courses;
    }

    public List<Instructor> getInstructorFromSectorAndLevel(Sector sector, Level level) {
        List<Instructor> instructors = new ArrayList<>();
        for (CourseEdition aCourseEdition : getRepoEditions().values()) {
            if (aCourseEdition.getCourse().getLevel().equals(level)
                    && aCourseEdition.getCourse().getSector().equals(sector)) {
                instructors.add(aCourseEdition.getInstructor());
            }

        }
        return instructors;
    }

    public List<Instructor> getInstructorsBornAfter(LocalDate date) {
        List<Instructor> instructors = new ArrayList<>();
        for (Instructor instructor : getRepoInstructors().values()) {
            if (isBornAfter(instructor, date) && isSpecializedInMultipleSectors(instructor))
                instructors.add(instructor);
        }
        return instructors;

    }

    private boolean isBornAfter(Instructor instructor, LocalDate date) {
        return instructor.getDob().isAfter(date);
    }

    private boolean isSpecializedInMultipleSectors(Instructor instructor) {
        return instructor.getSpecialization().size() > 1;
    }

    public void addInstructor(Instructor instructor) {
        repoInstructors.put(instructor.getId(), instructor);
    }

    public void addOrReplaceInstructor(CourseEdition courseEdition, long idInstructor) {
        if (instructorExists(idInstructor))
            courseEdition.setInstructor(repoInstructors.get(idInstructor));
    }

    private boolean instructorExists(long idInstructor) {
        Instructor instructor = repoInstructors.get(idInstructor);
        if (instructor == null)
            return false;
        return true;
    }

    public HashMap<Long, CourseEdition> getRepoEditions() {
        return repoEditions;
    }

    public HashMap<Long, Instructor> getRepoInstructors() {
        return repoInstructors;
    }

    public HashMap<Long, Course> getRepoCourses() {
        return repoCourses;
    }

    public void addCourseToRepo(Course course) {
        repoCourses.put(course.getId(), course);
    }

    public void addInstructorToRepo(Instructor Instructor) {
        repoInstructors.put(Instructor.getId(), Instructor);
    }

    public void addCourseEditionToRepo(CourseEdition CourseEdition) {
        repoEditions.put(CourseEdition.getId(), CourseEdition);
    }

}
