package dpsetter.filehandling;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ReadExcelFile {
	
	private final int MODBUS_DP = 0;
	
	public XSSFWorkbook getWorkbookFromExcelFile(MultipartFile file) throws IOException, IllegalArgumentException {
		InputStream inputStream = file.getInputStream();
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		if (wb.getSheetAt(MODBUS_DP).getSheetName().equals("MODBUS DP")) {
			inputStream.close();
			return wb;
		} else {
			inputStream.close();
			throw new IOException();
		}
	}
}
