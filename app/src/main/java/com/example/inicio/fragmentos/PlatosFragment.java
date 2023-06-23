package com.example.inicio.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.inicio.R;
import com.example.inicio.interfaces.IComunicaPlatos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlatosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlatosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    Activity actividad;
    CardView cardAgregar,cardVer,qr;
    ImageView retrocesom,personal;
    IComunicaPlatos iComunicaPlatos;

    public PlatosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlatosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlatosFragment newInstance(String param1, String param2) {
        PlatosFragment fragment = new PlatosFragment();
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
        vista=inflater.inflate(R.layout.fragment_platos, container, false);
        cardAgregar=vista.findViewById(R.id.cardAgregar);
        cardVer=vista.findViewById(R.id.cardVer);
        retrocesom=vista.findViewById(R.id.retrocesom);
        personal=vista.findViewById(R.id.personal);
        qr=vista.findViewById(R.id.cardSolicitudes);
        eventos();
        return vista;
    }

    private void eventos() {
        cardAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iComunicaPlatos.agregarPlato();
            }
        });
        cardVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {iComunicaPlatos.verPlato();
            }
        });
        retrocesom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iComunicaPlatos.retrocesoM();
            }
        });
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iComunicaPlatos.irPersonal();
            }
        });
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iComunicaPlatos.verMenu();
            }
        });
    }
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof Activity){
            actividad= (Activity) context;
            iComunicaPlatos= (IComunicaPlatos) actividad;
        }
    }
}