package edu.wpi.u.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import edu.wpi.u.App;
import edu.wpi.u.algorithms.Node;
import edu.wpi.u.models.GraphService;

import java.time.LocalDateTime;
import java.util.LinkedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.util.Date;

import java.util.ArrayList;

public class NewRequestController {

    @FXML
    public TextField titleTextField;
    @FXML
    public TextField descriptionTextField;
    @FXML
    public TextField nodeTextField;
    @FXML
    public TextField assigneeTextField;
    @FXML
    public TextField contactTextField;
    @FXML
    public SplitMenuButton serviceTypeMenu;
    @FXML
    public Button submitRequestButton;
    @FXML
    public Button assigneeButton;
    @FXML
    public ListView assigneeList;
    @FXML
    public Button cancelButton;
    @FXML
    public Label errorMessage;
    @FXML
    public Label errorMessage2;

    public String exampleID;

    public Date start = new Date();

    public Date end;

    private ArrayList<String> assignee = new ArrayList<String>();

    public void handleAssigneeList() {
        if (titleTextField.getText().equals("")) {
            errorMessage2.setText("Please enter an assignee!");
        } else {
            assignee.add(assigneeTextField.getText());
            assigneeList.getItems().add(assigneeTextField.getText());
            assigneeTextField.setText("");
            //System.out.println("call");}
        }
    }

    //there exist fields for contact info, location, and service type, this is not currently reflected in the constructor
    public void handleSubmitRequestButton() {

        if (titleTextField.getText().equals("")) {
            errorMessage.setText("Please enter a title!");}
            else{
                App.requestService.addRequest(exampleID, titleTextField.getText(), descriptionTextField.getText(), start, end, assignee);
                App.rightDrawerRoot.set("../views/ViewRequest.fxml");

            }

    }

    public void handleLeaveAdd(){
        App.rightDrawerRoot.set( "../views/ViewRequest.fxml");
    }

}
