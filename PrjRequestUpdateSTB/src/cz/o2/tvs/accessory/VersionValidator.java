package cz.o2.tvs.accessory;

import java.util.regex.Pattern;

public class VersionValidator {

    private static final String VERSION_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private final Pattern pattern;

    public VersionValidator() {
        pattern = Pattern.compile(VERSION_PATTERN);
    }

    public boolean validate(String version) {
        return pattern.matcher(version).matches();
    }

}
