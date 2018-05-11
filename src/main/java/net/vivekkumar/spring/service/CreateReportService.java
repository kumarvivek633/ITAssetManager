package net.vivekkumar.spring.service;

import java.io.IOException;

import jxl.write.WriteException;

public interface CreateReportService {
	public void write() throws IOException, WriteException;
}
