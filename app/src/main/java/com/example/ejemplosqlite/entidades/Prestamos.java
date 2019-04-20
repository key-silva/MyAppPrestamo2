package com.example.ejemplosqlite.entidades;

public class Prestamos {
    private String Nombre_completo;
    private String Monto;
    private String Interes;
    private String Plazo;
    private String Fechainicio;
    private String Fechafinal;
    private String MontoPagar;
    private String MontoPlazo;

    public String getNombre_completo() {
        return Nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        Nombre_completo = nombre_completo;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String monto) {
        Monto = monto;
    }

    public String getInteres() {
        return Interes;
    }

    public void setInteres(String interes) {
        Interes = interes;
    }

    public String getPlazo() {
        return Plazo;
    }

    public void setPlazo(String plazo) {
        Plazo = plazo;
    }

    public String getFechainicio() {
        return Fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        Fechainicio = fechainicio;
    }

    public String getFechafinal() {
        return Fechafinal;
    }

    public void setFechafinal(String fechafinal) {
        Fechafinal = fechafinal;
    }

    public String getMontoPagar() {
        return MontoPagar;
    }

    public void setMontoPagar(String montoPagar) {
        MontoPagar = montoPagar;
    }

    public String getMontoPlazo() {
        return MontoPlazo;
    }

    public void setMontoPlazo(String montoPlazo) {
        MontoPlazo = montoPlazo;
    }
}
