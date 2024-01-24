package app.modelo.meusclientes.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import app.modelo.meusclientes.R;
import app.modelo.meusclientes.controller.ClienteController;

// TODO - Criar o novo Layout para suporte aos CARDS

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener/* INJETA O MENU */ {

    FragmentManager fragmentManager;/* Implementa outras interfases/telas com ele */

    NavigationView navigationView;/* E responsabilizado por fazer uma ação depois de clicar em um botão em uma tela sem ser a principal */

    Menu menu; /* Trata do gerenciamento dos botões de gerenciamento de tela (EX: os tres pontinhos) */
    MenuItem nav_preto; /* A opição da tela no XML (não sendo a principal) */
    MenuItem nav_vermelho; /* A opição da tela no XML (não sendo a principal) */
    MenuItem nav_azul; /* A opição da tela no XML (não sendo a principal) */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =   findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Action Button Clicado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_fragment, new ModeloVermelhoFragment()).commit();

        ClienteController clienteController = new ClienteController(getBaseContext());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // TODO: opter ID para a opção selecionada no MENU DRAWER
        if (id == R.id.nav_preto) {

            menu = navigationView.getMenu();

            nav_preto = menu.findItem(R.id.nav_preto);
            nav_preto.setTitle("Preto Selecionado");

            nav_vermelho = menu.findItem(R.id.nav_vermelho);
            nav_vermelho.setTitle("Vermelho");

            nav_azul = menu.findItem(R.id.nav_azul);
            nav_azul.setTitle("Azul");

            navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ModeloPretoFragment()).commit();

        } else if (id == R.id.nav_vermelho) {

            menu = navigationView.getMenu();

            nav_preto = menu.findItem(R.id.nav_preto);

            nav_preto.setTitle("Preto");

            nav_vermelho = menu.findItem(R.id.nav_vermelho);
            nav_vermelho.setTitle("Vermelho Selecionado");

            nav_azul = menu.findItem(R.id.nav_azul);
            nav_azul.setTitle("Azul");

            // TODO: Mudar a cor de todos os itens do menu programaticamente
            navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ModeloVermelhoFragment()).commit();

        } else if (id == R.id.nav_azul) {

            menu = navigationView.getMenu();

            nav_preto = menu.findItem(R.id.nav_preto);
            nav_preto.setTitle("Preto");

            nav_vermelho = menu.findItem(R.id.nav_vermelho);
            nav_vermelho.setTitle("Vermelho");

            nav_azul = menu.findItem(R.id.nav_azul);
            nav_azul.setTitle("Azul Selecionado");


            navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));

            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ModeloAzulFragment()).commit();

        }else if(id == R.id.nav_adicionar_cliente){
            /*Modifica o título*/
            setTitle("Novo Cliente");

            /* Comando para troca de tela */
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new AficionarClienteFragment()).commit();
        } else if (id == R.id.nav_Listar_cliente) {
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ListarClientesFragment()).commit();

        }else if (id == R.id.nav_Listar_cliente_cards){
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new ListarClientesCardsFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
