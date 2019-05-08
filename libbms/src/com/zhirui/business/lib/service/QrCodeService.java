package com.zhirui.business.lib.service;

import java.util.List;

public interface QrCodeService {

	List<String> getAll();

	void creatQrCode(String sampleno);

}
