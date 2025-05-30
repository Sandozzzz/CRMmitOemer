module com.winston.crm_mit_oemer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.winston.crm_mit_oemer to javafx.fxml;
    exports com.winston.crm_mit_oemer;
    exports com.winston.crm_mit_oemer.controller;
    opens com.winston.crm_mit_oemer.controller to javafx.fxml;
}