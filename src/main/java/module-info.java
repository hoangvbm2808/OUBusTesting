module com.myproject.oubus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires javafx.graphics;

    opens com.myproject.oubus to javafx.fxml;
    exports com.myproject.oubus;
    exports com.myproject.pojo;
    requires org.apache.commons.lang3;
}

