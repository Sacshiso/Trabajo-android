package com.example.inicio.Clases;

public class Login {
    private String usuario, password;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Login(){}
    public int ValidarCredenciales(){
        if (this.getUsuario().equals("admin") && this.getPassword().equals("1234")){
            return 1;
        }else{
            return 0;
        }
    }
}
