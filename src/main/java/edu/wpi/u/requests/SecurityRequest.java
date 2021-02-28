package edu.wpi.u.requests;
import java.sql.Date;
import java.util.ArrayList;

//TODO: Private or protected fields?
public class SecurityRequest implements IRequest {
    private String threatLevel;
    private Request req;
    private int priority;

    //Composition Pattern Type
    public SecurityRequest(String threatLevel, int priority, Request req) {
        this.threatLevel = threatLevel;
        this.priority = priority;
        this.req = req;
    }

    @Override
    public java.sql.Date getDateCreated() { return (java.sql.Date) this.req.getDateCreated(); }

    @Override
    public java.sql.Date getDateCompleted() { return (Date) this.req.getDateCompleted(); }

    @Override
    public String getDescription() { return this.req.getDescription(); }

    @Override
    public String getRequestID() { return this.req.getRequestID(); }

    @Override
    public String getTitle() { return this.req.getTitle(); }

    @Override
    public String displayLocations() { return this.req.displayLocation(); }

    @Override
    public String displayAssignees() { return this.req.displayAssignees(); }

    @Override
    public String getSpecificData() { return null; }

    @Override
    public void createDBEntry() { }

    @Override
    public void updateDBEntry() { }

    @Override
    public String getRequestType() {
        return null;
    }

    @Override
    public Request getGenericRequest() {
        return null;
    }

}

