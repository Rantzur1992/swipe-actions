package Actions;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.IOSAction;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import org.openqa.selenium.Dimension;

@Action(name = "Swipe full page down/up", description = "Swipe entire screen page either up or down")
public class SwipeFullPageIOSAction implements IOSAction {

    @Parameter(description = "The duration of the swipe in milliseconds", direction = ParameterDirection.INPUT, defaultValue = "1000")
    private int swipeDuration;
    @Parameter(description = "The duration of sleep between swipes in milliseconds", direction = ParameterDirection.INPUT, defaultValue = "1000")
    private int waitDuration;
    @Parameter(description = "How many swipes to perform", direction = ParameterDirection.INPUT, defaultValue = "1")
    private int amountOfSwipes;
    @Parameter(direction = ParameterDirection.INPUT, description = "Swipe up or down (Enter up or down)")
    private String UpOrDown;

    @Override
    public ExecutionResult execute(IOSAddonHelper helper) throws FailureException {
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
        int counter = amountOfSwipes;
        while( counter-- > 0){
            helper.getDriver().testproject().swipeGesture(swipeDuration, startAndEndX, startY, startAndEndX, endY);
            Utils.sleepAbit(waitDuration);
        }

        return ExecutionResult.PASSED;
    }
}
