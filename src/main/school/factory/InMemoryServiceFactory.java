package main.school.factory;

import main.school.services.AbstractSchoolService;
import main.school.services.InMemorySchoolService;

public class InMemoryServiceFactory extends ServiceAbstractFactory{
    @Override
    public AbstractSchoolService createSchoolService() {
        return new InMemorySchoolService();
    }
}
