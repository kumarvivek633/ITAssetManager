/*
 *
 */
package net.vivekkumar.spring.service;

import java.io.IOException;
import java.util.List;

import jxl.write.WriteException;
import net.vivekkumar.spring.model.Asset;

/**
 * The Interface CreateReportService.
 */
public interface CreateReportService {

    /**
     * Creates the report.
     *
     * @param asset
     *            the asset
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws WriteException
     *             the write exception
     */
    public void createReport(List<Asset> asset) throws IOException, WriteException;
}
