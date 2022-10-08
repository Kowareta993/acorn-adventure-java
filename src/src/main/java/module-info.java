module com.acorn.acorn {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.acorn.acorn to javafx.fxml;
    exports com.acorn.acorn;
}