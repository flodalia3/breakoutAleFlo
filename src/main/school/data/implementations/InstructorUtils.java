package main.school.data.implementations;

import main.school.model.Course;
import main.school.model.Instructor;
import main.school.model.Level;
import main.school.model.Sector;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InstructorUtils {
    public static Instructor fromTextLine (String line) {//line deve contenere tutti i parametri che servono per creare un Instructor
        var sArray = line.split(",");

        //leggiamo la dimensione di Sector per l'istruttore (ottenuto di default)
        int numSpec = Integer.parseInt(sArray[5]);
        List<Sector> listSect = new ArrayList<>();
        for (int i = 0; i < numSpec; i++) {
            Sector s = Sector.valueOf(sArray[6+i]);
            listSect.add(s);
        }
        return new Instructor(Long.parseLong(sArray[0]), sArray[1], sArray[2], LocalDate.parse(sArray[3]),sArray[4],
                              listSect);
        //long id, String name, String lastname, LocalDate dob, String email, List<Sector> specialization
    }
    public static String toTextLine (Instructor i) {
        String sectors = "";
        for (Sector s : i.getSpecialization()) {
            sectors += ",";
            sectors += s.name();
        }
//        if (sectors.length() != 0) {
//            sectors = sectors.substring(0, sectors.length()-1); //escludo il carattere virgola nell'ultima posizione
//        }
         return String.format("%d,%s,%s,%s,%s,%d%s",i.getId(),i.getName(),i.getLastname(),i.getDob(),
                                            i.getEmail(), i.getSpecialization().size(), sectors);
    }
}
