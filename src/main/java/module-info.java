module vut.cz.bpcbdsproject3 {
    requires bcrypt;
    requires org.slf4j;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.zaxxer.hikari;
    requires javafx.controls;
    requires org.controlsfx.controls;

    opens vut.cz.bpcbdsproject3 to javafx.fxml;
    opens vut.cz.bpcbdsproject3.controllers to javafx.fxml;
    exports vut.cz.bpcbdsproject3;
    exports vut.cz.bpcbdsproject3.controllers;

}