package dpsetter.mapping;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ACCSignalMapper {

	private final Map<String, String> mapping = new HashMap<>();

	public ACCSignalMapper() {

		mapping.put("Fehlernummer", "Status Alarmnummer");
		mapping.put("Sollwert Luft", "Sollw. Volumenstrom");
		mapping.put("Istwert Luft", "Istw. Volumenstrom");
		mapping.put("Istwert Winkel", "Klappenstellung");
		mapping.put("Istwert Druck", "Istwert Druck");
		mapping.put("Status GLT Alarm", "Status Fehlernummer");
		mapping.put("Betriebsart", "Betriebsart");		
		mapping.put("Status GLT Alarm", "Status Fehlernummer");
		
	}

	public String map(String rawSignal) {
		return mapping.getOrDefault(rawSignal, rawSignal);
	}

}
