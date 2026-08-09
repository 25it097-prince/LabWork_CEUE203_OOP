public class PasswordChecker {

        public static boolean checkLen(String pw) {
            return pw.length() >= 8;
        }

        public static boolean Uppercase(String pw) {
            return pw.matches(".*[A-Z].*");
        }

        public static boolean Digit(String pw) {
            return pw.matches(".*[0-9].*");
        }

        public static boolean SpecialChar(String pw) {
            return pw.matches(".*[^a-zA-Z0-9].*");
        }

        public static String strength(String pw) {
            int count = 0;

            if (checkLen(pw))
                count++;

            if (Uppercase(pw))
                count++;

            if (Digit(pw))
                count++;

            if (SpecialChar(pw))
                count++;

            if (count <= 1)
                return "Weak";
            else if (count <= 3)
                return "Medium";
            else
                return "Strong";
        }
    }
