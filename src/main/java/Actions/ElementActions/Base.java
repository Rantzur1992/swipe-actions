package Actions.ElementActions;

import Actions.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.drivers.IOSDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import org.openqa.selenium.Dimension;

public class Base {
    public static ExecutionResult execute(Dimension screen, AppiumDriver driver, MobileElement element, boolean androidOrIos, int swipeDuration, int waitDuration, int maximumAmountOfSwipes, String UpOrDown) throws FailureException {
        int startY;
        int endY;
        if(UpOrDown.toLowerCase().equals("up")){
            startY = (int) (screen.height * 0.2);
            endY = (int) (screen.height * 0.8);
        } else if(UpOrDown.toLowerCase().equals("down")){
            startY = (int) (screen.height * 0.8);
            endY = (int) (screen.height * 0.2);
        } else
            throw new FailureException("Enter up or down only!");

        int startAndEndX = screen.width / 2; //Middle of the screen
        Utils.sleepAbit(1000);
        int counter = maximumAmountOfSwipes;
        while( counter-- > 0 || !element.isDisplayed()){
            if(androidOrIos)
                ((AndroidDriver)driver).testproject().swipeGesture(swipeDuration, startAndEndX, startY, startAndEndX, endY);
            else
                ((IOSDriver)driver).testproject().swipeGesture(swipeDuration, startAndEndX, startY, startAndEndX, endY);
            Utils.sleepAbit(waitDuration);
        }

        return ExecutionResult.PASSED;
    }
}
