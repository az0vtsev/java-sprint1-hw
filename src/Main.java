import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        ReportManager reportManager = new ReportManager(".\\resources");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            while (!scanner.hasNextInt()) {
                System.out.println("Неправильный формат ввода. Введите число из меню!");
                scanner.next();
            }
            int command = scanner.nextInt();
            switch (command) {
                case 0: {
                    return;
                }
                case 1: {
                    reportManager.readMonthlyReports();
                    break;
                }
                case 2: {
                    reportManager.readYearlyReport();
                    break;
                }
                case 3: {
                    reportManager.checkReports();
                    break;
                }
                case 4: {
                    reportManager.printMonthlyReports();
                    break;
                }
                case 5: {
                    reportManager.printYearlyReport();
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
}