package bcd.exercise.crypto.solution;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxReader {

    //Helper class
    private FileInputStream fis;
    private XSSFWorkbook wb;

    private String targetFile;
    private final String DELIMITER = ",";
    private final String DATE_FORMAT = "MMddyyyy";

    //constructor
    public XlsxReader(String fileName) {
        this.targetFile = fileName;
        try {
            fis = new FileInputStream(targetFile);
            wb = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //getAll
    public List<String> getAll(int sheetNum, int rowFrom, int rowTo, int cellFrom, int cellTo) {
        return toList(wb.getSheetAt(sheetNum), rowFrom, rowTo, cellFrom, cellTo);
    }

    private List<String> toList(XSSFSheet sheet, int rowFrom, int rowTo, int cellFrom, int cellTo) {
        rowFrom = rowFrom - 1;
        List<String> newLst = new ArrayList<>();

        for (Row row : sheet) {
            int rowNum = row.getRowNum();

            if (rowNum >= rowFrom && rowNum < rowTo) {
                StringBuilder line = new StringBuilder();
                for (int i = (cellFrom - 1); i < cellTo; i++) {
                    CellType ct = row.getCell(i).getCellTypeEnum();
                    switch (ct) {
                        case STRING:
                            line.append(row.getCell(i).getStringCellValue());

                            break;
                        case NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(row.getCell(i))) {
                                Calendar cal = Calendar.getInstance();
                                Date d = row.getCell(i).getDateCellValue();
                                cal.setTime(d);
                                String dt = new SimpleDateFormat(DATE_FORMAT).format(cal.getTime());
                                line.append( dt );
                            }else{
                                line.append(Double.toString(row.getCell(i).getNumericCellValue()));
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                    if (i != (cellTo - 1)) {
                        line.append(DELIMITER);
                    }
                }
                newLst.add(line.toString());
            }

        }

        return newLst;
    }

}
