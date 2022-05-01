module pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires spring.web;

    opens pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui to javafx.fxml;
    exports pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui;
    exports pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto;
    exports pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.rest;
    exports pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.table;
}