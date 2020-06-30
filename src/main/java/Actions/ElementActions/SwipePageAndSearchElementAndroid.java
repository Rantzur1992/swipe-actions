package Actions.ElementActions;

import Actions.Utils;
import io.appium.java_client.android.AndroidElement;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidElementAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import org.openqa.selenium.Dimension;

@Action(name = "Swipe full page up or down searching for an element", description = "Searches for an element while swiping full pages up or down")
public class SwipePageAndSearchElementAndroid implements AndroidElementAction {
    @Parameter(description = "The duration of the swipe in milliseconds", direction = ParameterDirection.INPUT, defaultValue = "1000")
    public int swipeDuration;
    @Parameter(description = "The duration of sleep between swipes in milliseconds", direction = ParameterDirection.INPUT, defaultValue = "1000")
    public int waitDuration;
    @Parameter(description = "Maximum amount of swipes to perform", direction = ParameterDirection.INPUT, defaultValue = "1")
    public int maximumAmountOfSwipes;
    @Parameter(direction = ParameterDirection.INPUT, description = "Swipe up or down (Enter up or down)", defaultValue = "down")
    public String UpOrDown;

    public void setSwipeDuration(int swipeDuration) {
        this.swipeDuration = swipeDuration;
    }

    public void setWaitDuration(int waitDuration) {
        this.waitDuration = waitDuration;
    }

    public void setMaximumAmountOfSwipes(int maximumAmountOfSwipes) {
        this.maximumAmountOfSwipes = maximumAmountOfSwipes;
    }

    public void setUpOrDown(String upOrDown) {
        UpOrDown = upOrDown;
    }

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper, AndroidElement element) throws FailureException {
        Dimension screen = helper.getDriver().manage().window().getSize();
        int startY;
        int endY;
        if(UpOrDown.toLowerCase().equals("up")){
            startY = (int) (screen.height * 0.1);
            endY = (int) (screen.height * 0.9);
        } else if(UpOrDown.toLowerCase().equals("down")){
            startY = (int) (screen.height * 0.9);
            endY = (int) (screen.height * 0.1);
        } else
            throw new FailureException("Enter up or down only!");

        int startAndEndX = screen.width / 2; //Middle of the screen
        Utils.sleepAbit(1000);
        int counter = maximumAmountOfSwipes;
        while( counter-- > 0 || !element.isDisplayed()){
            helper.getDriver().testproject().swipeGesture(swipeDuration, startAndEndX, startY, startAndEndX, endY);
            Utils.sleepAbit(waitDuration);
        }

        if(element.isDisplayed())
            helper.getReporter().result("Element has been found!");
        else
            helper.getReporter().result("Element was not found.");

        return ExecutionResult.PASSED;
    }
}
