package Interfaz;

public interface PasarelaPago {
    boolean realizarPago(String numeroTarjeta, String nombreTarjeta, double monto, String numeroCuenta, String numeroTransaccion);
    void bloquearCupo(String numeroTarjeta, double monto);
    boolean validarTarjeta(String numeroTarjeta, String nombreTarjeta, String cvv);
    void cargarConfiguracion(String archivoConfiguracion);
    void registrarTransaccion(String mensaje);
}
