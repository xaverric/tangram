package core.graphics.graphicselements;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

public class MenuGraphicsElements {
    private TabPane tabPane;
    private Button startButton;
    private Label notificationLabel;

    public TabPane getTabPane() {
        return tabPane;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public Button getStartButton() {
        return startButton;
    }

    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }

    public Label getNotificationLabel() {
        return notificationLabel;
    }

    public void setNotificationLabel(Label notificationLabel) {
        this.notificationLabel = notificationLabel;
    }
}
