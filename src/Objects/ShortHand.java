package Objects;

public enum ShortHand {
    TD, WS, CM, CH, CL, WB, PH, NO;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
