package com.acme.dbo.txlog;

public class Facade {

    private static final String PREFIX_PRIMITIVE = "primitive: ";
    private static final String PREFIX_CHAR = "char: ";
    private static final String PREFIX_STRING = "string: ";
    private static final String PREFIX_REFERENCE = "reference: ";

    private static final String POSTFIX_PRIMITIVE = "";
    private static final String POSTFIX_CHAR = "";
    private static final String POSTFIX_STRING = "";
    private static final String POSTFIX_REFERENCE = "";

    private static final String TYPE_INT = "INT";
    private static final String TYPE_STR = "STR";
    private static final String TYPE_CHAR = "CHAR";
    private static final String TYPE_BOOL = "BOOL";
    private static final String TYPE_BYTE = "BYTE";
    private static final String TYPE_REFERENCE = "REFERENCE";

    private static String CURRENT_TYPE = "";
    private static Object Accumulator = null;


    private static String decorate(String prefix, Object message, String postfix) {
        return prefix + message + postfix;
    }

    private static void print(String DecoratedMessage){
        System.out.println(DecoratedMessage);
    }

    public static void flush() {
        switch (CURRENT_TYPE) {
            case TYPE_STR:
                print(decorate(PREFIX_STRING, Accumulator, POSTFIX_STRING));
                break;
            default:
                print(decorate(PREFIX_PRIMITIVE, Accumulator, POSTFIX_PRIMITIVE));
        }
        Accumulator = null;
        CURRENT_TYPE = "";
    }

    private static void setAccumulator(Object message, String type) {
        Accumulator = message;
        CURRENT_TYPE = type;
    }

    public static void log(int message) {
        if (CURRENT_TYPE == TYPE_INT){
            if (message == Integer.MAX_VALUE | (int)Accumulator == Integer.MAX_VALUE) {
                flush();
                setAccumulator(message, TYPE_INT);
            } else Accumulator = (int)Accumulator + message;
        } else {
            if (CURRENT_TYPE != "") flush();
            setAccumulator(message, TYPE_INT);
        }
    }

    public static void log(byte message) {
        if (CURRENT_TYPE == TYPE_BYTE){
            if (message == Byte.MAX_VALUE | (Byte)Accumulator == Byte.MAX_VALUE) {
                flush();
                setAccumulator(message, TYPE_BYTE);
            } else setAccumulator(message, TYPE_BYTE);
        } else {
            if (CURRENT_TYPE != "") flush();
            setAccumulator(message, TYPE_BYTE);
        }
    }

    public static void log(char message) {
        print(decorate(PREFIX_CHAR, message, POSTFIX_CHAR));
    }

    public static void log(String message) {
        if (CURRENT_TYPE == TYPE_STR){
            flush();
            setAccumulator(message, TYPE_STR);
        } else {
            if (CURRENT_TYPE != "") flush();
            setAccumulator(message, TYPE_STR);
        }
    }

    public static void log(boolean message) {
        print(decorate(PREFIX_PRIMITIVE, message, POSTFIX_PRIMITIVE));
    }

    public static void log(Object message) {
        print(decorate(PREFIX_REFERENCE, message, POSTFIX_REFERENCE));
    }
}
