/*
Класс для записи отчетов в объекты классов.
 */

public class ReportWriter {

    public static void writeMonthlyReportContents(MonthlyReport report, String[] contents){
        for (int i = 1; i < contents.length; i++) {
            String[] recordValues = contents[i].split(",");
            String itemName = recordValues[0];
            boolean isExpense = recordValues[1].equalsIgnoreCase("true");
            int quantity = Integer.parseInt(recordValues[2]);
            int sumOfOne = Integer.parseInt(recordValues[3]);
            //создаем объект строки месячного отчета и добавляем в отчет
            MonthlyRecord reportRecord = new MonthlyRecord(itemName, isExpense, quantity, sumOfOne);
            report.addRecord(reportRecord);
        }
    }

    public static void writeYearReportContents(YearlyReport report, String[] contents) {
        for (int i = 1; i < contents.length; i++) {
            String[] recordValues = contents[i].split(",");
            int monthIndex = Integer.parseInt(recordValues[0]);
            int amount = Integer.parseInt(recordValues[1]);
            boolean isExpense = recordValues[2].equalsIgnoreCase("true");
            //создаем объект строки годового отчета и добавляем в отчет
            YearlyRecord reportRecord = new YearlyRecord(monthIndex, amount, isExpense);
            report.addRecord(reportRecord);
        }
    }
}
