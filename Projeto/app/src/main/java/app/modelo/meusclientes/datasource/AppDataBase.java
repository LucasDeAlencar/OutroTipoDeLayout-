package app.modelo.meusclientes.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import app.modelo.meusclientes.api.AppUtil;
import app.modelo.meusclientes.datamodel.ClienteDataModel;
import app.modelo.meusclientes.model.Cliente;


public class AppDataBase extends SQLiteOpenHelper {

    public static final String DB_NAME = "MeusCLientes.sqlite";
    public static final int DB_VERSION = 1;
    SQLiteDatabase db;

    public AppDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();/*Permite a escrita no banco de dados*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ClienteDataModel.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    /* Método para incluir dados no banco de dados */
    public boolean insert(String tabela, ContentValues dados){

        db = getWritableDatabase();

        boolean retorno = false;

        try{
            retorno = db.insert(tabela,null,dados) > 0/*Caso seja maior que 0 quer dizer que execulto(retorna true)*/;
        }catch (Exception e){
            Log.d(AppUtil.TAG,"insert:"+e.getMessage());
        }

        return retorno;
    }

    /* Método para remover dados no banco de dados */
    public boolean deleteByID(String tabela,int id){

        db = getWritableDatabase();

        boolean retorno = false;

        try{
            retorno = db.delete(tabela,"id = ?",new String[]{String.valueOf(id)}) > 0;
        }catch (Exception e){
            Log.d(AppUtil.TAG,"delete:"+e.getMessage());
        }

        return retorno;
    }

    public boolean update(String tabela,ContentValues dados){

        db = getWritableDatabase();

        boolean retorno = false;

        try {
            retorno = db.update(tabela,dados,"id=?", new String[] {String.valueOf(dados.get("id"))}) > 0;
        }catch (Exception e){
            Log.d(AppUtil.TAG, "update: " + e.getMessage());
        }


        return retorno;
    }

    public List<Cliente> getAllClientes(String tabela){

        db = getWritableDatabase();

        List<Cliente> clientes = new ArrayList<>();
        Cliente obj;

        String sql = "SELECT * FROM " + tabela;

        Cursor cursor;/* Consegui receber os valores de consultas do SQL */

        cursor = db.rawQuery(sql,null)/*Execulta uma query bruta*/;

        if(cursor.moveToFirst()/*Vai para o primeiro elemento da Tabela*/){

            do{

                obj = new Cliente();
                obj.setId(cursor.getInt(0)/*Retorna uma coluna do tipo "int" em determinado index */);
                obj.setNome(cursor.getString(1)/**/);
                obj.setTelefone(cursor.getString(2));

                clientes.add(obj);

            }while (cursor.moveToNext()/*Vai para o proximo*/);

        }

        return clientes;
    }


}
