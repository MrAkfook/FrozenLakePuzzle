package Objects;

public enum Experiment {
    TemperatureMeasurement,WindSpeedMeasurement,CameraPlacement,GlacialSampling;

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(this.name().charAt(0));
        for (int i = 1; i < this.name().length(); i++) {
            char c = this.name().charAt(i);
            if (Character.isUpperCase(c)) {
            sBuilder.append(' ');
            }
            sBuilder.append(c);
        }
        return sBuilder.toString();
    }
}
