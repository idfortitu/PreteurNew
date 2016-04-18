package com.example.idfortitu.preteurnew;


import java.util.Date;

/**
 * Created by idFortitu on 09/04/2016.
 */
public class Livre {
    private String titre;
    private String Auteur;
    private int preter;
    private String Personne;
    private String Image;
    private String nameimage;


    public void setnimage(String nimage) {
        this.nameimage = nimage;
    }
    public String getnImage() {return nameimage;}

    public void setimage(String image) {
        this.Image = image;
    }
    public String getImage() {return Image;}

    public void setPersonne(String Personne){
        this.Personne = Personne;
    }

    public String getPersonne(){
        return Personne;
    }

    public void setTitre(String titre){
        this.titre = titre;
    }

    public String getTitre(){
        return titre;
    }

    public String getAuteur(){
        return Auteur;
    }
    public void setAuteur(String Auteur){
        this.Auteur = Auteur;
    }

    public void setPreter(int Preter){
        this.preter = Preter;
    }

    public int getPreter(){
        return preter;
    }



}
