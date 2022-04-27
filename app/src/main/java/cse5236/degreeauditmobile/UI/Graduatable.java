package cse5236.degreeauditmobile.UI;

import java.util.List;
import cse5236.degreeauditmobile.Model.Class;

public class Graduatable {

    public static String userCanGrad(boolean classTaken, double CPHR, double hours) {
        String inMajorStr = "Student can apply";
        String majorStatus = userCanMajor(classTaken, CPHR);
        String canGrad = "";

        if (!majorStatus.equals(inMajorStr)) {
            canGrad = majorStatus;
        } else {
            //Min Hours
            if (hours < 126) {
                canGrad += "Student has less than 126 hours\n";
            } else {
                canGrad += "Student can graduate";
            }

        }

        return canGrad;
    }

    public static String userCanMajor(boolean classTaken, double CPHR) {
        boolean cphrStatus = true, classStatus = false;
        String majorStatus = "";

        //CPHR
        if (CPHR < 2.0) {
            cphrStatus = false;
            majorStatus += "CPHR is below 2.0\n";
        }

        classStatus = classTaken;

        if (!classStatus) {
            majorStatus += "Student has not taken CSE 2221\n";
        }

        if (classStatus && cphrStatus) {
            majorStatus = "Student can apply";
        }

        return majorStatus;
    }
}
