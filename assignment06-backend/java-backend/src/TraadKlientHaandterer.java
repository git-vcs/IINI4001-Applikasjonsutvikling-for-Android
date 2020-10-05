import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TraadKlientHaandterer extends Thread {
    private Socket forbindelse = null;
    public TraadKlientHaandterer(Socket forbindelse) throws IOException {
        this.forbindelse=forbindelse;
    }

    public void run(){
        try{
            //hele komunikasjonen med klient skal ligge i run()-metoden
            /* Åpner strømmer for kommunikasjon med klientprogrammet */
            InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
            BufferedReader leseren = new BufferedReader(leseforbindelse);
            PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);
            System.out.println("Kontakt!!");
            /* Mottar data fra klienten */
            String enLinje = leseren.readLine();  // mottar en linje med tekst
            while (enLinje != null && !enLinje.equals("close")) {  // forbindelsen på klientsiden er lukket
                System.out.println("Klient "+ this.getId() +" skrev: " + enLinje);
                String oppgave[]=enLinje.split(" ");

                switch (oppgave[1]){
                    case "+":
                        skriveren.println(Double.parseDouble(oppgave[0])+Double.parseDouble(oppgave[2]));
                        break;

                    case "-":
                        skriveren.println(Double.parseDouble(oppgave[0])-Double.parseDouble(oppgave[2]));

                        break;

                    case "*":
                        skriveren.println(Double.parseDouble(oppgave[0])*Double.parseDouble(oppgave[2]));
                        break;

                    case "/":
                        skriveren.println(Double.parseDouble(oppgave[0])/Double.parseDouble(oppgave[2]));
                        break;

                }
                enLinje = leseren.readLine();
            }
            /* Lukker forbindelsen */
            System.out.println("Klienten "+this.getId()+" har lokket forbindelsen");
            leseren.close();
            skriveren.close();
            forbindelse.close();
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getClass());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace()[0].getLineNumber());

        }

    }
}