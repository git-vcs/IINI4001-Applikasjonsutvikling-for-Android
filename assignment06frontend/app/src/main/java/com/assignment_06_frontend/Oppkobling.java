package com.assignment_06_frontend;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

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
    String req="-1";
    public Oppkobling(String url,int port){
        URL=url;
        PORT=port;
    }


    public void run() {
        Log.i(this.getClass().getSimpleName(), "Thread is running");
        Log.i("Thread", "run: ");
        Log.i("Oppkobling:","Oppgi navnet på maskinen der tjenerprogrammet kjører: ");
        /* Setter opp forbindelsen til tjenerprogrammet */

        try {
            forbindelse = new Socket(URL,PORT);
            Log.i("Oppkobling:","Nå er forbindelsen opprettet.");
            InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
            leseren = new BufferedReader(new InputStreamReader(forbindelse.getInputStream()));
            skriveren = new PrintWriter(forbindelse.getOutputStream(), true);
            /* Leser innledning fra tjeneren og skriver den til kommandovinduet */
            String innledning1 = leseren.readLine();
            String innledning2 = leseren.readLine();
            res = innledning1 + " "+innledning2;
            Log.i("Oppkobling:",innledning1 + " " + innledning2);
            this.wait();//puse til vi har fått en request

        }catch (Exception e){

        }

        synchronized (this){
            while (running){
                Log.i(this.getClass().getSimpleName(),"starter tråd igjen");
                try {
                    if(forbindelse.isConnected()&&!req.equals("-1")){
                        /* Åpner en forbindelse for kommunikasjon med tjenerprogrammet */
                        /* Leser tekst fra kommandovinduet (brukeren) */
                        skriveren.println(req);  // sender teksten til tjeneren
                        res = leseren.readLine();  // mottar respons fra tjeneren
                        Log.i("Oppkobling:","Respons fra kalkulator: " + res);
                        req="-1";
                        this.wait(); // pauser tråden
                    }else{
                        Log.i("Oppkobling: ", "Får ikke kontakt med server");
                        this.wait(); // pauser tråden
                    }
                }catch (Exception e){
                    Log.i("Exception", e.getLocalizedMessage());
                }

            }
        }



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
        sendReq(null);
        running=false;
        notify();

        try {
            this.join();// venter på at thread skal stoppe;
            leseren.close();
            skriveren.close();
            forbindelse.close();
        }catch (Exception e){
            Log.i(this.getClass().getSimpleName(),e.toString());
        }



    }
    }

