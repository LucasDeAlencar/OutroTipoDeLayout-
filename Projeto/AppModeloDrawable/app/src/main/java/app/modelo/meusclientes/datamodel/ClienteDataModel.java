package app.modelo.meusclientes.datamodel;

/*Esta classe representara uma tabela no SQL*/
public class ClienteDataModel {

    /*Este vai ser o nome da tabela*/
    public static final String TABELA = "cliente";

    /*Estás variaveis vão representar as colunas*/
     public static final String ID = "id";// Integer
    public static final String NOME = "nome";// text
    public static final String EMAIl = "email";// text
    public static final String TELEFONE = "telefone";// text
    public static final String CEP = "cep";// text
    public static final String LOGRADOURO = "logradouro";// text
    public static final String NUMERO = "numero";// text
    public static final String BAIRRO = "bairro";// text
    public static final String CIDADE = "cidade";// text
    public static final String ESTADO = "estado";// text
    public static final String TERMOS_DE_USO = "termos_de_uso";// text




    /*Criação da query par a tabela dos banco de dados*/
    public static String queryCriarTabela = "";

    /*Método para gerar o Script para criar a tabela*/
    public static String criarTabela(){

        queryCriarTabela += "CREATE TABLE IF NOT EXISTS " + TABELA +"("+
        ID+" integer primary key autoincrement, "+
        NOME+" text, "+
        TELEFONE+" text, "+
        EMAIl+" text, "+
        CEP+" integer, "+
        LOGRADOURO+" text, "+
        NUMERO+" text, "+
        BAIRRO+" text, "+
        CIDADE+" text, "+
        ESTADO+" text, "+
        TERMOS_DE_USO + " INTEGER"+")";

        return queryCriarTabela;
    }



}
