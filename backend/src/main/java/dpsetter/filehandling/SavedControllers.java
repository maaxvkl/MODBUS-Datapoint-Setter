package dpsetter.filehandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import lombok.Getter;

@Getter
@Component
public class SavedControllers {

	private final int CONTROLLER = 0;
	private final int MODBUS_DP = 0;
	Map<String, List<Row>> savedControllers = new HashMap<>();

	public void saveControllers(XSSFWorkbook wb) {
		XSSFSheet workSheet = wb.getSheetAt(MODBUS_DP);
		for (int i = 2; i <= workSheet.getLastRowNum(); i++) {
			Row row = workSheet.getRow(i);
			if (row == null)
				continue;

			Cell controllerCell = row.getCell(CONTROLLER);
			if (controllerCell == null || controllerCell.getCellType() == CellType.BLANK)
				continue;

			String controllerNumber = controllerCell.getStringCellValue().trim();
			if (controllerNumber.isEmpty())
				continue;

			savedControllers.computeIfAbsent(controllerNumber, k -> new ArrayList<>()).add(row);
		}
	}
}
