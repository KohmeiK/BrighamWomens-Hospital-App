package edu.wpi.u.models;

import edu.wpi.u.algorithms.Edge;
import edu.wpi.u.algorithms.Node;
import edu.wpi.u.database.Database;
import edu.wpi.u.database.RequestData;
import edu.wpi.u.requests.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class RequestService {


  static RequestData rd = new RequestData();
  ArrayList<Request> activeRequests = new ArrayList<>();

  public RequestService() {
    this.activeRequests = rd.loadActiveRequests();
//    for (Request x : this.activeRequests){
//      System.out.println("Req: "+ x.getRequestID());
//    }
  }

  /*
  Saves the model data into csvs and drops tables
   */

  public void loadCSVFile(String path, String tableName){
    Database.getDB().dropValues();
    rd.readCSV(path,tableName);
    this.activeRequests = rd.loadActiveRequests();
  }

  public void saveCSVFile(String path, String tableName){
    rd.saveCSV(tableName,path , "test"); // TODO: Provide header
  }
  /*
  Make sure x & y are positive integers within the map coordinate range
  */
  public String addRequest(String description, LinkedList<String> assignee, String title, LinkedList<String> location, String type, String creator) {
    /*
                        descriptionTextField.getText(),
                        lLConverter(assigneeArrayList),
                        titleTextField.getText(),
                        lLConverter(locationArrayList),
                        serviceTypeTextField.getText(),
                        userID );
     */
    //Scucess
    Random rand = new Random();
    int requestID = rand.nextInt();
    String ID = Integer.toString(requestID);//make a random id
    // String requestID,LinkedList<String> assignee, Date dateCreated, Date dateCompleted, String description, String title, LinkedList<String> location, String type, String creator) {
    Request newRequest = new Request(ID, assignee, new Date(), null, description ,title,location, type, creator);
    this.activeRequests.add(newRequest);
    rd.addRequest(newRequest);
    return "";
    //Fail
    //return requestID;
    /*
    Check if valid node_id
    Return "" is a success
    Return node_id if node already exists / invalid
     */
  }

  public String updateRequest(String requestID, String title, String description,Date dateCompleted, LinkedList<String> location, String type, LinkedList<String> assignee, String creator){
    //Scucess
    for(Request r : this.activeRequests){
      if(r.getRequestID() == requestID){
        if(dateCompleted != null){
          this.activeRequests.remove(r);
        }
        r.editRequest(dateCompleted,description,title,location,type,assignee,creator);
        rd.updateRequest(r);
        return "";
      }
    }
    return requestID;
    //Fail
    //return requestID;
    /*
    Check if valid node_id
    Return "" is a success
    Return node_id if node already exists / invalid
     */
  }

  public String deleteRequest(String requestID) {
    //Scucess
    Date now = new Date();
    for(Request r : this.activeRequests){
      if(r.getRequestID() == requestID){
        r.setDateCompleted(now);
        this.activeRequests.remove(r);
        rd.delRequest(r);
        return "";
      }
    }
    return "";
    //Fail
    //return requestID;
    /*
    Check if valid node_id
    Return "" is a success
    Return node_id if node does not exists / invalid
     */
    //        dm.delNode(node_id);
  }

  public ArrayList<Request> getRequests() {
    boolean debug = false;
    if(debug){ //Adding fake requests just so we can test UI - currently it always returns a 0 length array
      ArrayList<Request> temp = new ArrayList<Request>();
      LinkedList<String> list = new LinkedList<String>();
      list.add("Nothing");
      list.add("Here");
      Request r1 = new Request("FakeID",list ,new Date(1000), new Date(2000),"No Description","Fake Title",list, "No Type","Admin");
      temp.add(r1);
      return temp;

    }else{
      return new ArrayList<Request>(this.activeRequests);
    }

    /*
    Returns an ArrayList of all Node Objects in the graph
     */
  }
}
