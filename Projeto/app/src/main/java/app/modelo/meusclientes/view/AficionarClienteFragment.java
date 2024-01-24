package app.modelo.meusclientes.view;

import android.app.AlertDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Objects;

import app.modelo.meusclientes.R;
import app.modelo.meusclientes.controller.ClienteController;
import app.modelo.meusclientes.model.Cliente;


public class AficionarClienteFragment extends Fragment {

    View view;
    TextView txtTitulo;

    EditText edtNomeCompleto;
    EditText edtTelefone;
    EditText edtEmail;
    EditText edtCep;
    EditText edtLogradouro;
    EditText edtNumero;
    EditText edtBairro;
    EditText edtCidade;
    EditText edtEstado;
    CheckBox chkTermosdeUso;
    Button btnCancelar;
    Button btnSalvar;

    Cliente novoCliente;
    ClienteController clienteController;

    public AficionarClienteFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        TextView txtTitulo = view.findViewById(R.id.txtTitulo);
//        txtTitulo.setText("Adicionar Cliente");
//        txtTitulo.setTextColor(ColorStateList.valueOf(Color.CYAN));

        view =  inflater.inflate(R.layout.fragment_adicionar_cliente, container, false);

        iniciarComponentesDeLayout();

        btnSalvar.setEnabled(false);
        escutarEventoDosBotoes();
        
        return view;
    }

    private void iniciarComponentesDeLayout() {
    /*
        Inicializa os componentes da tela/layout
        para adicionar os clientes
     */
        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtTitulo.setText("Adicionar Novo Cliente");

        edtNomeCompleto = view.findViewById(R.id.edtNomeCompleto);
        edtEmail = view.findViewById(R.id.edtEmail);

        edtTelefone = view.findViewById(R.id.edtTelefone);
        edtCep = view.findViewById(R.id.edtCep);
        edtLogradouro = view.findViewById(R.id.edtLogradouro);
        edtNumero = view.findViewById(R.id.edtNumero);
        edtBairro = view.findViewById(R.id.edtBairro);
        edtCidade = view.findViewById(R.id.edtCidade);
        edtEstado = view.findViewById(R.id.edtEstado);

        chkTermosdeUso = view.findViewById(R.id.chkTermosdeUso);
        
        btnCancelar = view.findViewById(R.id.btnCancelar);
        btnSalvar = view.findViewById(R.id.btnSalvar);

        novoCliente = new Cliente();
        clienteController = new ClienteController(getContext());
    }

    private boolean isVazio(EditText edt){
        return TextUtils.isEmpty(edt.getText());
    }
    private EditText valor(){

        if (isVazio(edtNomeCompleto)){
            return edtNomeCompleto;
        }else if (isVazio(edtTelefone)){
            return edtTelefone;
        }else if(isVazio(edtEmail)){
            return edtEmail;
        }else if(isVazio(edtCep)){
            return edtCep;
        }else if(isVazio(edtLogradouro)){
            return edtLogradouro;
        }else if(isVazio(edtNumero)){
            return edtNumero;
        }else if(isVazio(edtBairro)){
            return edtBairro;
        }else if(isVazio(edtCidade)){
            return edtCidade;
        }else if(isVazio(edtEstado)){
            return edtEstado;
        }

        return null;
    }


    private void escutarEventoDosBotoes() {


        chkTermosdeUso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chkTermosdeUso.isChecked()){
                    btnSalvar.setEnabled(true);
                }else {
                    btnSalvar.setEnabled(false);
                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isDadosOk = true;

                if(isVazio(edtNomeCompleto) || isVazio(edtTelefone) ||isVazio(edtEmail) || isVazio(edtCep) || isVazio(edtLogradouro) ||
                   isVazio(edtNumero) || isVazio(edtBairro) || isVazio(edtCidade) || isVazio(edtEstado)){
                    isDadosOk = false;
                }
                if(isDadosOk){

                    novoCliente.setNome(edtNomeCompleto.getText().toString());
                    novoCliente.setTelefone(edtTelefone.getText().toString());
                    novoCliente.setEmail(edtEmail.getText().toString());
                    novoCliente.setCep(Integer.parseInt(edtCep.getText().toString()));
                    novoCliente.setLogradouro(edtLogradouro.getText().toString());
                    novoCliente.setNumero(edtNumero.getText().toString());
                    novoCliente.setBairro(edtBairro.getText().toString());
                    novoCliente.setCidade(edtCidade.getText().toString());
                    novoCliente.setEstado(edtEstado.getText().toString());

                    novoCliente.setTermosDeUso(chkTermosdeUso.isChecked());

                    clienteController.incluir(novoCliente);

                    edtNomeCompleto.setText(null);
                    edtTelefone.setText(null);
                    edtEmail.setText(null);
                    edtCep.setText(null);
                    edtLogradouro.setText(null);
                    edtNumero.setText(null);
                    edtBairro.setText(null);
                    edtCidade.setText(null);
                    edtEstado.setText(null);


                }else {

                    Objects.requireNonNull(valor()).setError("*** Campo Obrigatorio");
                    Objects.requireNonNull(valor()).requestFocus();
                }

//                clienteController.incluir(novoCliente);


            }
        });

    }
}
