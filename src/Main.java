import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        YearlyReport yearReport = null;
        ArrayList<MonthlyReport> listOfMonthlyReports = null;
        ReportReader reader = new ReportReader(".\\resources");

        Scanner scanner = new Scanner(System.in);
        boolean exitCondition = false;
        while (!exitCondition) {
            printMenu();
            while (!scanner.hasNextInt()) {
                System.out.println("Неправильный формат ввода. Введите число из меню!");
                scanner.next();
            }
            int command = scanner.nextInt();
            switch (command) {
                case 0: {
                    exitCondition = true;
                    break;
                }
                case 1: {
                    listOfMonthlyReports = reader.readMonthlyReports();
                    if (listOfMonthlyReports.isEmpty())
                        System.out.println("Не удалось считать месячные отчеты. " +
                                "Возможно, в директории отсутствуют файлы.");
                    break;
                }
                case 2: {
                    yearReport = reader.readYearReport();
                    if (yearReport == null) System.out.println("Не удалось считать годовой отчет. " +
                            "Возможно, в директории отсутствует файл.");
                    break;
                }
                case 3: {
                    if ((yearReport != null) && (listOfMonthlyReports != null))
                        checkOfMismatches(yearReport, listOfMonthlyReports);
                    else System.out.println("Перед сверкой необходимо выполнить операции 1 и 2!");
                    break;
                }
                case 4: {
                    if (listOfMonthlyReports != null && !listOfMonthlyReports.isEmpty()) {
                        for (MonthlyReport report : listOfMonthlyReports) {
                            report.printReport();
                        }
                    } else System.out.println("Перед печатью отчетов необходимо выполнить операцию 1!");
                    break;
                }
                case 5: {
                    if (yearReport != null) yearReport.printReport();
                    else System.out.println("Перед печатью отчета необходимо выполнить операцию 2!");
                    break;
                }
                default: System.out.println("Неправильный номер команды. Введите номер команды из меню!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду из списка и введите номер команды: ");
        System.out.println("1 - Считать все месячные отчеты;");
        System.out.println("2 - Считать годовой отчет;");
        System.out.println("3 - Сверить отчеты;");
        System.out.println("4 - Вывести информацию о всех месячных отчетах;");
        System.out.println("5 - Вывести информацию о годовом отчете;");
        System.out.println("0 - Выйти из программы.");
    }

    private static void checkOfMismatches(YearlyReport yearReport,
                                          ArrayList<MonthlyReport> listOfMonthlyReports) {
        String line ="Сверка прошла успешно";
        ArrayList<String> listOfMismatches = new ArrayList<>();
        /*
         сравниваем месячные отчеты с годовыми, если есть несоответствие добавляем
         название месяца в список
         */
        for ( MonthlyReport monthlyReport: listOfMonthlyReports ) {
            String result = compareTotalValues(yearReport, monthlyReport);
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

    private static String compareTotalValues(YearlyReport yearReport, MonthlyReport monthlyReport) {
        String result ="Сверка прошла успешно";

        for (YearlyRecord record: yearReport.getListOfRecords()) {
            int monthReportIndex = monthlyReport.getMonthIndex();
            int yearReportIndex = record.getMonthIndex();
            if ( ( yearReportIndex == monthReportIndex) && !record.isExpense() ) {
                if (record.getAmount() != monthlyReport.getTotalIncome())
                    result = MonthlyReport.MONTHS[monthReportIndex-1];
            }
            if ( (yearReportIndex == monthReportIndex) && record.isExpense() ) {
                if (record.getAmount() != monthlyReport.getTotalExpense())
                    result = MonthlyReport.MONTHS[monthReportIndex-1];
            }
        }
        return result;
    }
}