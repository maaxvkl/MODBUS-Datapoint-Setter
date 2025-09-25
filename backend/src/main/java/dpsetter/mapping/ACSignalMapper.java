package dpsetter.mapping;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ACSignalMapper {

	private final Map<String, String> mapping = new HashMap<>();

	public ACSignalMapper() {

		mapping.put("Status EIN", "Normalbetrieb");
		mapping.put("Status AUS", "Aus");
		mapping.put("Status ERHOEHT", "erhoehte Luftmenge");
		mapping.put("Status GESENKT", "reduzierte Luftmenge");
		mapping.put("Status ALARM", "Stoerung VSR");
		mapping.put("Fehlernummer", "Alarmnummer");
		mapping.put("Sollwert Luft", "Sollw. Volumenstrom");
		mapping.put("Istwert Luft", "Istw. Volumenstrom");
		mapping.put("Istwert Winkel", "Klappenstellung");
		mapping.put("Status Betriebsart", "Betriebsart");
		mapping.put("Status GLT Alarm", "Status Stoermeldung");
		
		
	}

	public String map(String rawSignal) {
		return mapping.getOrDefault(rawSignal, rawSignal);
	}

}
