/*
Класс для считывания отчетов из исходных файлов.
 */

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.IOException;

public class ReportReader {

    private String directory;  //директория с отчетами
    private File[] listFiles;  //список файлов в директории

    public ReportReader(String directory) {
        this.directory = directory;
        listFiles = new File(directory).listFiles();
    }

    public String getDirectory() { return directory; }
    public File[] getListFiles() { return listFiles; }

    public void setDirectory(String directory) { this.directory =directory; }

    public ArrayList<MonthlyReport> readMonthlyReports() {
        ArrayList<MonthlyReport> listOfMonthlyReports = new ArrayList<>();
        // цикл для поиска месячных отчетов в директории
        if (listFiles.length != 0) {
            for ( File report: listFiles ) {
                String[] reportName = report.getName().split("\\.");
                //проверяем является ли файл месячным отчетом с нужным расширением
                if (reportName[0].equals("m") && reportName[reportName.length-1].equals("csv")) {
                    //cчитываем содержимое исходного файла
                    String fileContents = readFileContentsOrNull(report);
                    if (fileContents != null) {
                        String[] reportLines = fileContents.split("\\n");
                        String date = reportName[1];
                        // создаем объект месячного отчета без записей
                        MonthlyReport monthlyReport = new MonthlyReport(date);
                        // добавляем записи в отчет
                        ReportWriter.writeMonthlyReportContents(monthlyReport, reportLines);
                        listOfMonthlyReports.add(monthlyReport);
                    }
                }
            }
        }

        return listOfMonthlyReports;
    }

    public YearlyReport readYearReport() {
        YearlyReport yearReport = null;
        // цикл для поиска годовых отчетов в директории
        if (listFiles.length != 0) {

            for (File report : listFiles) {
                String[] reportName = report.getName().split("\\.");
                //проверяем является ли файл годовым отчетом с нужным расширением
                if (reportName[0].equals("y") && reportName[reportName.length - 1].equals("csv")) {
                    yearReport = new YearlyReport(reportName[1]);
                    //cчитываем содержимое исходного файла
                    String fileContents = readFileContentsOrNull(report);
                    if (fileContents != null) {
                        String[] reportLines = fileContents.split("\\n");
                        ReportWriter.writeYearReportContents(yearReport, reportLines);
                    }
                }
            }
        }
        return yearReport;
    }

    private static String readFileContentsOrNull(File file) {
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            return null;
        }
    }
}
