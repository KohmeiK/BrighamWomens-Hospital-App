package edu.wpi.u.requests;

import java.io.Serializable;
import java.util.LinkedList;

public class ComputerRequest extends SpecificRequest {

    @Override
    public String getType() {
        return "Computer";
    }


    @Override
    public String[] getSpecificFields() {
        String[] res = new String[]{"electronicType", "priority"};
        return res;
    }


}
