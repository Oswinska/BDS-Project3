module vut.cz.bpcbdsproject3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens vut.cz.bpcbdsproject3 to javafx.fxml;
    exports vut.cz.bpcbdsproject3;
}