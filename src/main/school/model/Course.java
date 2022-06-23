package main.school.model;

public class Course {
    private long id;
    private String title;
    private int hours;
    private Sector sector;
    private Level level;

    public Course(String title, int hours, Sector sector, Level level) {
        this.title = title;
        this.hours = hours;
        this.sector = sector;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Title: " + title + " Duration: " + hours + " Sector" + sector.name() + " Level: "
                + level.name();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

}