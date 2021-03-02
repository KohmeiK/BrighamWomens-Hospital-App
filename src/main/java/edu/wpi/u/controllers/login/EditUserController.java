package edu.wpi.u.controllers.login;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import edu.wpi.u.App;
import edu.wpi.u.algorithms.Node;
import edu.wpi.u.users.Employee;
import edu.wpi.u.users.Guest;
import edu.wpi.u.users.StaffType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class EditUserController {


    public JFXComboBox userSelectComboBox;
    public JFXButton selectUserButton;
   public JFXTextField usernameTextField;
   public JFXTextField nameTextField;
   public JFXTextField phoneNumTextField;
   public JFXTextField passwordTextField;
   public JFXComboBox userTypeComboBox;
   public JFXCheckBox userEmployStatus;
   public JFXButton editUserButton;
    public JFXTextField emailTextField;
    public JFXDatePicker appointmentDatePicker;

    public void initialize() throws IOException {




        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Username Required");
        usernameTextField.getValidators().add(validator);
        usernameTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                usernameTextField.validate();
            }
            for(Guest user : App.userService.getGuests()){
                if(user.getUserName().equals(newVal)){

                }
            }
        });

        RequiredFieldValidator validatorGuestExists = new RequiredFieldValidator();
        validatorGuestExists .setMessage("Username already exists");
        usernameTextField.getValidators().add(validatorGuestExists );
        usernameTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            for(Guest user : App.userService.getGuests()){
                if(user.getUserName().equals(newVal)){
                    usernameTextField.validate();
                }
            }
        });

        RequiredFieldValidator validatorEmployeeExists = new RequiredFieldValidator();
        validatorEmployeeExists.setMessage("Username already exists");
        usernameTextField.getValidators().add(validatorEmployeeExists);
        usernameTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            for(Employee user : App.userService.getEmployees()){
                if(user.getUserName().equals(newVal)){
                    usernameTextField.validate();
                }
            }
        });



        RequiredFieldValidator validator2 = new RequiredFieldValidator();
        validator2.setMessage("Password Required");
        nameTextField.getValidators().add(validator2);
        nameTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                nameTextField.validate();
            }
        });
        // TODO: ADD REGEX FUNCTIONALITY TO THIS
        RequiredFieldValidator validator3 = new RequiredFieldValidator();
        validator3.setMessage("Phone Number Required");
        phoneNumTextField.getValidators().add(validator3);
        phoneNumTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                phoneNumTextField.validate();
            }
        });

        RequiredFieldValidator validator4 = new RequiredFieldValidator();
        validator4.setMessage("Password Required");
        passwordTextField.getValidators().add(validator4);
        passwordTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                passwordTextField.validate();
            }
        });
        // TODO: ADD REGEX FUNCTIONALITY TO THIS
        RequiredFieldValidator validator5 = new RequiredFieldValidator();
        validator5.setMessage("Email Required");
        emailTextField.getValidators().add(validator5);
        emailTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                emailTextField.validate();
            }
        });

        RequiredFieldValidator validator6 = new RequiredFieldValidator();
        validator6.setMessage("Email not in correct format");
        emailTextField.getValidators().add(validator6);
        emailTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            String regex = "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            Pattern pat = Pattern.compile(regex);
            if (newVal){
                if(!pat.matcher(emailTextField.getText()).matches()) {
                    emailTextField.validate();
                }
            }
        });
    }


    /**This function intakes a set of text fields, a combo box, and a checkbox, and sends that info to
     * the database to edit an existing user

     */
    public void handleEditUser(){

        String userType = "";
        if(userEmployStatus.isSelected()){
            App.userService.updateEmployee(userSelectComboBox.getValue().toString(), nameTextField.getText(), usernameTextField.getText(), passwordTextField.getText(), emailTextField.getText(), (StaffType) userTypeComboBox.getValue(), phoneNumTextField.getText(), false);
        }
        else{
            nameTextField.setText(App.selectedGuest.getName());
            usernameTextField.setText(App.selectedGuest.getUserName());
            passwordTextField.setText(App.selectedGuest.getPassword());
            emailTextField.setText(App.selectedGuest.getEmail());
            userTypeComboBox.setValue(App.selectedGuest.getType());
            phoneNumTextField.setText(App.selectedGuest.getPhoneNumber());
            appointmentDatePicker.setValue(App.selectedGuest.getAppointmentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            App.userService.updateGuest(userSelectComboBox.getValue().toString(), nameTextField.getText(), usernameTextField.getText(), passwordTextField.getText(), emailTextField.getText(), (StaffType) userTypeComboBox.getValue(),  phoneNumTextField.getText(), Date.from(Instant.from(appointmentDatePicker.getValue()))  , false);
        }

    }

    /**This function takes a user from the combobox and fills the fields and artifacts with the selected user's info
     *
     * @param userSelectComboBox
     */
    public void handleSelectUser(JFXComboBox userSelectComboBox){

    }
}
