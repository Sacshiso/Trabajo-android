package com.example.inicio.Conexion;

import android.os.StrictMode;
import android.util.Log;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexion {
    public static Connection connectionclass(){
        Connection con = null;
        String ip = "192.168.57.16",port="1433",username="root",password="1234",databasename="Birka";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl ="jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+databasename+";User="+username+";password="+password+";";
            con= DriverManager.getConnection(connectionUrl);
        }catch (Exception e){
            Log.e("Conexion", "Error al conectar a la base de datos: " + e.getMessage());
        }
        return con;
    }
    public void agregarUsuario(String usuario, String contrasena) {
        Connection connection = null;
        try {
            connection = connectionclass();
            if (connection != null) {
                String query = "INSERT INTO usuario (Usuario, Contraseña) VALUES (?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, usuario);
                statement.setString(2, contrasena);
                statement.executeUpdate();
                Log.d("Conexion", "Usuario agregado correctamente");
            } else {
                Log.e("Conexion", "No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            Log.e("Conexion", "Error al agregar usuario: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("Conexion", "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
    public static String obtenerCargo(String usuario) {
        String cargo = null;
        Connection con = connectionclass();
        if (con == null) {
            return null;
        }
        String consulta = "SELECT Cargo FROM usuario WHERE Usuario = ?";

        try {
            PreparedStatement statement = con.prepareStatement(consulta);
            statement.setString(1, usuario);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                cargo = rs.getString("Cargo");
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cargo;
    }
    public boolean existeUsuario(String nombreUsuario) {
        Connection connection = null;
        try {
            connection = connectionclass();
            if (connection != null) {
                String query = "SELECT COUNT(*) FROM usuario WHERE Usuario = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, nombreUsuario);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            } else {
                Log.e("Conexion", "No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            Log.e("Conexion", "Error al verificar la existencia del usuario: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("Conexion", "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }
    public boolean validarUsuario(String usuario, String contrasena) {
        Connection connection = null;
        try {
            connection = connectionclass();
            if (connection != null) {
                String query = "SELECT * FROM usuario WHERE Usuario = ? AND Contraseña = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, usuario);
                statement.setString(2, contrasena);
                ResultSet resultSet = statement.executeQuery();

                // Verificar si se encontró un usuario con el nombre y contraseña proporcionados
                if (resultSet.next()) {
                    // El usuario es válido
                    Log.d("Conexion", "Usuario válido");
                    return true;
                } else {
                    // El usuario no es válido
                    Log.d("Conexion", "Usuario inválido");
                    return false;
                }
            } else {
                Log.e("Conexion", "No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            Log.e("Conexion", "Error al validar usuario: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("Conexion", "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }

        // Si ocurre alguna excepción o no se puede establecer la conexión, asumimos que el usuario no es válido
        return false;
    }
    public void agregarEmpleado(String nombre, String apellido, String dni, String direccion,String cargo, String telefono, String correo, int salario) {
        Connection connection = null;
        try {
            connection = connectionclass();
            if (connection != null) {
                String query = "INSERT INTO Empleado (NombreEm, ApellidoEm, DNIEm, cargo, DirecEm, TeleEm, CorreoEm, Salario) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, dni);
                statement.setString(4, direccion);
                statement.setString(5, cargo);
                statement.setString(6, telefono);
                statement.setString(7, correo);
                statement.setInt(8, salario);
                statement.executeUpdate();
                Log.d("Conexion", "Empleado agregado correctamente");
            } else {
                Log.e("Conexion", "No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            Log.e("Conexion", "Error al agregar empleado: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("Conexion", "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public interface EmpleadoCallback {
        void onEmpleadoEncontrado(int idEmpleado, String nombre, String apellido);
        void onEmpleadoNoEncontrado(int idEmpleado);
    }

    public void buscarEmpleado(int idEmpleado, EmpleadoCallback callback) {
        Connection connection = null;
        try {
            connection = connectionclass();
            if (connection != null) {
                String query = "SELECT * FROM Empleado WHERE IdEmpleado = ? AND Estado = 0";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, idEmpleado);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("IdEmpleado");
                    String nombre = resultSet.getString("NombreEm");
                    String apellido = resultSet.getString("ApellidoEm");

                    callback.onEmpleadoEncontrado(id, nombre, apellido);
                } else {
                    callback.onEmpleadoNoEncontrado(idEmpleado);
                }
            } else {
                Log.e("Conexion", "No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            Log.e("Conexion", "Error al buscar empleado: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("Conexion", "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
    public interface PersonaCallback {
        void personaEncontrada(int idEmpleado, int dni, String nombre, String apellido, String direccion, String telefono, String correo, String cargo, int salario);
        void personaNoEncontrada(int idEmpleado);
    }

    public void buscarPersona(int idEmpleado, PersonaCallback callback) {
        Connection connection = null;
        try {
            connection = connectionclass();
            if (connection != null) {
                String query = "SELECT * FROM Empleado WHERE IdEmpleado = ? AND Estado = 0";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, idEmpleado);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("IdEmpleado");
                    int dni = resultSet.getInt("DNIEm");
                    String nombre = resultSet.getString("NombreEm");
                    String apellido = resultSet.getString("ApellidoEm");
                    String direccion = resultSet.getString("DirecEm");
                    String telefono = resultSet.getString("TeleEm");
                    String correo = resultSet.getString("CorreoEm");
                    String cargo = resultSet.getString("Cargo");
                    int salario = resultSet.getInt("Salario");

                    callback.personaEncontrada(id, dni, nombre, apellido, direccion, telefono, correo, cargo, salario);
                } else {
                    callback.personaNoEncontrada(idEmpleado);
                }
            } else {
                Log.e("Conexion", "No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            Log.e("Conexion", "Error al buscar empleado: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("Conexion", "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
    //
    public void enviarMensaje(String nombre, String apellido, String mensaje) {
        Connection connection = null;
        try {
            connection = connectionclass();
            if (connection != null) {
                String query = "INSERT INTO Mensajes (Nombre, Apellido, Mensaje) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, mensaje);
                statement.executeUpdate();
                Log.i("Conexion", "Mensaje enviado correctamente");
            } else {
                Log.e("Conexion", "No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            Log.e("Conexion", "Error al enviar el mensaje: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("Conexion", "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }


    public void editarEmpleado(String nombre, String apellido, String dni, String direccion, String cargo, String telefono, String correo, int salario) {
        Connection connection = null;
        try {
            connection = connectionclass();
            if (connection != null) {
                String query = "UPDATE Empleado SET NombreEm = ?, ApellidoEm = ?, DNIEm = ?, cargo = ?, DirecEm = ?, TeleEm = ?, CorreoEm = ? WHERE Salario= ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, dni);
                statement.setString(4, direccion);
                statement.setString(5, cargo);
                statement.setString(6, telefono);
                statement.setString(7, correo);
                statement.setInt(8, salario);
                statement.executeUpdate();
                Log.d("Conexion", "Empleado actualizado correctamente");
            } else {
                Log.e("Conexion", "No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            Log.e("Conexion", "Error al editar empleado: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("Conexion", "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
    public void elminiarEmpleado(int idEmpleado){
        Connection connection = null;
        try {
            connection = connectionclass();
            if (connection != null){
                String query = "UPDATE Empleado SET Estado = 1 WHERE IdEmpleado = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1,idEmpleado);
                int actualizacion= statement.executeUpdate();
                if (actualizacion>0){
                    Log.e("Conexion","Empleado eliminado");
                }
            }
        }catch (SQLException e){
            Log.e("Conexion","Error al eliminar empleado: "+ e.getMessage());
        }
    }

    public static void agregarPlato(String nombre, String descripcion, BigDecimal precio, String ingredientes, String adicional) {
        Connection connection = null;
        try {
            connection = connectionclass();
            if (connection != null) {
                String query = "INSERT INTO Plato(NombrePl, Descripcion, Precio, Ingredientes, adicional) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setBigDecimal(3, precio);
                statement.setString(4, ingredientes);
                statement.setString(5, adicional);
                statement.executeUpdate();
                Log.d("Conexion", "Plato agregado correctamente");
            } else {
                Log.e("Conexion", "No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            Log.e("Conexion", "Error al agregar plato: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Log.e("Conexion", "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }


}
