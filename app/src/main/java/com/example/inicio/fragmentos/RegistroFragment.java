package com.example.inicio.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.inicio.Conexion.Conexion;
import com.example.inicio.R;
import com.example.inicio.interfaces.IComunicaLogin;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Conexion conexion;

    View vista;

    Activity actividad;

    EditText reusuario;
    EditText recontraseña;
    Button registrar;

    TextView registrado;

    IComunicaLogin iComunicaLogin;
    public RegistroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
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
        conexion = new Conexion();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_registro, container, false);
        reusuario=vista.findViewById(R.id.txtUsuario);
        recontraseña=vista.findViewById(R.id.txtPassword);
        //cambio del fragment a la pagina de login
        registrado=vista.findViewById(R.id.registrado);
        registrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iComunicaLogin.Login();
            }
        });
        //registro del usuario hacie el sql
        registrar=vista.findViewById(R.id.btnRegistrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = reusuario.getText().toString();
                String contrasena = recontraseña.getText().toString();
                if (usuario.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(getContext(), "Ambos campos deben estar llenos", Toast.LENGTH_SHORT).show();
                    return; // Salir del método sin registrar el usuario
                }
                if (conexion.existeUsuario(usuario)) {
                    Toast.makeText(getContext(), "Este usuario ya existe", Toast.LENGTH_SHORT).show();
                    return;
                }

                conexion.agregarUsuario(usuario, contrasena);
                Toast.makeText(getContext(), "Usuario agregado", Toast.LENGTH_SHORT).show();
                reusuario.setText("");
                recontraseña.setText("");
            }
        });
        return vista;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof Activity){
            actividad= (Activity) context;
            iComunicaLogin= (IComunicaLogin) actividad;
        }
    }

}