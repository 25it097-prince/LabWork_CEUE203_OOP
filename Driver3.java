import java.util.Scanner;

public class Driver3{

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        System.out.println("Enter Password: ") ;
        String pw=sc.next();
            System.out.println("Password: " + pw);

            System.out.println("Length >= 8: " + PasswordChecker.checkLen(pw));

            System.out.println("Uppercase: " + PasswordChecker.Uppercase(pw));

            System.out.println("Digit: " + PasswordChecker.Digit(pw));

            System.out.println("Special character: " + PasswordChecker.SpecialChar(pw));

            System.out.println("Strength: "
                    + PasswordChecker.strength(pw));

            System.out.println("-------------------------");

            sc.close();
        }
    }