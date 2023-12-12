package workflow.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBService {
    public Connection establishConnection() throws SQLException;
}

