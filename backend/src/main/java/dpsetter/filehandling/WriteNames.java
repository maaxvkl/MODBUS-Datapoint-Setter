package dpsetter.filehandling;

import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component
public class WriteNames {

	private final int NAMEBASE = 1;
	private final int OBJECT_ID = 3;
	private final int NAME = 5;

	public void writeNames(Map<String, List<Row>> savedControllers) {
		for (Map.Entry<String, List<Row>> controller : savedControllers.entrySet()) {
			List<Row> controllerRows = controller.getValue();
			Row firstRow = controllerRows.get(0);
			String nameBase = firstRow.getCell(NAMEBASE).getStringCellValue();
			for (Row row : controllerRows) {
				String objectID = row.getCell(OBJECT_ID).getStringCellValue();
				String finalName = nameBase + objectID;
				row.getCell(NAME).setCellValue(finalName);
			}
		}
	}
}
