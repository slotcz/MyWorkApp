package cz.o2.tvs.accessory;

import cz.o2.tvs.stb.servlet.RequestSTB;

import java.util.logging.Logger;

public class Version {

    private static final String DOT = "\\.";

    int first;
    int second;
    int third;

    public Version(String sVers) {
        super();
        String[] version = sVers.trim().split(DOT);
        first = Integer.valueOf(version[0]);
        second = Integer.valueOf(version[1]);
        third = Integer.valueOf(version[2]);
    }


    public int compareTo(String fwVersion) {
        int result = -1;
        String[] version = fwVersion.split(DOT);
        int frst = Integer.valueOf(version[0]);
        int scnd = Integer.valueOf(version[1]);
        int thrd = Integer.valueOf(version[2]);
        if (frst > first) {
            result = 1;
        } else {
            if (scnd > second) {
                result = 1;
            } else {
                if (thrd > third) {
                    result = 1;
                } else {
                    if (thrd == third) {
                        result = 0;
                    }
                }
            }
        }
        return result;
    }
}
