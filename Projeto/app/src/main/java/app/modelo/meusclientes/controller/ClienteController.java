package app.modelo.meusclientes.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import app.modelo.meusclientes.api.AppUtil;
import app.modelo.meusclientes.datamodel.ClienteDataModel;
import app.modelo.meusclientes.datasource.AppDataBase;
import app.modelo.meusclientes.model.Cliente;

public class ClienteController extends AppDataBase implements iCrud<Cliente>{

    ContentValues dadoDoObjeto;/*Serve para fazer alterações no banco de dados*/

    public ClienteController(@Nullable Context context) {
        super(context);

        Log.d(AppUtil.TAG,"ClientrController: Conectado ");
    }

    @Override
    public boolean incluir(Cliente obj) {
        /*Passa uma key e o o valor*/
        dadoDoObjeto = new ContentValues();

        dadoDoObjeto.put(ClienteDataModel.NOME/*coluna*/,obj.getNome()/*Valor*/);
        dadoDoObjeto.put(ClienteDataModel.TELEFONE/*coluna*/,obj.getTelefone()/*Valor*/);
        dadoDoObjeto.put(ClienteDataModel.EMAIl/*coluna*/,obj.getEmail()/*Valor*/);
        dadoDoObjeto.put(ClienteDataModel.CEP/*coluna*/,obj.getCep()/*Valor*/);
        dadoDoObjeto.put(ClienteDataModel.LOGRADOURO/*coluna*/,obj.getLogradouro()/*Valor*/);
        dadoDoObjeto.put(ClienteDataModel.NUMERO/*coluna*/,obj.getNumero()/*Valor*/);
        dadoDoObjeto.put(ClienteDataModel.BAIRRO/*coluna*/,obj.getBairro()/*Valor*/);
        dadoDoObjeto.put(ClienteDataModel.CIDADE/*coluna*/,obj.getCidade()/*Valor*/);
        dadoDoObjeto.put(ClienteDataModel.ESTADO/*coluna*/,obj.getEstado()/*Valor*/);
        dadoDoObjeto.put(ClienteDataModel.TERMOS_DE_USO/*coluna*/,obj.isTermosDeUso()/*Valor*/);


        return insert(ClienteDataModel.TABELA, dadoDoObjeto);
    }

    @Override
    public boolean alterar(Cliente obj) {
        dadoDoObjeto = new ContentValues();

        dadoDoObjeto.put(ClienteDataModel.ID/*coluna*/,obj.getId()/*Valor*/);
        dadoDoObjeto.put(ClienteDataModel.NOME/*coluna*/,obj.getNome()/*Valor*/);
        dadoDoObjeto.put(ClienteDataModel.EMAIl/*coluna*/,obj.getEmail()/*Valor*/);

        return update(ClienteDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public boolean deletar(int id) {

        return deleteByID(ClienteDataModel.TABELA,id);
    }

    @Override
    public List<Cliente> listar() {

        return getAllClientes(ClienteDataModel.TABELA);

    }

    public List<String> gerarListaDeClientesListView(){

        List<String> clientes = new ArrayList<>();

        for (Cliente obj : listar()){

            clientes.add(obj.getId()+", " + obj.getNome());

        }
        return clientes;
    }



}
