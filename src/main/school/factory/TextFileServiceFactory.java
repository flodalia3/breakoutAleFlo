package main.school.factory;

import main.school.services.AbstractSchoolService;
import main.school.services.TextFileSchoolService;

public class TextFileServiceFactory extends ServiceAbstractFactory{
    @Override
    public AbstractSchoolService createSchoolService() {
        return new TextFileSchoolService();
    }
}
