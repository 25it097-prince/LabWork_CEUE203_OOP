public class RemoteControl {
    public interface Switchable {
        void on();
        void off();

        default void toggle() {
            System.out.println("Toggling device...");
            on();
        }
    }
    public static class Fan implements Switchable {
        private boolean isOn = false;

        @Override
        public void on() {
            isOn = true;
            System.out.println("Fan is now ON.");
        }

        @Override
        public void off() {
            isOn = false;
            System.out.println("Fan is now OFF.");
        }
    }

    public static class Light implements Switchable {
        private boolean isOn = false;

        @Override
        public void on() {
            isOn = true;
            System.out.println("Light is now ON.");
        }

        @Override
        public void off() {
            isOn = false;
            System.out.println("Light is now OFF.");
        }
    }

    @FunctionalInterface
    public interface ScheduleChecker {
        boolean maySwitchOn(Switchable device, int hour);
    }

    public static void main(String[] args) {
        Switchable[] devices = { new Fan(), new Light() };
        System.out.println("--- Toggling Devices ---");
        for (Switchable device : devices) {
            device.toggle();
        }

        ScheduleChecker anonymousChecker = new ScheduleChecker() {
            @Override
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        ScheduleChecker lambdaChecker = (device, hour) -> hour >= 8 && hour <= 18;
        int testHour = 14;
        System.out.println("\n--- Testing Schedule Checkers at Hour " + testHour + " ---");
        System.out.println("Anonymous checker allows Fan? " + anonymousChecker.maySwitchOn(devices[0], testHour));
        System.out.println("Lambda checker allows Light? " + lambdaChecker.maySwitchOn(devices[1], testHour));
    }
}
