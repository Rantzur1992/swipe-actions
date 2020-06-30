package Actions.ElementActions;

import Actions.Utils;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.IOSElementAction;
import io.testproject.java.sdk.v2.addons.helpers.IOSAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import org.openqa.selenium.Dimension;

import java.time.Duration;

@Action(name = "Swipe left/right to search for an element", description = "Swipes left/right and searches for the element")
public class SwipePageSidewaysAndSearchElementIOS implements IOSElementAction {

    @Parameter(description = "The duration of the swipe in milliseconds", direction = ParameterDirection.INPUT, defaultValue = "1000")
    private int swipeDuration;
    @Parameter(description = "The duration of sleep between swipes in milliseconds", direction = ParameterDirection.INPUT, defaultValue = "2000")
    private int waitDuration;
    @Parameter(description = "Maximum amount of swipes to perform", direction = ParameterDirection.INPUT, defaultValue = "1")
    private int maximumAmountOfSwipes;
    @Parameter(direction = ParameterDirection.INPUT, description = "Swipe left or right (Enter left or right)", defaultValue = "left")
    private String LeftOrRight;

    @Override
    public ExecutionResult execute(IOSAddonHelper helper, IOSElement element) throws FailureException {
        Dimension size = helper.getDriver().manage().window().getSize();

        int startX = 0;
        int endX = 0;
        int startY = 0;

        while (maximumAmountOfSwipes-- > 0 || !element.isDisplayed()) {
            switch (LeftOrRight.toLowerCase()) {
                case "left":
                    startY = (int) (size.height / 2);
                    startX = (int) (size.width * 0.05);
                    endX = (int) (size.width * 0.90);
                    new TouchAction(helper.getDriver())
                            .press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipeDuration)))
                            .moveTo(PointOption.point(endX, startY))
                            .release()
                            .perform();
                    break;
                case "right":
                    startY = (int) (size.height / 2);
                    startX = (int) (size.width * 0.90);
                    endX = (int) (size.width * 0.05);
                    new TouchAction(helper.getDriver())
                            .press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipeDuration)))
                            .moveTo(PointOption.point(endX, startY))
                            .release()
                            .perform();
                    break;
                default:
                    throw new FailureException("Please enter left or right only!");
            }

            Utils.sleepAbit(waitDuration);
        }

        if(element.isDisplayed())
            helper.getReporter().result("Element has been found!");
        else
            helper.getReporter().result("Element was not found.");
        return ExecutionResult.PASSED;
    }
}
