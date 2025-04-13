module Server {
    requires Common;
    requires Client;

    exports edu.sdccd.cisc191.server to Client, Server, Common;
}