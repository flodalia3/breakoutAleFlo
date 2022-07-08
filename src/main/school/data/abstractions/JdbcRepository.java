package main.school.data.abstractions;

import java.sql.Connection;

public abstract class JdbcRepository {
    protected Connection conn;
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
