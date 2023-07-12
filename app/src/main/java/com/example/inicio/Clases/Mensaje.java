package com.example.inicio.Clases;

public class Mensaje {
    private int idEmpleado;
    private String mensaje;

    public Mensaje(int idEmpleado, String mensaje) {
        this.idEmpleado = idEmpleado;
        this.mensaje = mensaje;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
