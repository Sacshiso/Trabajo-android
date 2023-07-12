package com.example.inicio.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inicio.Conexion.Conexion;
import com.example.inicio.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SolicitudesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SolicitudesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    EditText CId,CNombre,CApellido,cMensaje;
    Button btnBuscar,btnEnviar;
    Conexion conexion;

    public SolicitudesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SolicitudesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SolicitudesFragment newInstance(String param1, String param2) {
        SolicitudesFragment fragment = new SolicitudesFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        conexion = new Conexion();
        vista=inflater.inflate(R.layout.fragment_solicitudes, container, false);
        CId=vista.findViewById(R.id.CampoId);
        CNombre=vista.findViewById(R.id.campoNombre);
        CApellido=vista.findViewById(R.id.campoApellido);
        cMensaje=vista.findViewById(R.id.mensaje);
        btnBuscar=vista.findViewById(R.id.btnBuscar);
        btnEnviar=vista.findViewById(R.id.btnEnviar);
        eventos();
        return vista;
    }

    private void eventos() {
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID=CId.getText().toString();
                if (!ID.isEmpty()){
                    int idEmpleado = Integer.parseInt(ID);
                    buscarEmpleado(idEmpleado);
                }
                else {
                    Toast.makeText(getContext(),"Ingrese un ID valido",Toast.LENGTH_SHORT).show();
                    CNombre.setText("");
                    CApellido.setText("");
                }
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idEmpleado = CId.getText().toString();
                String nombre = CNombre.getText().toString();
                String apellido = CApellido.getText().toString();
                String mensaje = cMensaje.getText().toString();

                if (!idEmpleado.isEmpty()) {
                    int id = Integer.parseInt(idEmpleado);
                    EnviarMensaje(nombre, apellido, mensaje, id);
                    Toast.makeText(getContext(),"Mensaje enviado",Toast.LENGTH_SHORT).show();
                    CId.setText("");
                    CNombre.setText("");
                    CApellido.setText("");
                }
            }
        });

    }
    private void buscarEmpleado(int idEmpleado){
        conexion.buscarEmpleado(idEmpleado, new Conexion.EmpleadoCallback() {
            @Override
            public void onEmpleadoEncontrado(int idEmpleado, String nombre, String apellido) {
                CNombre.setText(nombre);
                CApellido.setText(apellido);
            }

            @Override
            public void onEmpleadoNoEncontrado(int idEmpleado) {
                CId.setText("");
                CNombre.setText("");
                CApellido.setText("");
                Toast.makeText(getContext(), "Empleado no encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void EnviarMensaje(String nombre, String apellido, String mensaje, int idEmpleado) {
        conexion.enviarMensaje(nombre, apellido, mensaje, idEmpleado);
    }

}