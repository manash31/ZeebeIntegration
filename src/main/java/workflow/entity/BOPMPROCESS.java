package workflow.entity;

public class BOPMPROCESS {
    long pid;
    String TaskID;
    String Assignee;

    public long getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getAssignee() {
        return Assignee;
    }

    public void setAssignee(String assignee) {
        Assignee = assignee;
    }

    public BOPMPROCESS(){}

    public BOPMPROCESS(long pid, String taskID, String assignee) {
        this.pid = pid;
        TaskID = taskID;
        Assignee = assignee;
    }

    @Override
    public String toString() {
        return "BOPMPROCESS{" +
                "pid=" + pid +
                ", TaskID='" + TaskID + '\'' +
                ", Assignee='" + Assignee + '\'' +
                '}';
    }
}
