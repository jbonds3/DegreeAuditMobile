package cse5236.degreeauditmobile.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

public class ClassID {

    public static String[] classID(String department, String courseNumber) {
        String[] creditPrereqsArray = new String[2];

        if (department.equals("CSE")) {

            if (courseNumber.equals("1223")) {
                creditPrereqsArray[0] = "3";
//              this.prereqs = null;
            } else if (courseNumber.equals("2221")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("2231")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("2321")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("2331")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("2421")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("2431")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("2501")) {
                creditPrereqsArray[0] = "1";
//            this.prereqs = null;
            } else if (courseNumber.equals("3231")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("3241")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("3341")) {
                creditPrereqsArray[0] = "1";
//            this.prereqs = null;
            } else if (courseNumber.equals("3421")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("3461")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("3521")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("3541")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("3901")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("3902")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("3903")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("5911")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("5912")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("5913")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("5914")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("5915")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else {
                creditPrereqsArray[0] = "0";
//            this.prereqs = null;
            }
        } else if (department.equals("ENGR")) {
            if (courseNumber.equals("1100")) {
                creditPrereqsArray[0] = "1";
//            this.prereqs = null;
            } else if (courseNumber.equals("1181")) {
                creditPrereqsArray[0] = "4";
            } else if (courseNumber.equals("1182")) {
                creditPrereqsArray[0] = "4";
            } else if (courseNumber.equals("1196")) {
                creditPrereqsArray[0] = "4";
            } else if (courseNumber.equals("2367")) {
                creditPrereqsArray[0] = "4";
            }
        } else if (department.equals("ENGLISH")) {
            if (courseNumber.equals("1110")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            }
        } else if (department.equals("EARTHSC")) {
            if (courseNumber.equals("1121")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            }
        } else if (department.equals("COMM")) {
            if (courseNumber.equals("1100")) {
                creditPrereqsArray[0] = "3";
            } if (courseNumber.equals("2110")) {
                creditPrereqsArray[0] = "3";
            }
        } else if (department.equals("RURALSOC")) {
            if (courseNumber.equals("1500")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            }
        } else if (department.equals("COMPSTD")) {
            if (courseNumber.equals("2105")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            }
        } else if (department.equals("AFAMAST")) {
            if (courseNumber.equals("1121")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            }
        } else if (department.equals("THEATRE")) {
            if (courseNumber.equals("2811")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            }
        } else if (department.equals("LING")) {
            if (courseNumber.equals("3801")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            }
        } else if (department.equals("ECE")) {
            if (courseNumber.equals("2060")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("2020")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            }
        }  else if (department.equals("MATH")) {
            if (courseNumber.equals("1151")) {
                creditPrereqsArray[0] = "5";
//            this.prereqs = null;
            } else if (courseNumber.equals("1172")) {
                creditPrereqsArray[0] = "5";
//            this.prereqs = null;
            } else if (courseNumber.equals("2153")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("2255")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("2415")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("2568")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            } else if (courseNumber.equals("3345")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            }
        } else if (department.equals("PHILOS")) {
            if (courseNumber.equals("1138")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            } else if (courseNumber.equals("1332")) {
                creditPrereqsArray[0] = "4";
//            this.prereqs = null;
            }
        } else if (department.equals("PHYSICS")) {
            if (courseNumber.equals("1250")) {
                creditPrereqsArray[0] = "5";
//            this.prereqs = null;
            }
        } else if (department.equals("STAT")) {
            if (courseNumber.equals("3470")) {
                creditPrereqsArray[0] = "3";
//            this.prereqs = null;
            }
        }


            return creditPrereqsArray;
    }
}
