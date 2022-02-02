package CONEX;

import ENTIDADE.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tab {
    
    final  CNX      cnx;
    public String   nom;
    public String[] hdr;
    public E e;

    protected Tab(CNX cnx, String nom, int id) { // Tab(le)
        this.cnx = cnx;
        this.nom = nom;
        init(id);
    }
    
    // ----------------------------------------------------------------------------------------------------- //

    /**
     * <p>Essa função corresponde ao INSERT</p>
     * @param o O(bjeto) a ser inserido na tabela.
     * @throws SQLException
     */
    
    public void ins(O o) throws SQLException{
        
        if (o.e.id == e.id){
        
            cnx.s("INSERT INTO " + nom + " VALUES("+o.toString2()+")");
        
        }
        
    }
    
    // ----------------------------------------------------------------------------------------------------- //
    
    public void del(String cond) throws SQLException{
        
        cnx.s("DELETE FROM " + nom + " WHERE ("+cond+")");
        
    }
    
    // ----------------------------------------------------------------------------------------------------- //
    
    public ArrayList <O> whr(String cond){
        
        ArrayList <O> os = new ArrayList<>();
        
        try {
            
            ArrayList <String[]> sas = cnx.g("SELECT * FROM " + nom + " WHERE ("+cond+");");
            for (String[] sa : sas){
                os.add( e.parse(sa) );
            }
            
        } catch (SQLException ex) {
            System.out.println("[Tab.getWhr()] ERRO NO BD: "+ex);
            
        } catch (X ex) {
            System.out.println("[Tab.getWhr()] ERRO DE PARSEAMENTO: "+ex);
        }
        
        return os;
        
    }
    
    // ----------------------------------------------------------------------------------------------------- //
    
    public ArrayList <O> ret(){
        
        ArrayList <O> os = new ArrayList<>();
        
        try {
            
            ArrayList <String[]> sas = cnx.g("SELECT * FROM " + nom);
            for (String[] sa : sas){
                os.add( e.parse(sa) );
            }
            
        } catch (SQLException ex) {
            System.out.println("[Tab.retrieve()] ERRO NO BD: "+ex);
            
        } catch (X ex) {
            System.out.println("[Tab.retrieve()] ERRO DE PARSEAMENTO: "+ex);
        }
        
        return os;
        
    }
    
    // ----------------------------------------------------------------------------------------------------- //
    
    private void init(int id){
        
        byte[] cs;
        
        try{
            
            ResultSet resset = cnx.q("SELECT * FROM "+nom);
            ResultSetMetaData resmd = resset.getMetaData();

            int colc = resmd.getColumnCount();
            
            hdr = new String [colc];
            cs  = new byte   [colc];

            for (int i = 0; i < colc; i++) {
                hdr [i] = resmd .getColumnLabel(i+1);
                cs  [i] = C.sqlCid(resmd.getColumnType(i+1));
            }
            
            e = new E(id, cs);
            
        } catch (SQLException ex) {
            System.out.println("Ouch! Houve um erro em \"Tab.init()\": "+ex);
        }
        
    }
    
}
