package gui;


import com.sun.javafx.application.LauncherImpl;
import gui.components.preloader.GamePreloader;
import gui.window.menu.MainMenu;

public class Main {

    private Main() {
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(MainMenu.class, GamePreloader.class, args);
    }
}
