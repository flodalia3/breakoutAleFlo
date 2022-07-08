package main.school.jdbc.implementation;

import main.school.data.DataException;
import main.school.data.abstractions.InstructorRepository;
import main.school.data.abstractions.JdbcRepository;
import main.school.model.Instructor;
import main.school.model.Sector;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
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
        String query = "SELECT ID, SNAME, SURNAME, DOB, EMAIL FROM INSTRUCTOR ";
        List<Instructor> instructors;
        try (
                Statement s = this.conn.createStatement();
                ResultSet rs = s.executeQuery(query);
        ) {
            instructors = new ArrayList<>();
            while (rs.next()) {
                instructors.add(createInstructor(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instructors;
        //FASE 2 APPLICARE IL TEMPLATE PATTERN
    }
    //String name, String lastname, LocalDate dob, String email, List<Sector> specialization
    private Instructor createInstructor(ResultSet rs) throws SQLException {
        long id = rs.getLong("ID");
        String name = rs.getString("SNAME");
        String lastname = rs.getString("SURNAME"); // It may be null
        Date date =  rs.getDate("DOB");
        LocalDate dob = date.toLocalDate();
        String email = rs.getString("EMAIL"); // It may be null
        List<Sector> specialization = getSpecialization(id);
        return new Instructor(id, name, lastname, dob, email, specialization);
    }
    private List<Sector> getSpecialization (long id) throws SQLException {
        String query = "SELECT S.SNAME FROM INSTRUCTOR I " +
                            "JOIN INSTRUCTOR_SECTOR IS_S ON (IS_S.INSTRUCTOR_ID = I.ID)" +
                                "JOIN SECTOR S ON (IS_S.SECTOR_ID = S.ID)" +
                        "WHERE I.ID = " + id;
        List<Sector> sectors = new ArrayList<>();
        try (
                Statement s = this.conn.createStatement();
                ResultSet rs = s.executeQuery(query);
            ){
                while (rs.next()) {
                    Sector sect = createSector(rs);
                    sectors.add(sect);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sectors;
    }
    private Sector createSector(ResultSet rs) throws SQLException {
        if (rs.getString("SNAME").equals("GRAPHICS")) {
            return Sector.GRAPHICS;
        }
        else if (rs.getString("SNAME").equals("OFFICE")) {
            return Sector.OFFICE;
        }
        else if (rs.getString("SNAME").equals("DEVELOPMENT")) {
            return Sector.DEVELOPMENT;
        }
        else  {
            return null;
        }
    }

    @Override
    public Optional<Instructor> findById(long instructorId) {
        String query = "SELECT SNAME, SURNAME, DOB, EMAIL" +
                       "FROM INSTRUCTOR " +
                       "WHERE ID = ?";
        try (
                PreparedStatement prs = this.conn.prepareStatement(query);
                ) {
                    prs.setLong(1, instructorId);
                    try (
                            ResultSet rs = prs.executeQuery();
                            ) {
                            if (rs.next()) {
                                Instructor i = createInstructor(rs);
                                Optional<Instructor> instructor = Optional.of(i);
                                return instructor;
                            } else {
                                return Optional.empty();
                            }
                        }

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
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


