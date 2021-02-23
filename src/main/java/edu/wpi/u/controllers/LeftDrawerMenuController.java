package edu.wpi.u.controllers;

import com.jfoenix.controls.JFXToggleNode;
import edu.wpi.u.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

    public class LeftDrawerMenuController {

        @FXML public Rectangle flair1;
        @FXML public Rectangle flair2;
        @FXML public Rectangle flair3;
        @FXML public Rectangle flair4;

        @FXML public Label text1;
        @FXML public Label text2;
        @FXML public Label text3;
        @FXML public Label text4;

        @FXML public JFXToggleNode toggle1;
        @FXML public JFXToggleNode toggle2;
        @FXML public JFXToggleNode toggle3;
        @FXML public JFXToggleNode toggle4;

        @FXML
        public void initialize() throws IOException{
//        toPathPlanningBtn.onActionProperty()
//        toggle4.setDisable(true);
            setTextColor(App.leftMenuScreenNum);
            toggleButton(App.leftMenuScreenNum);
            setRectVisibility(App.leftMenuScreenNum);
//        setRectVisibility(-1);
//        toggle4.setDisableAnimation(true);
        }

        private void toggleButton(int screen){
            switch(screen) {
                case 1:
                    toggle1.setSelected(true); break;
                case 2:
                    toggle2.setSelected(true); break;
                case 3:
                    toggle3.setSelected(true); break;
                case 4:
                    toggle4.setSelected(true); break;
            }
        }

        private void setRectVisibility(int activeRect){
            flair1.setVisible(false);
            flair2.setVisible(false);
            flair3.setVisible(false);
            flair4.setVisible(false);
            switch (activeRect){
                case 1: flair1.setVisible(true); break;
                case 2: flair2.setVisible(true); break;
                case 3: flair3.setVisible(true); break;
                case 4: flair4.setVisible(true); break;

            }
        }

        private void setTextColor(int activeText){
            text1.setTextFill(Color.web("#8a93a0"));
            text2.setTextFill(Color.web("#8a93a0"));
            text3.setTextFill(Color.web("#8a93a0"));
            text4.setTextFill(Color.web("#8a93a0"));
            switch (activeText){
                case 1: text1.setTextFill(Color.web("#2e5bff")); break;
                case 2: text2.setTextFill(Color.web("#2e5bff")); break;
                case 3: text3.setTextFill(Color.web("#2e5bff")); break;
                case 4: text4.setTextFill(Color.web("#2e5bff")); break;

            }
        }


        public void handleChangeToPathPlanning(ActionEvent actionEvent) {
            App.rightDrawerRoot.set( "/edu/wpi/u/views/PathfindingRightPage.fxml");
            App.leftMenuScreenNum = 1;
            setRectVisibility(1);
            setTextColor(1);
        }

        public void handleChangeToRequests(ActionEvent actionEvent) {
            App.rightDrawerRoot.set( "/edu/wpi/u/views/ViewRequest.fxml");
            App.leftMenuScreenNum = 2;
            setRectVisibility(2);
            setTextColor(2);
        }

        public void handleChangeToAdmin(ActionEvent actionEvent) {
            App.rightDrawerRoot.set( "/edu/wpi/u/views/AdminTools.fxml");
            App.leftMenuScreenNum = 3;
            setRectVisibility(3);
            setTextColor(3);
        }

        public void handleChangeToSettings(ActionEvent actionEvent) {
            App.rightDrawerRoot.set( "/edu/wpi/u/views/Settings.fxml");
            App.leftMenuScreenNum = 4;
            setRectVisibility(4);
            setTextColor(4);
        }

        public void handleExit(ActionEvent actionEvent) {
            App.getInstance().stop();
        }
    }