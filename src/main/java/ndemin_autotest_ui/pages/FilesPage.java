package ndemin_autotest_ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Given;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$x;
import static ndemin_autotest_ui.Constants.FilesPageConstants.*;
import static ndemin_autotest_ui.PropertiesProvider.PATH_FILES;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.sizeOf;
import static org.junit.Assert.assertEquals;

public class FilesPage {

    String downloadFiles = "//a[@class='t1075__link ' ]//*[contains(text(), '";
    SelenideElement downloadFilePNG = $x(downloadFiles + FILES_PAGE_PNG + "')]//ancestor::a");
    SelenideElement downloadFileTXT = $x(downloadFiles + FILES_PAGE_TXT + "')]//ancestor::a");
    SelenideElement downloadFileExcel = $x(downloadFiles + FILES_PAGE_EXCEL + "')]//ancestor::a");

    String downloadFilePNGPath;
    String downloadFileTXTPath;
    String downloadFileExcelPath;

    @Given("Download file {string}")
    public void downloadFiles() throws FileNotFoundException {
        downloadFilePNGPath = downloadFilePNG.download().getAbsolutePath();
        downloadFileTXTPath = downloadFileTXT.download().getAbsolutePath();
        downloadFileExcelPath = downloadFileExcel.download().getAbsolutePath();
    }

    @Given("Delete all files")
    public void deleteAllFiles() throws IOException {
        FileUtils.deleteDirectory(new File(PATH_FILES));
    }

    @Given("Check txt file")
    public void checkFileTXT() throws IOException {
        assertEquals(readFileToString(new File(FILE_TXT_EXPECTED_PATH), StandardCharsets.UTF_8),
                readFileToString(new File(downloadFileTXTPath), StandardCharsets.UTF_8));
    }

    @Given("Check png file")
    public void checkFilePNG() throws IOException {
        assertEquals(sizeOf(new File(FILE_PNG_EXPECTED_PATH)),
                sizeOf(new File(downloadFilePNGPath)));
    }

    @Given("Check excel file")
    public void checkFileEXCEL() throws Exception {
        XSSFWorkbook xlsxBook = new XSSFWorkbook(downloadFileExcelPath);
        XSSFSheet sheet = xlsxBook.getSheetAt(0);

        assertEquals(FILE_EXCEL_RESULT,
                getCellData(sheet, 0, 0));
    }

    public static String getCellData(XSSFSheet sheet, int RowNum, int ColNum) throws Exception {
        try {
            XSSFCell cell = sheet.getRow(RowNum).getCell(ColNum);
            return cell.getStringCellValue();
        } catch (Exception e) {
            return "Error";
        }
    }
}