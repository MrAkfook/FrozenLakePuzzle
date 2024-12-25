package Objects;

import java.util.Locale;

public enum Experiment {
    TEMPERATURE_MEASUREMENT, WIND_SPEED_MEASUREMENT, CAMERA_PLACEMENT, GLACIAL_SAMPLING;

    @Override
    public String toString() {
        String name = name().toLowerCase(Locale.ENGLISH);
        String[] words = name.split("_");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0)))
              .append(word.substring(1))
              .append(" ");
        }
        return result.toString().trim();
    }
}
