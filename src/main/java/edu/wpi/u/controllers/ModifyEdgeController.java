package edu.wpi.u.controllers;

import edu.wpi.u.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ModifyEdgeController {

    @FXML public TextField modifyNodeID;
    @FXML public TextField sNode;
    @FXML public TextField eNode;

    @FXML
    public void handleEdgeSubmitButton() {
        App.graphService.updateStartEdge(modifyNodeID.getText(), sNode.getText());
        App.graphService.updateEndEdge(modifyNodeID.getText(), eNode.getText());
        App.rightDrawerRoot.set( "../views/AdminTools.fxml");
    }
    @FXML
    public void handleEdgeCancelButton() {
        App.rightDrawerRoot.set( "../views/AdminTools.fxml");
    }


}