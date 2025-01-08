package com.anudip;

import java.io.IOException;


public class App {
    public static void main(String[] args) throws NumberFormatException, IOException {
        System.out.println("**************Employee Management System**************");
        Menu menu=new Menu();
        menu.displayMenu();
    }
}
