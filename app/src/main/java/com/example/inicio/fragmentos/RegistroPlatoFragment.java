package com.example.inicio.fragmentos;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inicio.Conexion.Conexion;
import com.example.inicio.R;

import java.math.BigDecimal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroPlatoFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class RegistroPlatoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    EditText campoN,campoD,campoP,campoC,campoI,campoA;
    Button btnRegistrar;
    Conexion conexion;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroPlatoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroPlatoFragment newInstance(String param1, String param2) {
        RegistroPlatoFragment fragment = new RegistroPlatoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RegistroPlatoFragment() {
        // Required empty public constructor
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
        vista=inflater.inflate(R.layout.fragment_registro_plato, container, false);
        campoN = vista.findViewById(R.id.campoN);
        campoD = vista.findViewById(R.id.campoD);
        campoP = vista.findViewById(R.id.campoP);
        campoC = vista.findViewById(R.id.campoC);
        campoI = vista.findViewById(R.id.campoI);
        campoA = vista.findViewById(R.id.campoA);
        btnRegistrar = vista.findViewById(R.id.btnRegistrar);
        eventos();
        return vista;
    }

    private void eventos() {
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = campoN.getText().toString();
                String descripcion = campoD.getText().toString();
                String precioStr = campoP.getText().toString();
                String ingredientes = campoI.getText().toString();
                String adicional = campoA.getText().toString();

                if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(descripcion) ||
                        TextUtils.isEmpty(precioStr)|| TextUtils.isEmpty(ingredientes)) {
                    Toast.makeText(getContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                    return;
                }

                BigDecimal precio = new BigDecimal(precioStr);

                conexion = new Conexion();
                conexion.agregarPlato(nombre, descripcion, precio, ingredientes,adicional);

                Toast.makeText(getContext(), "Plato agregado correctamente", Toast.LENGTH_SHORT).show();

                // Limpiar los campos de entrada después de agregar el plato
                campoN.setText("");
                campoD.setText("");
                campoP.setText("");
                campoC.setText("");
                campoI.setText("");
                campoA.setText("");
            }
        });
    }
}