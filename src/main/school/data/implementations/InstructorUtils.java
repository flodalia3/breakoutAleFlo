package main.school.data.implementations;


import main.school.model.Instructor;
import main.school.model.Sector;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InstructorUtils {
    public static Instructor fromTextLine (String line) {//line deve contenere tutti i parametri che servono per creare un Instructor
        var token = line.split(",");

        //leggiamo la dimensione di Sector per l'istruttore (ottenuto di default)
        int numSpec = Integer.parseInt(token[5]);
        List<Sector> listSect = new ArrayList<>();
        for (int i = 0; i < numSpec; i++) {
            Sector s = Sector.valueOf(token[6+i]);
            listSect.add(s);
        }
        return new Instructor(Long.parseLong(token[0]), token[1], token[2], LocalDate.parse(token[3]),token[4],
                              listSect);
        //long id, String name, String lastname, LocalDate dob, String email, List<Sector> specialization
    }
    public static String toTextLine (Instructor i) {
        String sectors = "";
        for (Sector s : i.getSpecialization()) {
            sectors += ",";
            sectors += s.name();
        }
         return String.format("%d,%s,%s,%s,%s,%d%s",i.getId(),i.getName(),i.getLastname(),i.getDob(),
                                            i.getEmail(), i.getSpecialization().size(), sectors);
    }
}
