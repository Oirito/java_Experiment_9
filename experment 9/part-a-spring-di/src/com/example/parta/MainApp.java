package com.example.parta;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Student s = context.getBean(Student.class);
        s.showInfo();
        context.close();
    }
}
