package space.readingrainbow.tychos.simulator;

enum Epoch {
    J2000, B1950
}

public class EquatorialCoords {
    Epoch reference;

    double rightAscensionHours;
    double rightAscensionMinutes;
    double rightAscensionSeconds;

    double declinationHours;
    double declinationMinutes;
    double declinationSeconds;

}
