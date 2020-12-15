package mk.ukim.finki.wp.lab.model.enumerators;

import java.util.Random;

public enum CourseType {
        WINTER,
        SUMMER,
        MANDATORY,
        ELECTIVE;

        public static CourseType randomType() {
            int pick = new Random().nextInt(CourseType.values().length);
            return CourseType.values()[pick];
        }
}
