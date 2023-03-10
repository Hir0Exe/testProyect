/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author miguelropero
 */
public class Partido {
    
    private String grupo;
    
    private String equipoLocal;
    private String equipoVisitante;
    
    private String continenteLocal;
    private String continenteVisitante;
    
    private int golesLocal;
    private int golesVisitante;

    public Partido() {}

    public Partido(String grupo, String equipoLocal, String equipoVisitante, String continenteLocal, String continenteVisitante, int golesLocal, int golesVisitante) {
        this.grupo = grupo;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.continenteLocal = continenteLocal;
        this.continenteVisitante = continenteVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public String getContinenteLocal() {
        return continenteLocal;
    }

    public void setContinenteLocal(String continenteLocal) {
        this.continenteLocal = continenteLocal;
    }

    public String getContinenteVisitante() {
        return continenteVisitante;
    }

    public void setContinenteVisitante(String continenteVisitante) {
        this.continenteVisitante = continenteVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    
    
    @Override
    public String toString() {
        String cadena = "";
        cadena += this.getGrupo() + " " + this.equipoLocal + " " + this.equipoVisitante;
        
        return cadena;
    }
}
