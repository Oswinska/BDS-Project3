module vut.cz.bpcbdsproject3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;
    requires org.controlsfx.controls;


    opens vut.cz.bpcbdsproject3 to javafx.fxml;
    exports vut.cz.bpcbdsproject3;
}