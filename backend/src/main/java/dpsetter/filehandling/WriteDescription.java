package dpsetter.filehandling;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;
import dpsetter.mapping.ACCSignalMapper;
import dpsetter.mapping.ACSignalMapper;
import dpsetter.mapping.GCSignalMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class WriteDescription {

	ACSignalMapper acMapper;
	GCSignalMapper gcMapper;
	ACCSignalMapper accMapper;

	private final int BESCHREIBUNG = 6;

	public void writeDescription(Map<String, List<Row>> savedControllers) {
		for (Map.Entry<String, List<Row>> controller : savedControllers.entrySet()) {
			List<Row> controllerRows = controller.getValue();
			if (controllerRows == null || controllerRows.isEmpty())
				continue;

			Row firstRow = controllerRows.get(0);
			Cell baseCell = firstRow.getCell(BESCHREIBUNG);
			if (baseCell == null)
				continue;

			String description = baseCell.getStringCellValue().trim(); // e.g. "NW-X X.X.XX.XX Labore BT X.X - GC_Sollwert Raumzuluft"
			if (description.isEmpty())
				continue;

			int idxDash = description.indexOf(" - ");
			String descriptionBase = (idxDash > 0) ? description.substring(0, idxDash + 3) // "NW-X X.X.XX.XX Labore BT X.X -"
					: description + " - ";

			for (Row row : controllerRows) {
				Cell cell = row.getCell(BESCHREIBUNG);
				if (cell == null)
					continue;

				String rawValue = cell.getStringCellValue().trim();
				if (rawValue.isEmpty())
					continue;

				// If rawValue already contains the description base, cut the description base
				// off, if not take the rawValue. Result e.g: "GC_Sollwert Raumzuluft"
				String signalValue = rawValue.startsWith(descriptionBase)
						? rawValue.substring(descriptionBase.length()).trim()
						: rawValue;

				if (signalValue.startsWith("GC")) {
					mapGCDescription(row, descriptionBase, signalValue, cell);
				} else if (signalValue.startsWith("ACC")) {
					mapACCDescription(row, descriptionBase, signalValue, cell);
				} else if (signalValue.startsWith("AC")) {
					mapACDescription(row, descriptionBase, signalValue, cell);
				} else {
					cell.setCellValue(descriptionBase + signalValue);
				}
			}
		}
	}

	private void mapACDescription(Row row, String descriptionBase, String signalValue, Cell cell) {
		String signalPart;
		String acPart;
		
		if (signalValue.contains("_")) {
			String[] parts = signalValue.split("_", 2);
			acPart = parts[0]; // e.g. "AC01"
			signalPart = parts[1]; // e.g. "Status Betriebsart Dig 1"
		} else {
			acPart = signalValue.substring(0, 4); // e.g "AC01"
			signalPart = signalValue.substring(4); // e.g "_Status Betriebsart Dig 1"
		}

		int regulatorNumber = Integer.parseInt(acPart.substring(2));

		// check if "Dig.X" is contained in the signalPart String
		Matcher digMatcher = Pattern.compile("Dig\\.?\\s*(\\d+)").matcher(signalPart); // e.g "Status Dig. 12 Alarm"
		if (digMatcher.find()) {
			regulatorNumber = Integer.parseInt(digMatcher.group(1)); // e.g "12"
			signalPart = signalPart.replace(digMatcher.group(0), "").trim(); // e.g "Status Alarm"
		} else {
			signalPart = signalPart.replaceAll("Dig\\.?\\s*\\d+", "").trim(); // e.g "Status Alarm"
		}

		// remove non-breaking spaces and whitespaces
		signalPart = signalPart.replace('\u00A0', ' ').replaceAll("\\s+", " ").trim();

		String mappedSignal = acMapper.map(signalPart);
		if (mappedSignal == null || mappedSignal.isEmpty())
			return;

		String finalName = descriptionBase + "Abzug " + regulatorNumber + " " + mappedSignal;
		cell.setCellValue(finalName);
	}

	private void mapGCDescription(Row row, String descriptionBase, String signalValue, Cell cell) {
		String signalPart;
		
		// remove non-breaking spaces and whitespaces
		signalPart = signalValue.replace('\u00A0', ' ').replaceAll("\\s+", " ").trim();

		if (signalValue.startsWith("GC_")) {
			signalPart = signalValue.substring(3).trim(); // e.g from "GC_Sollwert Raumzuluft" to "Sollwert Raumzuluft"
		}

		String mappedSignal = gcMapper.map(signalPart);

		if (mappedSignal == null || mappedSignal.isEmpty())
			return;

		String finalName = descriptionBase + mappedSignal;
		cell.setCellValue(finalName);
	}

	private void mapACCDescription(Row row, String descriptionBase, String signalValue, Cell cell) {
		String accPart;
		String signalPart;

		if (signalValue.contains("_")) {
			String[] parts = signalValue.split("_", 2);
			accPart = parts[0]; // e.g ACC30
			signalPart = parts[1]; // e.g Betriebsart Abl. 2
		} else {
			accPart = signalValue.substring(0, 5); // "ACCxx"
			signalPart = signalValue.substring(5); // e.g _Betriebsart Abl. 2
		}

		int accNumber = Integer.parseInt(accPart.substring(3));

		// remove Dig.X
		signalPart = signalPart.replaceAll("Dig\\.?\\s*\\d+", "").trim();

		// remove non-breaking spaces and whitespaces
		signalPart = signalPart.replace('\u00A0', ' ').replaceAll("\\s+", " ").trim();

		String statusPrefix = "Raum";
		String numberSuffix = "";

		if (signalPart.contains("Zul.")) {
			statusPrefix = "Raumzuluft";
			Matcher matcher = Pattern.compile("Zul\\.\\s*(\\d*)").matcher(signalPart); // e.g "Istwert Winkel Zul.2"
			if (matcher.find()) {
				numberSuffix = matcher.group(1) != null ? matcher.group(1).trim() : ""; // e.g "2"
				signalPart = signalPart.replace(matcher.group(0), "").trim(); // e.g "Istwert Winkel"
			} else {
				// also remove only "Zul." even if it comes without a number
				signalPart = signalPart.replace("Zul.", "").trim();
			}
		} else if (signalPart.contains("Abl.")) {
			statusPrefix = "Raumabluft";
			Matcher matcher = Pattern.compile("Abl\\.\\s*(\\d*)").matcher(signalPart); // e.g "Istwert Winkel Abl.2"
			if (matcher.find()) {
				numberSuffix = matcher.group(1) != null ? matcher.group(1).trim() : ""; // e.g "2"
				signalPart = signalPart.replace(matcher.group(0), "").trim(); // e.g "Istwert Winkel"
			} else {
				// also remove only "Abl." even if it comes without a number
				signalPart = signalPart.replace("Abl.", "").trim();
			}
		}

		String mappedSignal = accMapper.map(signalPart);
		if (mappedSignal == null || mappedSignal.isEmpty())
			return;

		String finalName;

		if (accNumber <= 2) {
			// ACC01 + ACC02
			finalName = descriptionBase + statusPrefix + " " + mappedSignal;   /* e.g NW-X X.X.XX.XX Labore BT X.X - Raumabluft Klappenstellung*/
		} else {
			// ACC30, ACC31
			finalName = descriptionBase + statusPrefix + (numberSuffix.isEmpty() ? "" : " " + numberSuffix) + " "   /* e.g NW-X X.X.XX.XX Labore BT X.X - Raumzuluft 2 Klappenstellung*/
					+ mappedSignal;
		}

		cell.setCellValue(finalName);
	}

}
