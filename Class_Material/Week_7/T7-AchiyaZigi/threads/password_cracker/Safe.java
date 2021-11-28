package threads.password_cracker;

import java.io.Console;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Safe {
    static Scanner sc = new Scanner(System.in);
    static Console console = System.console();

    public static final Pattern textPattern = Pattern.compile("([a-z]|[0-9])*");

    private String password;

    public Safe() {
        System.out.println("enter password with no capital letters up to 6 chars:");
        this.password = String.valueOf(console.readPassword());

        while (password.length() > 6 || !textPattern.matcher(password).matches()) {
            System.out.println("bad password please try again:");
            this.password = String.valueOf(console.readPassword());
        }
        System.out.println("password saved");
    }

    public boolean open(String code) {
        return code.equals(this.password);
    }
}
