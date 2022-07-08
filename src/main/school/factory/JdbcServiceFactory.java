package main.school.factory;

import main.school.data.DataException;
import main.school.services.AbstractSchoolService;
import main.school.services.JdbcSchoolService;

public class JdbcServiceFactory extends ServiceAbstractFactory {
    @Override
    public AbstractSchoolService createSchoolService() throws DataException {
        return new JdbcSchoolService();
    }
}
