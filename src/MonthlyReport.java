/*
Класс месячного отчета.
 */

import java.util.ArrayList;

public class MonthlyReport {
    public static final String[] MONTHS = {"Январь", "Февраль", "Март", "Апрель",
            "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    private int year;
    private int monthIndex;
    private ArrayList<MonthlyRecord> listOfRecords;

    public MonthlyReport(String date) {

        if ( date.length() != 6 ) {
            System.out.println("Неправильный формат даты");
        } else {
            year = Integer.parseInt(date.substring(0,5));
            monthIndex = Integer.parseInt(date.substring(5));
        }
        listOfRecords = new ArrayList<>();
    }

    public int getMonthIndex()	{ return monthIndex; }
    public int getYear() { return year; }
    public ArrayList<MonthlyRecord> getListOfRecords() { return listOfRecords; }

    public void setYear(int year) { this.year = year; }
    public void setMonthIndex(int monthIndex) { this.monthIndex = monthIndex; }
    public void setListOfRecords(ArrayList<MonthlyRecord> listOfRecords) {
        if (listOfRecords != null) {
            this.listOfRecords = listOfRecords;
        }
    }

    public void addRecord(MonthlyRecord record) {
        if (record != null) {
            listOfRecords.add(record);
        } else {
            System.out.println("Невозможно добавить запись.");
        }
    }

    public String getMostIncomeItem(){
        if (!listOfRecords.isEmpty()) {
            int maxSum = 0;
            String itemName = "";

            for (MonthlyRecord record :listOfRecords) {
                int profit = record.getQuantity()*record.getSumOfOne();
                if ( ( !record.isExpense() ) && ( profit > maxSum )) {
                    maxSum = profit;
                    itemName = record.getItemName();
                }
            }
            return "Самый прибыльный товар за месяц - " + itemName + ", сумма дохода: " + maxSum + ".";
        } else {
            return "В отчете отсутствуют записи.";
        }
    }

    public String getMostExpenseItem() {
        if (!listOfRecords.isEmpty()) {
            int maxSum = 0;
            String itemName = "";

            for (MonthlyRecord record :listOfRecords) {
                int expense = record.getQuantity()*record.getSumOfOne();
                if ( ( record.isExpense() ) && ( expense > maxSum )) {
                    maxSum = expense;
                    itemName = record.getItemName();
                }
            }
            return "Самая большая трата за месяц - " + itemName + ", потрачено: " + maxSum + ".";
        } else {
            return "В отчете отсутствуют записи.";
        }
    }

    public int getTotalIncome() {
        if (!listOfRecords.isEmpty()) {
            int sum = 0;

            for (MonthlyRecord record :listOfRecords) {
                if (!record.isExpense())  {
                    sum += record.getQuantity()*record.getSumOfOne();
                }
            }

            return sum;
        } else {
            return -1;
        }
    }

    public int getTotalExpense() {
        if (!listOfRecords.isEmpty()) {
            int sum = 0;

            for (MonthlyRecord record :listOfRecords) {
                if ( record.isExpense() ) {
                    sum += record.getQuantity()*record.getSumOfOne();
                }
            }

            return sum;
        } else {
            return -1;
        }
    }

    public void printReport() {
        System.out.println(MonthlyReport.MONTHS[monthIndex-1]);
        System.out.println(getMostIncomeItem());
        System.out.println(getMostExpenseItem());
    }
}
