package space.readingrainbow.tychos.simulator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Getter @Log
public class CelestialConfig {

    String test;
    List<CelestialPoint> bodies;
    // double gregorianDays;
    // List<Star> stars;

    //DEFINE TIME CONSTANTS
    double yearLength = 365.2425;
    double solarDay = 1 / yearLength;
    double solarYear = solarDay * 365;
    double solarMonth = solarDay * 30;
    double solarWeek = solarDay * 7;
    double solarHour = solarDay / 24;
    double solarMinute = solarHour / 60;
    double solarSecond = solarMinute / 60;


    public CelestialConfig(InputStream stream) throws CelestialException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        bodies = new ArrayList<>();
        try {
            JsonNode rootNode = mapper.readTree(stream);

            for(final JsonNode bodyNode : rootNode.get("bodies")) {
                bodies.add(marshalCelestialPoint(bodyNode));
            }

            test = marshalString(rootNode.get("test"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CelestialPoint marshalCelestialPoint(JsonNode node) {
        return CelestialPoint.builder()
                .name(marshalString(node.get("name")))
                .startPosition(marshalNumeric(node.get("startPosition")))
                .rotationalSpeed(marshalNumeric(node.get("rotationalSpeed")))
                .orbitalSpeed(marshalNumeric(node.get("orbitalSpeed")))
                .tiltA(marshalNumeric(node.get("tiltA")))
                .tiltB(marshalNumeric(node.get("tiltB")))
                .markerLength(marshalNumeric(node.get("markerLength")))
                .orbit(marshalOrbit(node.get("orbit")))
                .body(marshalBody(node))
                .build();
    }

    private Orbit marshalOrbit(JsonNode node) {
        if (node == null) return null;

        JsonNode primaryNode = node.get("primary");
        CelestialPoint primary;
        if (!primaryNode.isObject()) {
            primary = bodies.stream()
                    .filter(p -> p.getId().equals(primaryNode.textValue()))
                    .findFirst().get();
        } else {
            primary = marshalCelestialPoint(primaryNode);
        }

        // TODO: a primary body is required for an orbit
//      if (primary == null) throw new ConfigurationException();

        return Orbit.builder()
                .radius(marshalNumeric(node.get("radius")))
                .centerA(marshalNumeric(node.get("centerA")))
                .centerB(marshalNumeric(node.get("centerB")))
                .tiltA(marshalNumeric(node.get("tiltA")))
                .tiltB(marshalNumeric(node.get("tiltB")))
                .color(marshalColor(node.get("color")))
                .primary(primary)
                .build();
    }

    private Body marshalBody(JsonNode node) {
        JsonNode radiusNode = node.get("size");
        JsonNode colorNode = node.get("color");
        if (radiusNode != null && colorNode != null) {
            return new Body(marshalNumeric(radiusNode), marshalColor(colorNode));
        }
        return null;
    }

    private String marshalString(JsonNode textNode) {
        if (textNode != null) {
            return textNode.asText();
        }
        return null;
    }

    private double marshalNumeric(JsonNode formulaNode) {
        if (formulaNode != null) {
            return formulaNode.asDouble();
        }
        return 0;
    }

    private Color marshalColor(JsonNode colorNode) {
        if (colorNode != null) {
            return Color.valueOf(colorNode.asText());
        }
        return null;
    }
}
