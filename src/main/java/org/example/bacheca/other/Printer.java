package org.example.bacheca.other;

//classe che si occupa la stampa di messaggi
public class Printer {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET1 = "\u001B[0m";


    private Printer(){}

    //stampa
    public static void print(String message){
        System.out.print(message);
    }

    //stampa e va a capo
    public static void println(String message){
        System.out.println(message);
    }

    //stampa la guida per ogni pagina in CLI
    public static void printlnBlu(String message) {
        System.out.println(ANSI_BLUE + message + ANSI_RESET1);
    }
    public static void printBlu(String message) { System.out.print(ANSI_BLUE + message + ANSI_RESET1);}


    public static void printlnVerde(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET1);
    }
    public static void printVerde(String message) { System.out.print(ANSI_GREEN + message + ANSI_RESET1);}

    //stampa messaggio di errore
    public static void errorPrintln(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }
    public static void errorPrint(String message) { System.out.print(ANSI_RED + message + ANSI_RESET); }
}
