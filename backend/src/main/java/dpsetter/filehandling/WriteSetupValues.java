package dpsetter.filehandling;

import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component
public class WriteSetupValues {

	private final int OBJECT_TYPE = 4;
	private final int BESCHREIBUNG = 6;
	private final int INTRINSINC_ALARMING = 9;
	private final int MELDUNGSKLASSE = 11;
	private final int BACNETOBJNAME = 8;

	public void writeValues(Map<String, List<Row>> savedControllers) {
		for (Map.Entry<String, List<Row>> controller : savedControllers.entrySet()) {
			List<Row> controllerRows = controller.getValue();
			for (Row row : controllerRows) {
				if (row.getCell(OBJECT_TYPE).getStringCellValue().contains("BI")
					&& row.getCell(BESCHREIBUNG).getStringCellValue().contains("ALARM")) {
					Cell cell = row.getCell(INTRINSINC_ALARMING);
					cell.setCellValue("True");
					cell = row.getCell(MELDUNGSKLASSE);
					cell.setCellValue(20);
				}
				Cell cell = row.getCell(BACNETOBJNAME);
				cell.setCellValue("True");

			}
		}
	}
}
