package main.school.jdbc.implementation;

import main.school.data.DataException;
import main.school.data.abstractions.InstructorRepository;
import main.school.data.abstractions.JdbcRepository;
import main.school.model.Instructor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class JdbcInstructorRepository extends JdbcRepository implements InstructorRepository {

    @Override
    public boolean instructorExists(long idInstructor) {
        return false;
    }

    @Override
    public Iterable<Instructor> getInstructorsBornAfterDateAndMultiSpecialized(LocalDate date) throws DataException {
        return null;
    }

    @Override
    public void addInstructor(Instructor instructor) throws DataException {

    }

    @Override
    public Iterable<Instructor> getAll() throws DataException {
        //FASE 1
        String query = "";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
        //FASE 2 APPLICARE IL TEMPLATE PATTERN
    }

    @Override
    public Optional<Instructor> findById(long instructorId) {
        return Optional.empty();
    }

    @Override
    public Iterable<Instructor> findOlderThanGivenAgeAndMoreThanOneSpecialization(int age) {
        return null;
    }

    @Override
    public boolean updateInstructor(Instructor i) {
        return false;
    }

    @Override
    public void clear() {

    }
}


