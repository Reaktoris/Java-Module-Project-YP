import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Calculator calculator = new Calculator();
    static Product product = new Product();
    static Form form = new Form();
    public static void main(String[] args) {
        System.out.println("На скольких человек необходимо разделить счёт?");
        int numberOfPeople = scanner.hasNextInt()? scanner.nextInt() : 0;
        while (numberOfPeople<2) {
            System.out.println("Ошибка. Введите корректное значение.");
            scanner.nextLine();
            numberOfPeople = scanner.hasNextInt()? scanner.nextInt() : 0;
        }
        calculator.calculate(product, scanner, numberOfPeople);
        System.out.println("Добавленные товары:\n" + product.name + "\nКаждый должен заплатить по " + calculator.strResult + " " + form.ruble(calculator.dResult));
    }
}


class Product {
    String name = "";
    Double price = 0.0;

}
class Calculator {
    String strResult;
    Double dResult;
    void calculate(Product product, Scanner scanner, int numberOfPeople) {
        double price;
        while (true) {
            System.out.println("Введите название товара.");
            product.name += scanner.next() + "\n";
            System.out.println("Введите стоимость товара.");
            scanner.nextLine();
            price = scanner.hasNextDouble()? scanner.nextDouble() : -1.0;
            while (price < 0) {
                System.out.println("Ошибка. Введите корректное значение.");
                scanner.nextLine();
                price = scanner.hasNextDouble()? scanner.nextDouble() : -1.0;
            }
            product.price += price;
            System.out.println("Хотите добавить ещё один товар? Введите 'Завершить', если нет");
            if (scanner.next().equalsIgnoreCase("завершить")) {break;}
        }
        dResult = product.price / numberOfPeople;
        strResult = String.format("%.2f", product.price / numberOfPeople);
    }
}
class Form {
    String ruble(double price) {
        int intPrice = (int) (price);
        if (intPrice % 100 / 10 == 1f) {
            return "рублей";
        } else if (intPrice % 10 > 1 && price % 10 < 5) {
            return "рубля";
        } else if (intPrice %10 ==1){
            return "рубль";
        } else {return "рублей";}
    }
}