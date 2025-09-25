package dpsetter.mapping;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GCSignalMapper {

	private final Map<String, String> mapping = new HashMap<>();

	public GCSignalMapper() {

		mapping.put("SetStatus ERHOEHT", "Schaltbefehl Raum erhoehte Luft");
		mapping.put("Status EIN", "Raum Status Tag (Normalbetrieb)");
		mapping.put("Status AUS", "Raum Status Aus");
		mapping.put("Status ERHOEHT", "Raum Status erhoehte Luftmenge");
		mapping.put("Status GESENKT", "Raum Status Nacht (red. Luft)");
		mapping.put("Status ALARM", "Raum Sammelstoerung");
		mapping.put("Sollwert Raumzuluft", "Raumzuluft Sollwert Volumenstrom");
		mapping.put("Istwert Raumzuluft", "Raumzuluft Istwert Volumenstrom");
		mapping.put("Sollwert Raumabluft", "Raumabluft Sollwert Volumenstrom");
		mapping.put("Istwert Raumabluft", "Raumabluft Istwert Volumenstrom");
		mapping.put("Status Betriebsart", "Betriebsart");
		mapping.put("Set Status EIN", "Schaltbefehl Raum Tag (Normalbetrieb)");
		mapping.put("Set Status AUS", "Schaltbefehl Raum Aus");
		mapping.put("Set Status GESENKT", "Schaltbefehl Raum red. Luftmenge");
		mapping.put("Set Status Betriebsart", "Raum Schaltbefehl Betriebsart");
		mapping.put("Set Status ERHOEHT", "Schaltbefehl Raum Erh. Luftmenge");
		mapping.put("Status Sammelalarm", "Raum Status Sammelalarm");
		mapping.put("Istwert Summe Abl Gesamt", "Raum Status Sammelalarm");
        mapping.put("Sollwert Summe Verbr Abl", "Abluft Abzuege Summe Sollwert");
        mapping.put("Istwert Summe Verbr Abl", "Abluft Abzuege Summe Istwert");
        mapping.put("Sollwert Summe Raum Abl", "Raumabluft Summe Sollwert");
        mapping.put("Istwert Summe Raum Abl", "Raumabluft Summe Istwert");
        mapping.put("Sollwert Summe  Abl Gesamt", "Raumabluft Summe Sollwert Gesamt"); //checken! kam mit einem leerzeichen zu viel
        mapping.put("Istwert Summe  Abl Gesamt", "Raumabluft Summe Istwert Gesamt"); //checken! kam mit einem leerzeichen zu viel
		
	}

	public String map(String rawSignal) {
		return mapping.getOrDefault(rawSignal, rawSignal);
	}

}
