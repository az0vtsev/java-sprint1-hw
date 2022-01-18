import java.util.ArrayList;

public class ReportManager {
    private YearlyReport yearReport;
    private ArrayList<MonthlyReport> listOfMonthlyReports;
    private String directory;
    private ReportReader reader ;

    public ReportManager(String directory) {
        this.directory = directory;
        reader = new ReportReader(directory);
    }

    public YearlyReport getYearlyReport() { return yearReport; }
    public ArrayList<MonthlyReport> getListOfMonthlyReports() { return listOfMonthlyReports; }
    public String getDirectory() { return directory; }
    public ReportReader getReportReader() { return reader; }

    public void setNewDirectory(String directory) {
        this.directory = directory;
        reader = new ReportReader(directory);
    }

    public void readMonthlyReports() {
        listOfMonthlyReports = reader.readMonthlyReports();
        if (listOfMonthlyReports.isEmpty())
            System.out.println("Не удалось считать месячные отчеты. " +
                    "Возможно, в директории отсутствуют файлы.");
    }

    public void readYearlyReport() {
        yearReport = reader.readYearReport();
        if (yearReport == null) System.out.println("Не удалось считать годовой отчет. " +
                "Возможно, в директории отсутствует файл.");
    }

    public void checkReports() {
        if ((yearReport != null) && (listOfMonthlyReports != null))
            checkOfMismatches();
        else System.out.println("Перед сверкой необходимо выполнить операции 1 и 2!");
    }

    private void checkOfMismatches() {
        String line ="Сверка прошла успешно";
        ArrayList<String> listOfMismatches = new ArrayList<>();
        /*
         сравниваем месячные отчеты с годовыми, если есть несоответствие добавляем
         название месяца в список
         */
        for ( MonthlyReport monthlyReport: listOfMonthlyReports ) {
            String result = yearReport.compareTotalValues(monthlyReport);
            if ( !result.equals(line) ) listOfMismatches.add(result);
        }
        // если в списке несоответствий есть значения, печатаем названия месяцев из списка
        if (!listOfMismatches.isEmpty()) {
            for (String month: listOfMismatches) {
                System.out.println(month);
            }
        } else {
            System.out.println(line);
        }
    }

    public void printMonthlyReports() {
        if (listOfMonthlyReports != null && !listOfMonthlyReports.isEmpty()) {
            for (MonthlyReport report : listOfMonthlyReports) {
                report.printReport();
            }
        } else System.out.println("Перед печатью отчетов необходимо выполнить операцию 1!");
    }

    public void printYearlyReport() {
        if (yearReport != null) yearReport.printReport();
        else System.out.println("Перед печатью отчета необходимо выполнить операцию 2!");
    }
}
