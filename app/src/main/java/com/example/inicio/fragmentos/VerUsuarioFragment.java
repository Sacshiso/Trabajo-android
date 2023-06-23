package com.example.inicio.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.inicio.Conexion.Conexion;
import com.example.inicio.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerUsuarioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    Conexion conexion;
    EditText Pid,Pnombre,Papellido,PDNI,Pdireccion,Pcargo,Psalario,Pcorreo,Ptelefono;
    Button btningresar;
    ImageButton beditar,beliminar;

    public VerUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VerUsuarioFragment newInstance(String param1, String param2) {
        VerUsuarioFragment fragment = new VerUsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_ver_usuario, container, false);
        Pid = vista.findViewById(R.id.CampoId);
        Pnombre = vista.findViewById(R.id.Enombre);
        Papellido = vista.findViewById(R.id.Eapellido);
        PDNI = vista.findViewById(R.id.EDNI);
        Pdireccion = vista.findViewById(R.id.Edireccion);
        Pcargo = vista.findViewById(R.id.Ecargo);
        Psalario = vista.findViewById(R.id.Esalario);
        Pcorreo = vista.findViewById(R.id.Ecorreo);
        Ptelefono = vista.findViewById(R.id.Etelefono);
        btningresar=vista.findViewById(R.id.btningresar);
        beditar=vista.findViewById(R.id.btnEditar);
        beliminar=vista.findViewById(R.id.btnEliminar);
        conexion = new Conexion();
        eventos();
        return vista;
    }

    private void eventos() {
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID=Pid.getText().toString();
                if (!ID.isEmpty()){
                    int idEmpleado = Integer.parseInt(ID);
                    buscarempleado(idEmpleado);
                }
                else {
                    Toast.makeText(getContext(),"Ingrese un ID valido",Toast.LENGTH_SHORT).show();
                    Pnombre.setText("");
                    Papellido.setText("");
                    PDNI.setText("");
                    Pdireccion.setText("");
                    Pcargo.setText("");
                    Psalario.setText("");
                    Pcorreo.setText("");
                    Ptelefono.setText("");
                }
            }
        });
        beditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre=Pnombre.getText().toString();
                String  apelllido=Papellido.getText().toString();
                String dni=PDNI.getText().toString();
                String direccion=Pdireccion.getText().toString();
                String cargo=Pcargo.getText().toString();
                int salario=Integer.parseInt(Psalario.getText().toString());
                String correo=Pcorreo.getText().toString();
                String telefono=Ptelefono.getText().toString();
                actualizarEmpleado(nombre,apelllido, dni, direccion, cargo, telefono, correo, salario);
            }
        });
        beliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=Pid.getText().toString();
                eliminarEmpleado(id);
            }
        });
    }

    private void eliminarEmpleado(String id) {
        Pid.setText("");
        int idEmpleado = Integer.parseInt(id);
        conexion.elminiarEmpleado(idEmpleado);
        Toast.makeText(getContext(),"Empleado Eliminado",Toast.LENGTH_SHORT).show();
    }

    private void actualizarEmpleado(String nombre, String apelllido, String dni, String direccion, String cargo, String telefono, String correo, int salario) {
        Pnombre.setText(nombre);
        Papellido.setText(apelllido);
        PDNI.setText(dni);
        Pdireccion.setText(direccion);
        Pcargo.setText(cargo);
        Psalario.setText(String.valueOf(salario));
        Pcorreo.setText(correo);
        Ptelefono.setText(telefono);
        Conexion conexion = new Conexion();
        conexion.editarEmpleado(nombre, apelllido, dni, direccion, cargo, telefono, correo, salario);
        Toast.makeText(getContext(),"Los datos han sido actualizados",Toast.LENGTH_SHORT).show();
    }

    private void buscarempleado(int idEmpleado) {
        conexion.buscarPersona(idEmpleado, new Conexion.PersonaCallback() {
            @Override
            public void personaEncontrada(int idEmpleado, int dni, String nombre, String apellido, String direccion, String telefono, String correo, String cargo, int salario) {
                Pnombre.setText(nombre);
                Papellido.setText(apellido);
                PDNI.setText(String.valueOf(dni));
                Pdireccion.setText(direccion);
                Pcargo.setText(cargo);
                Psalario.setText(String.valueOf(salario));
                Pcorreo.setText(correo);
                Ptelefono.setText(telefono);
            }

            @Override
            public void personaNoEncontrada(int idEmpleado) {
                Pnombre.setText("");
                Papellido.setText("");
                PDNI.setText("");
                Pdireccion.setText("");
                Pcargo.setText("");
                Psalario.setText("");
                Pcorreo.setText("");
                Ptelefono.setText("");
                Toast.makeText(getContext(), "Empleado no encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}