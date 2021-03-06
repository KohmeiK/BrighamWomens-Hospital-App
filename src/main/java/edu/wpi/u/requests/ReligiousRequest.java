package edu.wpi.u.requests;

import java.io.Serializable;
import java.util.LinkedList;

public class ReligiousRequest extends SpecificRequest {

    @Override
    public String getType() {
        return "Religious";
    }


    @Override
    public String[] getSpecificFields() {
        String[] res = new String[]{"priority", "religion"};
        return res;
    }

}
