package com.acme.dbo.txlog;

public class Facade {

    public static final String PREFIX_PRIMITIVE = "primitive: ";
    public static final String PREFIX_CHAR = "char: ";
    public static final String PREFIX_STRING = "string: ";
    public static final String PREFIX_REFERENCE = "reference: ";

    public static final String POSTFIX_PRIMITIVE = "";
    public static final String POSTFIX_CHAR = "";
    public static final String POSTFIX_STRING = "";
    public static final String POSTFIX_REFERENCE = "";

    private static String decorate(String prefix, Object message, String postfix) {
        return prefix + message + postfix;
    }

    private static void print(String DecoratedMessage){
        System.out.println(DecoratedMessage);
    }

    public static void log(int message) {
        print(decorate(PREFIX_PRIMITIVE, message, POSTFIX_PRIMITIVE));
    }

    public static void log(byte message) {
        print(decorate(PREFIX_PRIMITIVE, message, POSTFIX_PRIMITIVE));
    }

    public static void log(char message) {
        print(decorate(PREFIX_CHAR, message, POSTFIX_CHAR));
    }

    public static void log(String message) {
        print(decorate(PREFIX_STRING, message, POSTFIX_STRING));
    }

    public static void log(boolean message) {
        print(decorate(PREFIX_PRIMITIVE, message, POSTFIX_PRIMITIVE));
    }

    public static void log(Object message) {
        print(decorate(PREFIX_REFERENCE, message, POSTFIX_REFERENCE));
    }
}
