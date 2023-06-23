package com.example.inicio.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.inicio.Conexion.Conexion;
import com.example.inicio.R;
import com.example.inicio.interfaces.IComunicaPersonal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroUsuarioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;

    EditText eDNI,eNombre,eApellido,eDireccion,eTelefono,eCorreo,eCargo,eSalario,eEstado;
    ImageView retroceso;

    Button agregarempleado;
    Conexion conexion;
    Activity actividad;
    IComunicaPersonal interfaceComunicaPersonal;
    public RegistroUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroUsuarioFragment newInstance(String param1, String param2) {
        RegistroUsuarioFragment fragment = new RegistroUsuarioFragment();
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
        vista = inflater.inflate(R.layout.fragment_registro_usuario, container, false);
        retroceso=vista.findViewById(R.id.retrocesoM);
        eNombre = vista.findViewById(R.id.campoNombre);
        eApellido = vista.findViewById(R.id.campoApellido);
        eDNI = vista.findViewById(R.id.campoDNI);
        eDireccion = vista.findViewById(R.id.campoDireccion);
        eTelefono = vista.findViewById(R.id.campoTelefono);
        eCorreo = vista.findViewById(R.id.campoCorreo);
        eCargo = vista.findViewById(R.id.campoCargo);
        eSalario = vista.findViewById(R.id.campoSalario);
        agregarempleado = vista.findViewById(R.id.btnRegistrar);
        eventos();
        return vista;
    }

    private void eventos() {
        retroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {interfaceComunicaPersonal.rectrocesoMenu();
            }
        });
        agregarempleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = eNombre.getText().toString();
                String apellido = eApellido.getText().toString();
                String dni = eDNI.getText().toString();
                String direccion = eDireccion.getText().toString();
                String cargo = eCargo.getText().toString();
                String telefono = eTelefono.getText().toString();
                String correo = eCorreo.getText().toString();
                String salarioStr = eSalario.getText().toString();
                Conexion conexion = new Conexion();


                if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(apellido) && !TextUtils.isEmpty(dni) && !TextUtils.isEmpty(direccion) && !TextUtils.isEmpty(cargo) && !TextUtils.isEmpty(telefono) && !TextUtils.isEmpty(correo) && !TextUtils.isEmpty(salarioStr)) {
                    double salario = Double.parseDouble(salarioStr);

                    conexion.agregarEmpleado(nombre, apellido, dni, direccion,cargo, telefono, correo, (int) salario);

                    Toast.makeText(getContext(), "Empleado agregado", Toast.LENGTH_SHORT).show();

                    // Limpiar los campos de entrada después de agregar un empleado
                    eNombre.setText("");
                    eApellido.setText("");
                    eDNI.setText("");
                    eDireccion.setText("");
                    eCargo.setText("");
                    eTelefono.setText("");
                    eCorreo.setText("");
                    eSalario.setText("");
                } else {
                    Toast.makeText(getContext(), "Debe de llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof Activity){
            actividad= (Activity) context;
            interfaceComunicaPersonal= (IComunicaPersonal) actividad;
        }
    }
}