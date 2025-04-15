package testingWeb.models.login;

import static testingWeb.services.utils.TheJsonModels.getWebElementFromJSON;

import testingWeb.models.TheWebElement;

//* @author Bryan Lucero

public class Principal  {

 
    private TheWebElement usuario;
    private TheWebElement contrasena;
    private TheWebElement iniciarSesion;

    public Principal(){
    
        this.usuario = getWebElementFromJSON("usuario");
        this.contrasena = getWebElementFromJSON("contrasena");
        this.iniciarSesion = getWebElementFromJSON("iniciarSesion");
    }

   

    public TheWebElement getUsuario() {
        return this.usuario;
    }

    public TheWebElement getContrasena() {
        return this.contrasena;
    }

    public TheWebElement getIniciarSesion() {
        return this.iniciarSesion;
    }
}
