package com.example.inicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.inicio.fragmentos.LoginFragment;
import com.example.inicio.fragmentos.MenuFragment;
import com.example.inicio.fragmentos.PagoFragment;
import com.example.inicio.fragmentos.PersonalFragment;
import com.example.inicio.fragmentos.PlatosFragment;
import com.example.inicio.fragmentos.QRFragment;
import com.example.inicio.fragmentos.RegistroFragment;
import com.example.inicio.fragmentos.RegistroPlatoFragment;
import com.example.inicio.fragmentos.RegistroUsuarioFragment;
import com.example.inicio.fragmentos.SolicitudesFragment;
import com.example.inicio.fragmentos.VerPlatosFragment;
import com.example.inicio.fragmentos.VerUsuarioFragment;
import com.example.inicio.interfaces.IComunicaLogin;
import com.example.inicio.interfaces.IComunicaMenu;
import com.example.inicio.interfaces.IComunicaPersonal;
import com.example.inicio.interfaces.IComunicaPlatos;

public class MainActivity extends AppCompatActivity implements IComunicaPersonal, IComunicaPlatos, IComunicaMenu, IComunicaLogin {

    //Inicializamos los Fragments que se utilizaran para el llamado
    //Separamos los Fragments que seran usado para la gestion de platos y personal

    //Fragment Personal
    Fragment fragmentPersonal,registroUsuarioFragment,verUsuarioFragment,fragmentSolicitudes,fragmentPago;

    //Fragment Platos
    Fragment fragmentPlatos,registroPlatosFragment,verPlatosFragment,qrfragment;

    //Fragment Login y Menu
    Fragment fragmentLogin,fragmentMenu,fragmentRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Area de login y menu
        fragmentLogin= new LoginFragment();
        fragmentMenu= new MenuFragment();
        fragmentRegistro= new RegistroFragment();

        //Area de Personal
        fragmentPersonal = new PersonalFragment();
        registroUsuarioFragment = new RegistroUsuarioFragment();
        verUsuarioFragment= new VerUsuarioFragment();
        fragmentSolicitudes = new SolicitudesFragment();
        fragmentPago = new PagoFragment();

        //Area de los Platos
        fragmentPlatos= new PlatosFragment();
        registroPlatosFragment= new RegistroPlatoFragment();
        verPlatosFragment= new VerPlatosFragment();
        qrfragment = new QRFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentLogin).commit();
    }

    //Se hace el llamado para reemplazar el fragment

    //Area del Personal
    @Override
    public void agregarPersonal() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,registroUsuarioFragment).commit();
    }

    @Override
    public void verPersonal() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,verUsuarioFragment).commit();
    }

    @Override
    public void rectrocesoMenu() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentMenu).commit();
    }

    @Override
    public void irPlatos() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentPlatos  ).commit();
    }
    public void solicitudesPersonal() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentSolicitudes).commit();
    }
    @Override
    public void pagoPersonal() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentPago).commit();
    }

    //Fin del Area de Personal

    //Area de los Platos

    @Override
    public void agregarPlato() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,registroPlatosFragment).commit();
    }

    @Override
    public void verPlato() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,verPlatosFragment).commit();
    }

    @Override
    public void retrocesoM() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentMenu).commit();
    }

    @Override
    public void irPersonal() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentPersonal).commit();
    }

    @Override
    public void verMenu() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,qrfragment).commit();
    }
    //Fin de Area de Platos

    //Area de Llamados

    @Override
    public void Personal() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentPersonal).commit();
    }

    @Override
    public void Platos() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentPlatos).commit();
    }

    @Override
    public void Acceder() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentMenu).commit();
    }

    @Override
    public void Registro() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentRegistro).commit();
    }

    @Override
    public void Login() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorfragments,fragmentLogin).commit();
    }
    //Fin del Area de Llamados
}