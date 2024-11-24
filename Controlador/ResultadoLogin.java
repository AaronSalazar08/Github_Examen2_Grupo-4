package Controlador;
public class ResultadoLogin {
    private final boolean exitoso;
    private final String mensaje;

    public ResultadoLogin(boolean exitoso, String mensaje) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
    }

    public boolean esExitoso() {
        return exitoso;
    }

    public String obtenerMensaje() {
        return mensaje;
    }
}
