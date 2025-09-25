package dpsetter.controller;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import dpsetter.service.SetterService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("setter/")
public class DPSetterController {
	
	SetterService setterService;
	
	@PostMapping("upload")
	public ResponseEntity<byte[]> uploadxlsm(MultipartFile file) throws IOException, IllegalArgumentException {
		byte[] processedFile = setterService.generateExcelFile(file);
		return new ResponseEntity<>(processedFile, HttpStatus.OK);
	}
}
