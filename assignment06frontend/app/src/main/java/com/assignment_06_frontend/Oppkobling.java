package com.assignment_06_frontend;
import android.app.Activity;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Oppkobling extends Thread {
    private Activity activity;
    private static String URL;
    private static int PORT;
    private String res="";
    BufferedReader leseren;
    PrintWriter skriveren;
    Socket forbindelse;
    Boolean running=true;
    String req="close";
    public Oppkobling(String url,int port){
        URL=url;
        PORT=port;
    }
    //tråd
    public void run() {
        Log.i(this.getClass().getSimpleName(), "Thread is running");
        /* Setter opp forbindelsen til tjenerprogrammet */
        try {
            forbindelse = new Socket(URL,PORT);
            Log.i("Oppkobling:","Nå er forbindelsen opprettet.");
            InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
            leseren = new BufferedReader(new InputStreamReader(forbindelse.getInputStream()));
            skriveren = new PrintWriter(forbindelse.getOutputStream(), true);
            /* Leser innledning fra tjeneren og skriver den til kommandovinduet */

        }catch (Exception e){
            Log.i(this.getClass().getSimpleName()+" Oppkobling ",e.toString());
        }

        synchronized (this){
            while (running){
                try {
                    this.wait(); // pauser tråden
                    Log.i(this.getClass().getSimpleName(),"starter tråd igjen");
                    if(forbindelse.isConnected()){
                        /* Åpner en forbindelse for kommunikasjon med tjenerprogrammet */
                        /* Leser tekst fra kommandovinduet (brukeren) */
                        skriveren.println(req);  // sender teksten til tjeneren
                        if(!req.equals("close")){
                            res = leseren.readLine();  // mottar respons fra tjeneren
                            Log.i("Oppkobling:","Respons fra kalkulator: " + res);
                        }
                        req = "close";
                    }else{
                        Log.i("Oppkobling: ", "Får ikke kontakt med server");
                    }
                }catch (Exception e){
                    Log.i("Exception", e.toString());
                }

            }
        }
        Log.i(this.getClass().getSimpleName(), "Tråden er ferdig");
    }

    public String getRes(){
        Log.i(this.getClass().getSimpleName(),"Henter res");
        return res;
    }

    public synchronized void sendReq(String req){
        //setter requesten og starter tråden igjen.
        this.req=req;
        this.notify();
    }

    public synchronized void close(){
        /* Lukker forbindelsen */
        Log.i(this.getClass().getSimpleName(),"Lokker oppkoblingen");
        req="close";
        running=false;
        notify();
        try {
            leseren.close();
            skriveren.close();
            forbindelse.close();
        }catch (Exception e){
            Log.i(this.getClass().getSimpleName(),e.toString());
        }
    }

}

