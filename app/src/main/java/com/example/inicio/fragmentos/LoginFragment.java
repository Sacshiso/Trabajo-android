package com.example.inicio.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inicio.Conexion.Conexion;
import com.example.inicio.R;
import com.example.inicio.interfaces.IComunicaLogin;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Conexion conexion;
    EditText iusuario,icontraseña;
    View vista;
    Activity actividad;
    TextView registro;

    Button acceder;
    IComunicaLogin iComunicaLogin;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conexion = new Conexion();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_login, container, false);
        iusuario = vista.findViewById(R.id.txtUsuario);
        icontraseña = vista.findViewById(R.id.txtPassword);
        acceder = vista.findViewById(R.id.btn);
        registro=vista.findViewById(R.id.textregistro);
        eventos();
        return vista;
    }

    private void eventos() {
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iComunicaLogin.Registro();
            }
        });
        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = iusuario.getText().toString();
                String contrasena = icontraseña.getText().toString();
                String cargo=conexion.obtenerCargo(usuario);
                boolean usuariovalido= conexion.validarUsuario(usuario,contrasena);
                if (usuariovalido){
                    Toast.makeText(getContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    int idEmpleado = conexion.obtenerIdEmpleado(usuario,contrasena);
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("idEmpleado", idEmpleado);
                    editor.apply();
                    if (idEmpleado != -1) {
                        Toast.makeText(getContext(), "ID de empleado guardado correctamente: " + idEmpleado, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "No se pudo obtener el ID de empleado", Toast.LENGTH_SHORT).show();
                    }
                    if (cargo!= null){
                        if (cargo.equals("admin")){
                            iComunicaLogin.Admin();
                        } else {
                            iComunicaLogin.User();
                        }
                    }
                }else {
                    Toast.makeText(getContext(), "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
                    iusuario.setText("");
                    icontraseña.setText("");
                }
            }
        });
    }


    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof Activity){
            actividad= (Activity) context;
            iComunicaLogin= (IComunicaLogin) actividad;
        }
    }
}