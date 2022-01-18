/*
Класс годового отчета.
 */

import java.util.ArrayList;

public class YearlyReport {
    private int year;
    private ArrayList<YearlyRecord> listOfRecords;

    public YearlyReport (String date) {
        if (date.length()!=4) {
            System.out.println("Неправильный формат даты");
        } else {
            year = Integer.parseInt(date);
        }
        listOfRecords = new ArrayList<>();
    }

    public int getYear() { return year; }
    public ArrayList<YearlyRecord> getListOfRecords() { return listOfRecords; }

    public void setYear(int year) { this.year = year; }
    public void setListOfRecords(ArrayList<YearlyRecord> listOfRecords) {
        if (listOfRecords != null) {
            this.listOfRecords = listOfRecords;
        }
    }

    public void addRecord(YearlyRecord record) {
        if (record != null) {
            listOfRecords.add(record);
        } else {
            System.out.println("Невозможно добавить запись.");
        }
    }

    public double getAverageIncome() {
        int sum = 0;
        int monthNumber = 12;
        for (YearlyRecord record: listOfRecords) {
            if (!record.isExpense()) {
                sum += record.getAmount();
            }
        }
        return (double) sum/monthNumber;
    }

    public double getAverageExpense() {
        int sum = 0;
        int monthNumber = 12;
        for (YearlyRecord record: listOfRecords) {
            if (record.isExpense()) {
                sum += record.getAmount();
            }
        }
        return (double) sum/monthNumber;
    }

    public int getProfitByMonth(int monthIndex){
        int incomeByMonth = 0;
        int expenseByMonth = 0;
        for (YearlyRecord record: listOfRecords) {
            if ((record.getMonthIndex() == monthIndex) && (!record.isExpense()))
                incomeByMonth = record.getAmount();
        }
        for (YearlyRecord record: listOfRecords) {
            if ((record.getMonthIndex() == monthIndex) && (record.isExpense()))
                expenseByMonth = record.getAmount();
        }
        return incomeByMonth - expenseByMonth;
    }

    public void printReport() {
        System.out.println(year);
        int monthNumber = 12;
        for (int i = 1; i <= monthNumber; i++) {
            System.out.println(MonthlyReport.MONTHS[i-1] + ", прибыль: " + getProfitByMonth(i));
        }
        String averageExpense = String.format("Cредний расход за год: %.2f", getAverageExpense());
        String averageIncome = String.format("Cредний доход за год: %.2f", getAverageIncome());
        System.out.println(averageExpense);
        System.out.println(averageIncome);
    }

    public String compareTotalValues(MonthlyReport monthlyReport) {
        String result ="Сверка прошла успешно";

        for (YearlyRecord record: listOfRecords) {
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
