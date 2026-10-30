import java.util.Scanner;
public class DriverChatFilter {
        public static void main(String[] args) {

            String[] logs = {
                    "10:05 alice Hello there",
                    "10:06 bob How are you",
                    "malformed line"
            };

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter keyword: ");
            String keyword = sc.nextLine();

            System.out.println(ChatFilter.filter(logs, keyword));

            sc.close();
        }
}

