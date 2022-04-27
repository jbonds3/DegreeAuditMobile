package cse5236.degreeauditmobile.Model;

public class Helper {

    public static double classLetterToNumber(String letterGrade) {
        double numberGrade = 0.0;
        switch (letterGrade) {
            case "A":   numberGrade = 4;
                        break;
            case "A-":   numberGrade = 3.7;
                        break;
            case "B+":   numberGrade = 3.3;
                        break;
            case "B":   numberGrade = 3;
                        break;
            case "B-":   numberGrade = 2.7;
                        break;
            case "C+":   numberGrade = 2.3;
                        break;
            case "C":   numberGrade = 2;
                        break;

            case "C-":   numberGrade = 1.7;
                        break;
        }
        return numberGrade;

    }

    static public int findPosition(String array[], String toFind) {
        for (int i = 0; i < array.length; i++ ) {
            if (array[i].equals(toFind)) {
                return i;
            }
        }
        return -1;
    }
}
