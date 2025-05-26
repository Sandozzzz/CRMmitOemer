module com.winston.crm_mit_oemer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.winston.crm_mit_oemer to javafx.fxml;
    exports com.winston.crm_mit_oemer;
}