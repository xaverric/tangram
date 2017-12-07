package gui.components.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {

    private Alerts() {
    }

    public static Optional<ButtonType> showNewGameConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Game");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Current game progress will be lost");
        return alert.showAndWait();
    }

    public static Optional<ButtonType> showExitAppConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Current game progress will be lost");
        return alert.showAndWait();
    }

    public static void showNoNextBoardWarningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Sorry");
        alert.setHeaderText("There is no next board");
        alert.setContentText("You will be returned back to main menu");
        alert.showAndWait();
    }
}
