package de.unikiel.klik.energychallenge;

public class Config {

    //public static final String SERVER_REST_PATH = "http://192.168.0.3:8080/Server/App/"; //Nick Notebook
    //public static final String SERVER_REST_PATH = "http://192.168.0.83:8080/Server/App/"; //Lennard Notebook
    public static final String SERVER_REST_PATH = "http://192.168.0.102:8080/Server/App/"; //Samuel Notebook
    //public static final String SERVER_REST_PATH = "http://192.168.178.27:8080/Server/App/"; //Sören Home
    //public static final String SERVER_REST_PATH = "http://luedtkegmbh.de/Soeren/Softwareprojekt/App/"; //Static serverdata

    public static final boolean LOGIN_REQUIRED = true;

    //Better use averting caching on server side
    public static final boolean AVERT_SERVER_CACHING = true;
}
