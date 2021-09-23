package display;

import constraint.MessageConstraint;
import util.PropertiesUtil;

public class DisplayManger implements MessageConstraint {

    public void showGameRuleSummary() {
        System.out.println(PropertiesUtil.getValueString(PROPERTIES_FILE_NAME_MICE_AND_MEN, "msg.beginning.summary"));
    }
}
