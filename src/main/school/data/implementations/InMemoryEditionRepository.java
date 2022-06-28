package main.school.data.implementations;

import main.school.data.DataException;
import main.school.data.abstractions.CourseRepository;
import main.school.data.abstractions.EditionRepository;
import main.school.model.*;

import java.util.*;

public class InMemoryEditionRepository implements EditionRepository {
    private long editionId;
    private static HashMap<Long, Edition> repoEditions = new HashMap<>();

    @Override
    public Iterable<Edition> getAll() throws DataException {
        return repoEditions.values();
    }

    @Override
    public List<Instructor> getInstructorFromSectorAndLevel(Sector sector, Level level) throws DataException {
        Set<Instructor> es = new HashSet<>();
        for(Edition edition: repoEditions.values()) {
            if (edition.getCourse().getSector()==sector && edition.getCourse().getLevel().ordinal()>=level.ordinal()) { // va bene == perchè Sector è enum
                es.add(edition.getInstructor());
            }
        }
        return new ArrayList<Instructor>(es);
    }

    @Override
    public void addEdition(Edition edition) throws DataException {
        edition.setId(++editionId);
        repoEditions.put(edition.getId(), edition);
    }

    @Override
    public List<Edition> getEditionsFromCourseId(long idCourse) throws DataException {
        List<Edition> editionsOfCourse = new ArrayList<>();
        for (Edition aCourseEdition : repoEditions.values()) {
            if (aCourseEdition.getCourse().getId() == idCourse) {
                editionsOfCourse.add(aCourseEdition);
            }
        }
        return editionsOfCourse;
    }

    @Override
    public Optional<Edition> findCourseEditionById(long courseEditionId) {
        Edition ce = repoEditions.get(courseEditionId);
        return ce!=null?Optional.of(ce):Optional.empty();
    }

    @Override
    public void clear() {
        this.repoEditions.clear();
    }
}
