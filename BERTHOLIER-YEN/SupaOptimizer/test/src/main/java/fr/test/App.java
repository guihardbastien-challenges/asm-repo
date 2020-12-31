package fr.test;

/**
 * Hello world!
 */
public class App {
    private String value1;
    private String value2;

    public App(String truc, String machin) {
        this.value1 = truc;
        this.value2 = machin;
    }

    public static void main(String[] args) {
        App app = new App("toto", "3");
        App.staticMethod();
        app.voidMethod();
        app.memberMethodInt();
        app.memberMethodDouble();
        app.memberMethodFloat();
        app.memberMethodLong();
        app.memberMethodByte();
        app.memberMethodChar();
        app.memberMethodShort();
        System.out.println(app.memberMethod());
    }

    public String memberMethod() {
        return value1;
    }

    public int memberMethodInt() {
        return 3;
    }
    public double memberMethodDouble() {
        return 3.50;
    }

    public float memberMethodFloat() {
        return (float) 4;
    }

    public long memberMethodLong() {
        return 350;
    }

    public byte memberMethodByte() {
        return (byte) 5;
    }

    public char memberMethodChar() {
        return 'A';
    }

    public short memberMethodShort() {
        return (short) 50;
    }


    public void voidMethod() {
        System.out.println(value2);
    }

    public static String staticMethod(){
        return "TOTO";
    }
}
