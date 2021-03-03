package edu.wpi.u.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXTextField;
import edu.wpi.u.App;
import edu.wpi.u.requests.IRequest;
import edu.wpi.u.requests.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;

public class RequestDetailController {
    public HBox HBoxToClone;
    public VBox specificFields;
    public VBox VBoxToAdd;
    public Label typeLabel;
    @FXML Label requestDetailTitleLabel;
    @FXML Label requestDetailCreatorLabel;
    @FXML Label requestDetailDescriptionLabel;
    @FXML JFXChipView requestDetailLocationChipView;
    @FXML JFXChipView requestDetailStaffChipView;
    @FXML Label requestDetailDateCreatedLabel;
    @FXML Label requestDetailDate2BCompleteLabel;

    //Maintenance Requests Panes
    @FXML Label requestDetailSecurityLabel;
    @FXML ListView commentListView;
    @FXML StackPane requestDetailStack;
    @FXML Pane requestDetailSecurityPane;
    @FXML Pane requestDetailMaintenancePane;
    @FXML Pane requestDetailLaundryPane;
    IRequest currentIRequest;



    @FXML
    public void initialize() throws IOException{
        currentIRequest = App.requestService.getRequests().get(App.lastClickedRequestNumber);
        Request request = currentIRequest.getGenericRequest();
        requestDetailTitleLabel.setText("Request Title: "+ request.getTitle());
        requestDetailCreatorLabel.setText("Created By: Administrator");
        //requestDetailCreatorLabel.setText("Created By: " + request.getCreator());
        requestDetailDescriptionLabel.setText(request.getDescription());
        //requestDetailLocationChipView.setText(request.getLocation());
      //  requestDetailStaffChipView.setText(request.getStaff());
        System.out.println(request.getDateCreated().toString());
        requestDetailDateCreatedLabel.setText("Date Created: "+ request.getDateCreated().toString());
        //System.out.println(request.getDateNeeded());
        requestDetailDate2BCompleteLabel.setText("Date to Complete By: NA");
        typeLabel.setText("Type: " + currentIRequest.getType());
        //requestDetailSecurityLabel.setText(request);
//        setSpecifics();
        generateSpecificFields();

    }

    //TODO : Replace with function written in NER Controller, based on current IREQUEST
    public void generateSpecificFields(){
       //Generate Labels ONLY, using fields/values of Irequest, no error checking needed
        //NO text fields, just for display
        for(int i = 0; i < currentIRequest.getSpecificFields().length; i++) {

            Label entryData= new Label(currentIRequest.getSpecificFields()[i] +": "+ currentIRequest.getSpecificData().get(i).toString());
            entryData.setStyle("-fx-font-size: 34");
            VBoxToAdd.getChildren().add(entryData);
        }

    }

    public void handleCommentButton() {
    }

    @FXML
    public void handleRequestDetailCancelButton() throws Exception {
        AnchorPane anchor = (AnchorPane) App.tabPaneRoot.getSelectionModel().getSelectedItem().getContent();
        Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/u/views/NewViewRequest.fxml"));
        anchor.getChildren().clear();
        anchor.getChildren().add(root);
    }

    @FXML
    public void handleResolveRequestButton() throws IOException {
        //Resolve Request()

        IRequest r = App.requestService.getRequests().get(App.lastClickedRequestNumber);
        App.requestService.deleteRequest(r.getGenericRequest().getRequestID());

        AnchorPane anchor = (AnchorPane) App.tabPaneRoot.getSelectionModel().getSelectedItem().getContent();
        Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/u/views/NewViewRequest.fxml"));
        anchor.getChildren().clear();
        anchor.getChildren().add(root);
    }

    @FXML
    public void handleEditRequestButton() throws IOException {
        System.out.println("HERE, attempting delete Request " + App.lastClickedRequestNumber);

        AnchorPane anchor = (AnchorPane) App.tabPaneRoot.getSelectionModel().getSelectedItem().getContent();
        Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/u/views/NewRequestEdit.fxml"));
        anchor.getChildren().clear();
        anchor.getChildren().add(root);
    }

    private void setSpecifics(){
        switch(currentIRequest.getGenericRequest().getType()) {
            case("Maintenance") :
                requestDetailMaintenancePane.setVisible(true);
                break;
            case("Laundry") :
                requestDetailLaundryPane.setVisible(true);
                //add stuff
                break;
            case("Security"):
                requestDetailSecurityPane.setVisible(true);
                //add stuff
                break;
            default:
                System.out.println("lmao you screwed up");
        }
    }
}
