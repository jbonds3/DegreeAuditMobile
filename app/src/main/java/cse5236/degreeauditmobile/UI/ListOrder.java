package cse5236.degreeauditmobile.UI;

import java.util.ArrayList;
import java.util.List;

import cse5236.degreeauditmobile.Model.Semester;

public class ListOrder {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    /**
     * Source: https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
     *
     * Input: Byte array
     * Output: Hexadecimal string corresponding to input (with leading zeroes)
     *
     * @author acc
     */
    public static List<Semester> listSort(List<Semester> sem) {

        List<Semester> s1 = new ArrayList<>();
        if (sem.size() > 1) {

            while (sem.size() != 0) {
                int minNum = 1000;
                for (Semester s : sem) {
                    int semYear = Integer.parseInt(s.getYear());
                    if (minNum > semYear) {
                        minNum = semYear;
                    }
                }

//                if (sem.contains("SP" + minNum)) {
//
//                }
//
//                if (sem.contains("SU" + minNum)) {
//                    sem.ge
//                }
//
//                if (sem.contains("AU" + minNum)) {
//
//                }
            }

        }
        return sem;
    }
}
