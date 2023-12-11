package aplicacion_cliente;

import java.io.*;

public class ClienteUtils {

    public static void guardarClienteEnCSV(String[] datosCliente) {
        File archivo = new File("data/Clientes.csv");
        try (FileWriter fw = new FileWriter(archivo, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            for(int i = 0; i < datosCliente.length; i++) {
                out.print(datosCliente[i]);
                if(i < datosCliente.length - 1) {
                    out.print(",");
                }
            }
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean autenticarUsuario(String login, String password) {
        String archivoClientes = "data/Clientes.csv";
        String line;
        String[] data;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoClientes))) {
            while ((line = br.readLine()) != null) {
                data = line.split(",");
                // Asegúrate de que las columnas coincidan con las posiciones correctas de login y password
                if (data[5].equals(login) && data[6].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public static void guardarReservaEnCSV(String[] datosReserva) {
        File archivo = new File("data/Reservas.csv");
        try (FileWriter fw = new FileWriter(archivo, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            for(int i = 0; i < datosReserva.length; i++) {
                out.print(datosReserva[i]);
                if(i < datosReserva.length - 1) {
                    out.print(",");
                }
            }
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
