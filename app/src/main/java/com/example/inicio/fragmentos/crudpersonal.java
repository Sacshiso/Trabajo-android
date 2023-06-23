package com.example.inicio.fragmentos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.inicio.MainActivity;
import com.example.inicio.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class crudpersonal extends MainActivity {
    EditText campoNombre,campoApellido,campoDNI,campoDireccion,campoCargo,campoSalario,campoCorreo,campoTelefono;
    Button btnBuscar1,btnLimpiar;
    ImageButton btnEditar,btnEliminar,btnGuardar;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.crud_personal);

        campoNombre = (EditText) findViewById(R.id.camponombre);
        campoApellido = (EditText) findViewById(R.id.campoApellido);
        campoDNI = (EditText) findViewById(R.id.campoDNI);
        campoDireccion = (EditText) findViewById(R.id.campoDireccion);
        campoCargo = (EditText) findViewById(R.id.campoCargo);
        campoSalario = (EditText) findViewById(R.id.campoSalario);
        campoCorreo = (EditText) findViewById(R.id.campoCorreo);
        campoTelefono = (EditText) findViewById(R.id.campoTelefono);

        String nombre = campoNombre.getText().toString();
        String apellido = campoApellido.getText().toString();
        String dni = campoDNI.getText().toString();
        String cargo = campoCargo.getText().toString();
        String direccion = campoDireccion.getText().toString();
        String telefono = campoTelefono.getText().toString();
        String correo = campoCorreo.getText().toString();
        String salario = campoSalario.getText().toString();


        btnBuscar1 = (Button) findViewById(R.id.btnBuscar1);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnEditar = (ImageButton) findViewById(R.id.btnEditar);
        btnEliminar = (ImageButton) findViewById(R.id.btnEliminar);
        btnGuardar = (ImageButton) findViewById(R.id.btnGuardar);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = null;
                try {
                    if (connection != null) {
                        String query = "INSERT INTO Empleado (NombreEm, ApellidoEm, DNIEm, cargo, DirecEm, TeleEm, CorreoEm, Salario) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
                        PreparedStatement statement = connection.prepareStatement(query);
                        double salarioo = Double.parseDouble(salario);
                        statement.setString(1, nombre);
                        statement.setString(2, apellido);
                        statement.setString(3, dni);
                        statement.setString(4, cargo);
                        statement.setString(5, direccion);
                        statement.setString(6, telefono);
                        statement.setString(7, correo);
                        statement.setInt(8, (int) salarioo);
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

        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = null;
                try {
                    if (connection != null) {
                        String query = "UPDATE Empleado SET NombreEm = ?, ApellidoEm = ?, DNIEm = ?, cargo = ?, DirecEm = ?, TeleEm = ?, CorreoEm = ? WHERE Salario= ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        double salarioo = Double.parseDouble(salario);
                        statement.setString(1, nombre);
                        statement.setString(2, apellido);
                        statement.setString(3, dni);
                        statement.setString(4, direccion);
                        statement.setString(5, cargo);
                        statement.setString(6, telefono);
                        statement.setString(7, correo);
                        statement.setInt(8, (int) salarioo);
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
        });
    }
}