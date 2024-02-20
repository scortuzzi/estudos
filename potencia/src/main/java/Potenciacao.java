import java.util.Scanner;

public class Potenciacao {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("entre com o primeiro número:");
        double numero01 = scanner.nextDouble();
        double numero01Template = numero01;

        System.out.println("entre com o número da potência:");
        double numero02 = scanner.nextDouble();

        for(int i = 1; i < numero02; i++) {
            numero01Template = numero01Template * numero01;
            System.out.println(numero01Template);
        }

        System.out.println(numero01 + " elevado á " + numero02 + " é: " + numero01Template);


    }
}
