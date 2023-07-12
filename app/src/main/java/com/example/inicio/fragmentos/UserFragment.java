package com.example.inicio.fragmentos;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.inicio.Clases.Mensaje;
import com.example.inicio.Conexion.Conexion;
import com.example.inicio.R;
import com.example.inicio.interfaces.IComunicaLogin;
import com.example.inicio.interfaces.IComunicaMenu;

import java.util.List;

public class UserFragment extends Fragment {

    private CardView mensaje;
    private View vista;
    private Conexion conexion;
    Activity actividad;
    IComunicaMenu iComunicaMenu;

    public UserFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_user, container, false);

        mensaje = vista.findViewById(R.id.cardVerMensaje);

        mensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               iComunicaMenu.Mensaje();
            }
        });
        return vista;
    }
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof Activity){
            actividad= (Activity) context;
            iComunicaMenu= (IComunicaMenu) actividad;
        }
    }
}
