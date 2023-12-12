package workflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import workflow.entity.BOPMPROCESS;
import workflow.entity.BOPMProcessMapper;


import javax.sql.DataSource;
import java.util.List;

@Component
public class BOPMProcessDAOImpl implements BOPMProcessDAO{

    JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_PID = "select * from processdata p where p.pid = ?";
    private final String SQL_DELETE_PID = "delete from processdata p where p.pid  = ?";
    private final String SQL_UPDATE_PID = "update processdata p set p.task_owner = ? where p.pid = ?";
    private final String SQL_GET_ALL = "select * from processdata";
    private final String SQL_INSERT_PID = "insert into processdata(pid, taskid, task_owner) values(?,?,?)";


    @Autowired
    public BOPMProcessDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public BOPMPROCESS getPId(int id) {
        return null;
    }

    @Override
    public List<BOPMPROCESS> getAllBOPMProcess() {
        return jdbcTemplate.query(SQL_GET_ALL, new BOPMProcessMapper());
    }

    @Override
    public boolean deleteBOPMProcessByID(BOPMPROCESS bopmprocess) {
        return false;
    }

    @Override
    public boolean updateBOPMProcessByID(BOPMPROCESS bopmprocess) {
        return false;
    }

    @Override
    public boolean createBOPMProcessByID(BOPMPROCESS bopmprocess) {
        return jdbcTemplate.update(SQL_INSERT_PID, bopmprocess.getPid(), bopmprocess.getTaskID(), bopmprocess.getAssignee()) > 0;

    }
}
