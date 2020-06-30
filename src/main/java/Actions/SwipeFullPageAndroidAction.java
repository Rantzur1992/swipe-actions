package Actions;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import org.openqa.selenium.Dimension;

@Action(name = "Swipe full page down/up PIXEL 2", description = "Swipe entire screen page either up or down")
public class SwipeFullPageAndroidAction implements AndroidAction {

    @Parameter(description = "The duration of the swipe in milliseconds", direction = ParameterDirection.INPUT, defaultValue = "1000")
    public int swipeDuration;
    @Parameter(description = "The duration of sleep between swipes in milliseconds", direction = ParameterDirection.INPUT, defaultValue = "1000")
    public int waitDuration;
    @Parameter(description = "How many swipes to perform", direction = ParameterDirection.INPUT, defaultValue = "1")
    public int amountOfSwipes;
    @Parameter(direction = ParameterDirection.INPUT, description = "Swipe up or down (Enter up or down)", defaultValue = "down")
    public String UpOrDown;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
        Dimension screen = helper.getDriver().manage().window().getSize();
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
        int counter = amountOfSwipes;
        while( counter-- > 0){
            helper.getDriver().testproject().swipeGesture(swipeDuration, startAndEndX, startY, startAndEndX, endY);
            Utils.sleepAbit(waitDuration);
        }

        return ExecutionResult.PASSED;
    }
}
