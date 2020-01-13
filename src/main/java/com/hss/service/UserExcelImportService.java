package com.hss.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserExcelImportService {

	String handlerUserExcelImport(MultipartFile file);
}
