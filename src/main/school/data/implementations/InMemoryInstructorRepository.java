package main.school.data.implementations;

import main.school.data.DataException;
import main.school.data.abstractions.InstructorRepository;
import main.school.model.Edition;
import main.school.model.Instructor;
import main.school.model.Level;
import main.school.model.Sector;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryInstructorRepository implements InstructorRepository {

    private static HashMap<Long, Instructor> repoInstructors = new HashMap<>();
    private static long instructorId;

    @Override
    public boolean instructorExists(long idInstructor) {
        Instructor instructor = repoInstructors.get(idInstructor);
        return instructor!=null;
    }

    @Override
    public List<Instructor> getInstructorsBornAfter(LocalDate date) throws DataException {
        List<Instructor> instructors = new ArrayList<>();
        for (Instructor instructor : repoInstructors.values()) {
            if (instructor.isBornAfter(date) && instructor.isSpecializedInMultipleSectors())
                instructors.add(instructor);
        }
        return instructors;
    }

    @Override
    public void addInstructor(Instructor instructor) throws DataException {
        instructor.setId(++instructorId);
        repoInstructors.put(instructor.getId(), instructor);

    }



    @Override
    public Optional<Instructor> findInstructorById(long instructorId) {
        Instructor i = repoInstructors.get(instructorId);
        return i!=null?Optional.of(i):Optional.empty();
    }

    @Override
    public List<Instructor> findByAgeGreaterThenAndMoreOneSpecialization(int age) {
        List<Instructor> listInstructor = new ArrayList<>();
        for(Instructor i : repoInstructors.values()) {
            if(i.isMajorThan(age)&&i.isSpecializedInMultipleSectors()) {
                listInstructor.add(i);
            }
        }
        return listInstructor;
    }
}
