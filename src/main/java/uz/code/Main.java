package uz.code;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uz.code.config.SpringConfig;
import uz.code.control.Control;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);
        Control control = (Control) context.getBean("control");

        control.start();


    }
}