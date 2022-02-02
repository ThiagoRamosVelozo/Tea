package CONEX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class CNX {

    public Connection cnc = null;

    String url, usr, psw, udb;

    public CNX(String host, String db, String usr, String psw) { // CONSTRUTOR
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("DEU MAL! -> "+e);
            return;
        }

        this.udb = db;
        this.url = "jdbc:mysql://" + host + "/" + db;
        this.usr = usr;
        this.psw = psw;

    }

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------- //

    /**
     *
     * @return Retorna se foi possível realizar a conexão.
     */
    public boolean c() { // CONECTAR

        try {

            cnc = DriverManager.getConnection(url, usr, psw);
            cnc.setAutoCommit(true);
            System.out.println("[c]: SUCESSO!");
            return true;

        } catch (SQLException e) {

            System.out.println("[c]: ERRO: " + e);

        }

        return false;

    }

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------- //

    /**
     *
     * @param sql Expressão a ser executada.
     * @throws java.sql.SQLException
     */
    public void s(String sql) throws SQLException { // STATEMENT

        if (cnc == null) {

            System.out.println("[s]: CONEXÃO NULA!");

        }

        PreparedStatement p = cnc.prepareStatement(sql);
        p.execute();
        System.out.println("[s]: SUCESSO!");

    }
    
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------- //

    /**
     *
     * @return Retorna a lista de nomes das tabelas do BD
     */
    public ArrayList<String> l() { // LISTA DE TABELAS

        ArrayList<String> tns = new ArrayList<>();

        try {

            ResultSet resset = cnc.getMetaData().getTables(cnc.getCatalog(), "", null, new String[]{"TABLE", "VIEW"});

            while (resset.next()) {
                tns.add(resset.getString("TABLE_NAME"));
            }

            System.out.println("[l]: SUCESSO!");

        } catch (SQLException se) {
            System.out.println("[l]: ERRO: " + se);
        }

        return tns;
    }
    
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------- //

    /**
     *
     * @param sql Expressão a ser processada (Deve possuir retorno!)
     * @return Retorna ArrayList String[], correspondente aos dados retornados pela expressão.
     * @throws java.sql.SQLException
     */
    public ArrayList<String[]> g(String sql) throws SQLException { // SELECT XYZ

        ArrayList<String[]> sel = new ArrayList<>();

        ResultSet resset = q(sql);

        try {

            ResultSetMetaData resmd = resset.getMetaData();

            int colc = resmd.getColumnCount();

            while (resset.next()) {

                String[] selx = new String[colc];

                for (int i = 0; i < colc; i++) {
                    selx[i] = resset.getString(i + 1);
                }

                sel.add(selx);

            }

            System.out.println("[g]: SUCESSO!");

        } catch (SQLException ex) {

            System.out.println("[g]: ERRO: " + ex);

        }

        return sel;

    }

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------- //

    /**
     *
     * @param sql Expressão a ser processada pelo SGBD.
     * @return Retorna ResultSet com os dados retornados pela expressão.
     * @throws java.sql.SQLException
     */
    public ResultSet q(String sql) throws SQLException { // STATEMENT

        if (cnc == null) {

            System.out.println("[q]: CONEXÃO NULA!");

            return null;

        }

        PreparedStatement p = cnc.prepareStatement(sql);
        ResultSet resset = p.executeQuery();
        System.out.println("[q]: SUCESSO!");

        return resset;

    }

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------- //
    
    public Tab getTab(String tn, int id) {
        return new Tab(this, tn, id);
    }
    
    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------- //
    
    public String getUdb() {
        return udb;
    }
    
}
