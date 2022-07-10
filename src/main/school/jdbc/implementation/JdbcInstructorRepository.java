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
        String query = "SELECT ID " +
                " FROM INSTRUCTOR " +
                "WHERE ID = ?";

        try(
                PreparedStatement statement = this.conn.prepareStatement(query);

                ){
                    statement.setLong(1, idInstructor);
                    try(
                            ResultSet rs = statement.executeQuery();
                            ){
                        if(rs.next()){
                            return true;
                        }else{
                            return false;
                        }
                    }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Instructor> getInstructorsBornAfterDateAndMultiSpecialized(LocalDate date) throws DataException {
        String query = "SELECT I.ID, I.SNAME, I.SURNAME, I.DOB, I.EMAIL " +
                "FROM INSTRUCTOR I JOIN INSTRUCTOR_SECTOR IS_S " +
                "ON (IS_S.INSTRUCTOR_ID = I.ID) " +
                "JOIN SECTOR S " +
                "ON (IS_S.SECTOR_ID = S.ID) " +
                "WHERE I.DOB > ? " +
                "GROUP BY I.ID, I.SNAME, I.SURNAME, I.DOB, I.EMAIL " +
                "HAVING COUNT(S.SNAME) > 1";
        List<Instructor> instructors = new ArrayList<>();

        try(
                PreparedStatement statement = this.conn.prepareStatement(query);
                ) {
                    Date date1 = Date.valueOf(date);
                    statement.setDate(1, date1);
                    try(
                            ResultSet rs = statement.executeQuery(query);
                            ){
                             while (rs.next()) {
                                 instructors.add(createInstructor(rs));
                             }
                    }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return instructors;
    }

    @Override
    public void addInstructor(Instructor instructor) throws DataException {
        String query = "INSERT INTO INSTRUCTOR  (ID, SNAME, SURNAME, DOB, EMAIL) " +
                        "VALUES (?, ?, ?, ?, ?)";

        String idQuery = "SELECT INSTRUCTOR_ID_SEQUENCE.nextval as ID FROM DUAL";
        List<Sector> specialization = instructor.getSpecialization();
        try (
                Statement idStatement = this.conn.createStatement();
                ResultSet idRs = idStatement.executeQuery(idQuery);
                PreparedStatement statement = this.conn.prepareStatement(query);
        ) {
            idRs.next();
            long id = idRs.getLong("ID");
            statement.setLong(1, id);
            statement.setString(2, instructor.getName());
            statement.setString(3, instructor.getLastname());
            statement.setDate(4, Date.valueOf(instructor.getDob()));
            statement.setString(5, instructor.getEmail());
            //da controllare l'aggiunta dei settori
            statement.execute();
            long idSector;
            for(Sector s : specialization){
                idSector = getIDfromNameSector(s.name());
                addSector(idSector, id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Instructor> getAll() throws DataException {

        // Date date = Date.valueOf(LocalDate.now());
        // LocalDate localDate = Date.valueOf("2019-01-10").toLocalDate();
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
                "JOIN INSTRUCTOR_SECTOR IS_S ON (IS_S.INSTRUCTOR_ID = I.ID) " +
                "JOIN SECTOR S ON (IS_S.SECTOR_ID = S.ID) " +
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
        String query = "SELECT SNAME, SURNAME, DOB, EMAIL " +
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
        String query = "SELECT I.ID, I.SNAME, I.SURNAME, I.DOB, I.EMAIL COUNT(S.SNAME) AS COUNTER " +
                "FROM INSTRUCTOR I " +
                "JOIN INSTRUCTOR_SECTOR IS_S ON (IS_S.INSTRUCTOR_ID = I.ID) " +
                "JOIN SECTOR S ON (IS_S.SECTOR_ID = S.ID) " +
                "WHERE EXTRACT(YEAR FROM I.DOB) < ? " +
                "GROUP BY I.ID, I.SNAME, I.SURNAME, I.DOB, I.EMAIL " +
                "HAVING COUNT(S.SNAME) > 1";
        List<Instructor> instructors = new ArrayList<>();
        try (
                PreparedStatement prs = this.conn.prepareStatement(query);
        ){
            prs.setLong(1, age);

            try (
                    ResultSet rs = prs.executeQuery();
            ) {
                while (rs.next()) {
                    Instructor i = createInstructor(rs);
                    instructors.add(i);
                    return instructors;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instructors;
    }

    @Override
    public boolean updateInstructor(Instructor i) {

        long idInstr = i.getId();

        List<Sector> sectors = i.getSpecialization();
        String query =
                "UPDATE INSTRUCTOR I " +
                "SET I.SNAME = ?, I.SURNAME = ?, I.DOB = ?, I.EMAIL = ? " +
                "WHERE I.ID = ? "; //? = 5

        String query2 = "SELECT S.SNAME " +
                "FROM INSTRUCTOR I JOIN INSTRUCTOR_SECTOR IN_S " +
                "ON (I.ID = IN_S.INSTRUCTOR_ID) " +
                "JOIN SECTOR S " +
                "ON (S.ID = IN_S.SECTOR_ID) " +
                "WHERE I.ID = " + idInstr;

        try (
                PreparedStatement ps = this.conn.prepareStatement(query);
                Statement s = this.conn.createStatement();
                ResultSet rs = s.executeQuery(query2);

        ) {
            ps.setString(1, i.getName());
            ps.setString(2, i.getLastname());
            ps.setDate(3, Date.valueOf(i.getDob()));
            ps.setString(4, i.getEmail());
            ps.setLong(5, idInstr);

            ps.execute();

            List<String> array = new ArrayList();
            while(rs.next()){
                array.add(rs.getString(1));
            }

            //aggiunta dei settori "nuovi" da aggiungere
            for (Sector s1 : sectors) {
                if(!array.contains(s1.name())){
                    long idSector = getIDfromNameSector(s1.name());
                    addSector(idSector, idInstr);
                }
            }
            //sector : settori nuovi (da "aggiornare")
            //array : settori vecchi
            //cancellazione dei settori "vecchi" non più associati all'istruttore
            for (String a : array) {
                Sector sec = Sector.valueOf(a);
                if(!sectors.contains(sec)){
                    long idSector = getIDfromNameSector(sec.name());
                    deleteSector(idSector, idInstr);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //da sistemare il return
        return true;
    }

    private void deleteSector(long idSector, long idInstr) {
        String query = "DELETE FROM INSTRUCTOR_SECTOR " +
                        "WHERE INSTRUCTOR_ID = ? " +
                        "AND SECTOR_ID = ? ";
        try(
                PreparedStatement statement = this.conn.prepareStatement(query);
                ){
            statement.setLong(1, idInstr);
            statement.setLong(2, idSector);

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void addSector(long idSector, long idInstructor) {
        String query = "INSERT INTO INSTRUCTOR_SECTOR (ID, INSTRUCTOR_ID, SECTOR_ID) " +
                "VALUES (?, ?, ?)";
        String idQuery = "SELECT INSTR_SPEC_ID_SEQUENCE.nextval as ID FROM DUAL";

        try (
                Statement idStatement = this.conn.createStatement();
                ResultSet idRs = idStatement.executeQuery(idQuery);
                PreparedStatement statement = this.conn.prepareStatement(query);
        ) {
            idRs.next();
            long id = idRs.getLong("ID");
            statement.setLong(1, id);
            statement.setLong(2, idInstructor);
            statement.setLong(3, idSector);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long getIDfromNameSector(String name) {
        String query = "SELECT ID " +
                "FROM SECTOR " +
                "WHERE SNAME = ?";
        try (
                PreparedStatement pr = this.conn.prepareStatement(query);

                ){
                pr.setString(1, name);

                try(
                        ResultSet rs = pr.executeQuery();
                        ){
                    if(rs.next()){
                        return rs.getLong(1);
                    }
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void clear() {

    }
}


