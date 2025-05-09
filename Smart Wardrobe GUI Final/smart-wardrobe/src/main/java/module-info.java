module com.wardrobe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.wardrobe to javafx.fxml;
    exports com.wardrobe;
}
