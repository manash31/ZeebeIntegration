package workflow.service.impl;

import workflow.entity.BOPMPROCESS;
import workflow.entity.Person;

import java.util.List;

public interface BOPMProcessDAO {

    BOPMPROCESS getPId(int id);

    List<BOPMPROCESS> getAllBOPMProcess();

    boolean deleteBOPMProcessByID(BOPMPROCESS bopmprocess);

    boolean updateBOPMProcessByID(BOPMPROCESS bopmprocess);

    boolean createBOPMProcessByID(BOPMPROCESS bopmprocess);

}
