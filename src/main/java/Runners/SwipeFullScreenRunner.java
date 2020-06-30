package Runners;

import Actions.SwipeFullPageAndroidAction;
import Actions.SwipeFullPageIOSAction;
import io.testproject.java.sdk.v2.Runner;

public class SwipeFullScreenRunner {
    public static void main(String[] args) {
        try {
            runAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runAction() throws Exception {
        Runner runner = Runner.createAndroid("kJCeheoppFyo8uaZ17k0JQyBck1qLIf5ZrynbI6t7Fk1", "emulator-5556", "com.ninegag.android.app", "com.ninegag.android.app.ui.home.HomeActivity");
        runner.run(new SwipeFullPageAndroidAction(), true);
    }
}
