package testingWeb.models.login;

public class ConfigBrowser {
    private int numero;
    private Boolean probar;
    private String usuario;
    private String contrasenia;
    private String enlace;
    
    public ConfigBrowser(String numero, String probar, String usuario, String contrasenia, String enlace){

        try {
            this.numero = (int)Double.parseDouble(numero);
            this.probar = Boolean.parseBoolean(probar);
            this.usuario = usuario;
            this.contrasenia = contrasenia;
            this.enlace = enlace;
            
        } catch (Exception e) {
            this.numero = 0;
            this.probar = false;
            this.usuario = "";
            this.contrasenia = "";
            this.enlace = "";
        }
    }

    public int getNumero() {
        return this.numero;
    }

    public boolean getProbar() {
        return this.probar;
    }

   
    public String getUsuario() {
        return this.usuario;
    }

    public String getContrasena() {
        return this.contrasenia;
    }

    public String getEnlace() {
        return this.enlace;
    }

}
