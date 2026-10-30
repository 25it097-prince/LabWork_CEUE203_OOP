public class ChatFilter {

    public static String filterLogs(String[] logs, String keyword) {
        int matchCount = 0;
        StringBuilder report = new StringBuilder();
        String lowerKeyword = keyword.toLowerCase();

        for (String line : logs) {
            
            String[] parts = line.split(" ", 3);

            if (parts.length < 3) {
                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            if (message.toLowerCase().contains(lowerKeyword)) {
                matchCount++;
                report.append(time)
                        .append(" ")
                        .append(user)
                        .append(": ")
                        .append(message)
                        .append("\n");
            }
        }

        return "Matches: " + matchCount + "\n" + report.toString();
    }
}