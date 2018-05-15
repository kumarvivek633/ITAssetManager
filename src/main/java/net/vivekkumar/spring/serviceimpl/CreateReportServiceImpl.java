/*
 *
 */
package net.vivekkumar.spring.serviceimpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.vivekkumar.spring.model.Asset;
import net.vivekkumar.spring.service.CreateReportService;

/**
 * The Class CreateReportServiceImpl.
 */
@Service
public class CreateReportServiceImpl implements CreateReportService {

    /** The Constant DATE_CELL_FRMT. */
    public static final WritableCellFormat DATE_CELL_FRMT;
    static {
        DateFormat df = new DateFormat("dd/MM/yyyy");
        df.getDateFormat().setTimeZone(TimeZone.getTimeZone("GMT"));
        DATE_CELL_FRMT = new WritableCellFormat(df);
    }

    /** The times bold underline. */
    private WritableCellFormat timesBoldUnderline;

    /** The times. */
    private WritableCellFormat times;

    /**
     * Adds the caption.
     *
     * @param sheet
     *            the sheet
     * @param column
     *            the column
     * @param row
     *            the row
     * @param s
     *            the s
     * @throws RowsExceededException
     *             the rows exceeded exception
     * @throws WriteException
     *             the write exception
     */
    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    /**
     * Adds the date.
     *
     * @param sheet
     *            the sheet
     * @param column
     *            the column
     * @param row
     *            the row
     * @param date
     *            the date
     * @throws WriteException
     *             the write exception
     * @throws RowsExceededException
     *             the rows exceeded exception
     */
    private void addDate(WritableSheet sheet, int column, int row, Date date)
            throws WriteException, RowsExceededException {
        DateTime dateTime;
        dateTime = new DateTime(column, row, date, DATE_CELL_FRMT);
        sheet.addCell(dateTime);
    }

    /**
     * Adds the label.
     *
     * @param sheet
     *            the sheet
     * @param column
     *            the column
     * @param row
     *            the row
     * @param s
     *            the s
     * @throws WriteException
     *             the write exception
     * @throws RowsExceededException
     *             the rows exceeded exception
     */
    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }

    /**
     * Adds the number.
     *
     * @param sheet
     *            the sheet
     * @param column
     *            the column
     * @param row
     *            the row
     * @param value
     *            the value
     * @throws WriteException
     *             the write exception
     * @throws RowsExceededException
     *             the rows exceeded exception
     */
    private void addNumber(WritableSheet sheet, int column, int row, Long value)
            throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, value, times);
        sheet.addCell(number);
    }

    /**
     * Creates the content.
     *
     * @param sheet
     *            the sheet
     * @param asset
     *            the asset
     * @throws WriteException
     *             the write exception
     * @throws RowsExceededException
     *             the rows exceeded exception
     */
    private void createContent(WritableSheet sheet, List<Asset> asset) throws WriteException, RowsExceededException {
        for (int i = 0; i < asset.size(); i++) {
            addLabel(sheet, 0, i + 1, asset.get(i).getAssetId());
            addLabel(sheet, 1, i + 1, asset.get(i).getAssetType());
            addNumber(sheet, 2, i + 1, asset.get(i).getUser().getEmpId());
            addLabel(sheet, 3, i + 1,
                    asset.get(i).getUser().getFirstName() + " " + asset.get(i).getUser().getLastName());
            addDate(sheet, 4, i + 1, asset.get(i).getAllocatedOn());
            if (asset.get(i).getReturnedOn() != null) {
                addDate(sheet, 5, i + 1, asset.get(i).getReturnedOn());
            }

        }

    }

    /**
     * Creates the label.
     *
     * @param sheet
     *            the sheet
     * @throws WriteException
     *             the write exception
     */
    private void createLabel(WritableSheet sheet) throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        // Write a few headers
        addCaption(sheet, 0, 0, "Asset Id");
        addCaption(sheet, 1, 0, "Asset Type");
        addCaption(sheet, 2, 0, "Allocated to Emp Id");
        addCaption(sheet, 3, 0, "Allocated to Emp Name");
        addCaption(sheet, 4, 0, "Allocated On");
        addCaption(sheet, 5, 0, "Returned On");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.vivekkumar.spring.service.CreateReportService#createReport(java.util.
     * List)
     */
    @Override
    public void createReport(List<Asset> asset) throws IOException, WriteException {
        FileOutputStream file = new FileOutputStream("Asset_Allocation_report.xls");
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Asset Allocation Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet, asset);

        workbook.write();
        workbook.close();
        file.close();
    }
}
