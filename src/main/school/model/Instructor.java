package main.school.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Instructor {
    private long id;
    private String name;
    private String lastname;
    private LocalDate dob;
    private String email;
    private List<Sector> specialization;

    public Instructor(String name, String lastname, LocalDate dob, String email, List<Sector> specialization) {
        this.name = name;
        this.lastname = lastname;
        this.dob = dob;
        this.email = email;
        this.specialization = specialization;
    }
    public boolean isBornAfter (LocalDate date)
    {
        return this.dob.isAfter(date);
    }
    public boolean isSpecializedInMultipleSectors () {
        return this.specialization.size()>1;
    }
    public List<Sector> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<Sector> specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
