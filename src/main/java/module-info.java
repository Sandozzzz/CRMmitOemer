module com.winston.crm_mit_oemer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.winston.crm_mit_oemer to javafx.fxml;
    exports com.winston.crm_mit_oemer;
    exports com.winston.crm_mit_oemer.controller;
    opens com.winston.crm_mit_oemer.controller to javafx.fxml;
    exports com.winston.crm_mit_oemer.controller.tasks;
    opens com.winston.crm_mit_oemer.controller.tasks to javafx.fxml;
    exports com.winston.crm_mit_oemer.controller.customers;
    opens com.winston.crm_mit_oemer.controller.customers to javafx.fxml;
    exports com.winston.crm_mit_oemer.controller.personals;
    opens com.winston.crm_mit_oemer.controller.personals to javafx.fxml;

}