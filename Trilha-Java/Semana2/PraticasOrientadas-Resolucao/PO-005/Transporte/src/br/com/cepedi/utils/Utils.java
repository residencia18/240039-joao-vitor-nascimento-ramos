package br.com.cepedi.utils;

import java.io.IOException;

public abstract class Utils {

    public final static void clearConsole() throws InterruptedException, IOException {
    	new ProcessBuilder("clear").inheritIO().start().waitFor();
    }
}
