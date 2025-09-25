package dpsetter.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import dpsetter.filehandling.ReadExcelFile;
import dpsetter.filehandling.SavedControllers;
import dpsetter.filehandling.WriteDescription;
import dpsetter.filehandling.WriteNames;
import dpsetter.filehandling.WriteSetupValues;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SetterService {

	ReadExcelFile excelReader;
	SavedControllers saveControllers;
	WriteNames namesWriter;
	WriteDescription descriptionWriter;
	WriteSetupValues valueWriter;

	public byte[] generateExcelFile(MultipartFile file) throws IOException, IllegalArgumentException {
		XSSFWorkbook wb = excelReader.getWorkbookFromExcelFile(file);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		saveControllers.saveControllers(wb);
		namesWriter.writeNames(saveControllers.getSavedControllers());
		valueWriter.writeValues(saveControllers.getSavedControllers());
		descriptionWriter.writeDescription(saveControllers.getSavedControllers());
		wb.write(outputStream);
		outputStream.close();
		saveControllers.getSavedControllers().clear();
		return outputStream.toByteArray();

	}
}
