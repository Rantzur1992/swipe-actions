package Actions.ElementActions;

import io.appium.java_client.android.AndroidElement;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidElementAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;

@Action(name = "Swipe full page up or down searching for an element", description = "Searches for an element while swiping full pages up or down")
public class SwipePageAndSearchElementAndroid implements AndroidElementAction {
    @Parameter(description = "The duration of the swipe in milliseconds", direction = ParameterDirection.INPUT, defaultValue = "1000")
    public static int swipeDuration;
    @Parameter(description = "The duration of sleep between swipes in milliseconds", direction = ParameterDirection.INPUT, defaultValue = "1000")
    public static int waitDuration;
    @Parameter(description = "Maximum amount of swipes to perform", direction = ParameterDirection.INPUT, defaultValue = "1")
    public static int maximumAmountOfSwipes;
    @Parameter(direction = ParameterDirection.INPUT, description = "Swipe up or down (Enter up or down)", defaultValue = "down")
    public static String UpOrDown;

    @Override
    public ExecutionResult execute(AndroidAddonHelper helper, AndroidElement element) throws FailureException {
        return Base.execute(helper.getDriver().manage().window().getSize(), helper.getDriver(), element, true
                , swipeDuration
                , waitDuration
                , maximumAmountOfSwipes
                , UpOrDown);
    }
}
