package workflow.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BOPMProcessMapper implements RowMapper<BOPMPROCESS> {


    @Override
    public BOPMPROCESS mapRow(ResultSet rs, int rowNum) throws SQLException {

        BOPMPROCESS bopmprocess = new BOPMPROCESS();
        bopmprocess.setPid(rs.getInt("pid"));
        bopmprocess.setTaskID((rs.getString("TASKID")));
        bopmprocess.setTaskID((rs.getString("TASK_OWNER")));

        return bopmprocess;
    }
}
