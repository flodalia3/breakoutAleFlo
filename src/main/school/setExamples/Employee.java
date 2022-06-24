package main.school.setExamples;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Ho chiamato equals");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        System.out.println("ho chiamato hashCode");
        return Objects.hash(id);
        //return 1; --> caso degenere: tutti gli oggetti avranno lo stesso hashcode
    }

    public static void main(String[] args) {
        Employee e1 = new Employee(1, "ciccio");
        Employee e2 = new Employee(1, "ciccio");
        Set<Employee> hs = new HashSet<>();
        hs.add(e1);
        hs.add(e2);
        System.out.println(hs.size());
    }
}
/*
 * Hash Code: è un codice generato a partire dalle caratteristiche di un oggetto, può essere un numero
 */