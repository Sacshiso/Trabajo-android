package com.example.inicio.fragmentos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inicio.Clases.Mensaje;
import com.example.inicio.Conexion.Conexion;
import com.example.inicio.R;

import java.util.ArrayList;
import java.util.List;

public class VerSolicitudesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    TextView solicitudes;
    Button Mensajes;
    View vista;
    Conexion conexion;
    public VerSolicitudesFragment() {
        // Required empty public constructor
    }

    public static VerSolicitudesFragment newInstance(String param1, String param2) {
        VerSolicitudesFragment fragment = new VerSolicitudesFragment();
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
        vista=inflater.inflate(R.layout.fragment_ver_solicitudes, container, false);
        solicitudes=vista.findViewById(R.id.textViewSolicitudes);
        Mensajes=vista.findViewById(R.id.btnMensajes);
        conexion = new Conexion();
        Mensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                int idEmpleado = sharedPreferences.getInt("idEmpleado", -1);

                // Obtener los mensajes correspondientes al idEmpleado desde la tabla de mensajes
                List<Mensaje> mensajes = conexion.obtenerMensajesPorIdEmpleado(idEmpleado);

                // Mostrar los mensajes en el TextView textViewSolicitudes
                mostrarMensajes(solicitudes, mensajes);
                Toast.makeText(getContext(),"Mensajes",Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "ID de empleado obtenido: " + idEmpleado, Toast.LENGTH_SHORT).show();
            }
        });

        return vista;
    }

    private void mostrarMensajes(TextView textView, List<Mensaje> mensajes) {
        StringBuilder sb = new StringBuilder();
        for (Mensaje mensaje : mensajes) {
            int idEmpleado = mensaje.getIdEmpleado();
            String contenido = mensaje.getMensaje();
            sb.append("ID del empleado: ").append(idEmpleado).append("\n");
            sb.append("Contenido del mensaje: ").append(contenido).append("\n\n");
        }
        textView.setText(sb.toString());
    }
}