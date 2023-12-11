package modelo;

import javax.swing.JOptionPane;
import java.util.Map;

public class Empleado {
    private String nombre;
    private String cargo;

    public Empleado(String nombre, String cargo) {
        this.nombre = nombre;
        this.cargo = cargo;
    }

    public void PrintmenuEmpleado() {
        JOptionPane.showMessageDialog(null,
                "1.Crear nuevo empleado\n2.Buscar empleado\n3.Eliminar empleado\n4.Mostrar todos los empleados\n9.Salir al Menu principal");
    }

    public void NuevoEmpleado(Map<String, String> empleadoMap) {
        String elnombre = input("Nombre del empleado");
        String elcargo = input("Cargo Empleado");
        empleadoMap.put(elnombre, elcargo);
        JOptionPane.showMessageDialog(null, "Se ha guardado exitosamente el empleado. Para buscarlo, use su nombre.");
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void BuscarEmpleado(Map<String, String> empleadoMap) {
        String elnombre = input("Ingrese el nombre del empleado");
        if (empleadoMap.containsKey(elnombre)) {
            JOptionPane.showMessageDialog(null, "Nombre empleado: " + elnombre + "\nCargo empleado: " + empleadoMap.get(elnombre));
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado");
        }
    }

    public void EliminarEmpleado(Map<String, String> empleadoMap) {
        String elnombre = input("Ingrese el nombre del empleado");
        if (empleadoMap.containsKey(elnombre)) {
            empleadoMap.remove(elnombre);
            JOptionPane.showMessageDialog(null, "Empleado eliminado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el empleado que desea eliminar.");
        }
    }
    public void ModificarEmpleado(Map<String, String> empleadoMap, String nombreEmpleado) {
        if (empleadoMap.containsKey(nombreEmpleado)) {
            String nuevoNombre = input("Nuevo nombre del empleado");
            String nuevoCargo = input("Nuevo cargo del empleado");
            empleadoMap.remove(nombreEmpleado);
            empleadoMap.put(nuevoNombre, nuevoCargo);
            JOptionPane.showMessageDialog(null, "Empleado modificado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el empleado que desea modificar.");
        }
    }
    public void ejecutarEmpleado(Map<String, String> empleadoMap) {
        boolean continuar = true;
        while (continuar) {
            PrintmenuEmpleado();
            int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
            if (opcion_seleccionada == 1) {
                NuevoEmpleado(empleadoMap);
            } else if (opcion_seleccionada == 2) {
                BuscarEmpleado(empleadoMap);
            } else if (opcion_seleccionada == 3) {
                EliminarEmpleado(empleadoMap);
            } else if (opcion_seleccionada == 4) {
                showEmpleados(empleadoMap);
            } else if (opcion_seleccionada == 9) {
                continuar = false;
            }
        }
    }

    public void showEmpleados(Map<String, String> empleadoMap) {
        StringBuilder empleados = new StringBuilder("Empleados Actuales:\n");
        for (Map.Entry<String, String> entry : empleadoMap.entrySet()) {
            empleados.append("Nombre Empleado: ").append(entry.getKey()).append("\nCargo Empleado: ").append(entry.getValue()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, empleados.toString());
    }

    public String input(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }
}
