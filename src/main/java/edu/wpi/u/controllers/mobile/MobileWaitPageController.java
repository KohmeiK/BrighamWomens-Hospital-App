package edu.wpi.u.controllers.mobile;

import com.jfoenix.controls.JFXButton;
import edu.wpi.u.App;
import edu.wpi.u.requests.Request;
import edu.wpi.u.requests.SpecificRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MobileWaitPageController implements Initializable {
    public JFXButton pathFindButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //check if man is  good
        if(searchRequests()){
            enablePathFinding();
        }

    }

    public void enablePathFinding(){
        pathFindButton.setStyle("-fx-opacity: 1");
        pathFindButton.setDisable(false);
    }

    public void handlePathFInd(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/wpi/u/views/mobile/MobileEmbenedGoogleMaps.fxml"));
        fxmlLoader.load();
        fxmlLoader.getController();
        App.getPrimaryStage().getScene().setRoot(fxmlLoader.getRoot());
    }

    public void handleDebug(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/wpi/u/views/mobile/MobileEmbenedGoogleMaps.fxml"));
        fxmlLoader.load();
        fxmlLoader.getController();
        App.getPrimaryStage().getScene().setRoot(fxmlLoader.getRoot());
    }

    public void handleReturnLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/wpi/u/views/mobile/MobileUserLoginScreen.fxml"));
        fxmlLoader.load();
        fxmlLoader.getController();
        App.getPrimaryStage().getScene().setRoot(fxmlLoader.getRoot());
    }

    public boolean searchRequests(){
        for(SpecificRequest r : App.requestService.getRequests()){
            if(r.getGenericRequest().getCreator() != null){
                if(r.getGenericRequest().getCreator().equals(App.userService.getActiveUser().getUserName()) &&
                        r.getType() == "CovidSurvey" && r.getGenericRequest().isResolved()){
                    return true;
                }
            }

        }
        return false;
    }


}