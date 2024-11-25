package Controlador;
public class ResultadoLogin {
    private final boolean exitoso;
    private final String mensaje;
    private final String nombreUsuario;

    public ResultadoLogin(boolean exitoso, String mensaje, String nombreUsuario) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
        this.nombreUsuario = nombreUsuario;
    }

    public boolean esExitoso() {
        return exitoso;
    }

    public String obtenerMensaje() {
        return mensaje;
    }

    public String obtenerNombreUsuario() {
        return nombreUsuario;
    }
}
