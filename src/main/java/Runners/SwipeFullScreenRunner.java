package Runners;

import Actions.ElementActions.SwipePageAndSearchElementAndroid;
import Actions.ElementActions.SwipePageAndSearchElementIOS;
import Actions.SwipeFullPageAndroidAction;
import Actions.SwipeFullPageIOSAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import io.testproject.java.sdk.v2.Runner;
import org.openqa.selenium.By;

public class SwipeFullScreenRunner {
    public static void main(String[] args) {
        try {
            runAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runAction() throws Exception {
        SwipePageAndSearchElementIOS action = new SwipePageAndSearchElementIOS();
        action.setMaximumAmountOfSwipes(2);
        action.setUpOrDown("down");
        action.setSwipeDuration(1000);
        action.setWaitDuration(1000);
        By by = By.xpath("(//*[@name='See more'])[2]");
        Runner runner = Runner.createIOS("kJCeheoppFyo8uaZ17k0JQyBck1qLIf5ZrynbI6t7Fk1", "6db046d88d4e10d8860a05868f0a3324a6c8f5ce", "iPhone SE", "com.amazon.AmazonUK");
        runner.run(action, by, true, 0);
    }
}
