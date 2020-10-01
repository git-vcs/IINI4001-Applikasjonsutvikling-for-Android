import java.io.*;
import java.net.*;
// Denne koden stammer fra eksempel-kode utgitt for ø4 i faget
// TDAT2004 Datakommunikasjon med nettverksprogrammering

class Backend {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;
        ServerSocket tjener = new ServerSocket(PORTNR);
        System.out.println("Logg for tjenersiden. Nå venter vi...");

        while (true){
            // venter inntil noen tar kontakt
            Socket forbindelse = tjener.accept();
            TraadKlientHaandterer traad=new TraadKlientHaandterer(forbindelse);
            traad.start();        }
    }
}

