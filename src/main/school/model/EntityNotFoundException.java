package main.school.model;

public class EntityNotFoundException extends Exception{
    private long id;

    public EntityNotFoundException(String message, long id) {
        super(message);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
