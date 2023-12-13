package uz.code.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerUtils {
    @Autowired
    private  Scanner scanner;

    public String nextLine(String s) {
        System.out.print(s);
        String str = scanner.nextLine();
        return str;
    }

    public  int nextInt(String s) {
        int number;
        do {
            try {
                System.out.print(s);
                number = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println();
                System.out.println("Enter only numbers");
                scanner.nextLine();
            }
        } while (true);
        return number;
    }
}
