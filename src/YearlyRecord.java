/*
Класс строки годового отчета.
 */


public class YearlyRecord {
    private int monthIndex;
    private int amount;
    private boolean isExpense;

    public YearlyRecord(int monthIndex, int amount, boolean isExpense) {
        this.monthIndex = monthIndex;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    public int getMonthIndex() { return monthIndex; }
    public int getAmount() { return amount; }
    public boolean isExpense() { return isExpense; }
}
