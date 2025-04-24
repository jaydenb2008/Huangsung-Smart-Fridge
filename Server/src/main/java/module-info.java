module Server {
    requires Common;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.data.jpa;
    requires spring.context;
    requires spring.beans;
    requires spring.web;

    exports edu.sdccd.cisc191.server to spring.beans;
    exports edu.sdccd.cisc191.server.controllers to spring.beans;
}