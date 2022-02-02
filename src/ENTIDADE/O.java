package ENTIDADE;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class O { // O(bject)

    public E e;
    private Object[] a;

    /**
     * <p>Construtor</p>
     * @param e Entidade do O
     */
    protected O(E e) {
        this.e = e;
        this.a = new Object[e.cs.length];
    }
    
    // ---------------------------------------------------------------------------------- //

    /**
     *
     * @param i Índice do atributo
     * @param o Novo valor para o atributo
     * @return Retorna se foi possível setar o novo valor
     */
    public boolean set(int i, Object o) {
        byte c = C.cid(o);

        if (c == 127 || c == e.cs[i]) {
            a[i] = o;
            return true;
        }
        
        return false;

    }
    
    // ---------------------------------------------------------------------------------- //

    /**
     *
     * @param <T> Tipo do retorno
     * @param i Índice do atributo
     * @return Retorna o valor do atributo
     */
    public <T> T get(int i) {
        
        try {

            return (T) a[i];
            
        } catch (Exception e) {}
        
        return null;

    }
    
    // ---------------------------------------------------------------------------------- //
    
    @Override
    public String toString() {
        
        String s = "";
       
        for (Object o : a){
            s += o + ";";
        }
        
        return s.substring(0,s.length()-1);
    }
    
    // ---------------------------------------------------------------------------------- //
    
    /**
     * <p>INSERT INTO exemplo VALUES(objeto.toString2())</p>
     * @return Retorna o objeto em String formatada de modo a possibilitar fácil INSERT
     */
    public String toString2(){
        
        String s = "";
       
        for (Object o : a){
            if (o instanceof Boolean){
                s += "\'" + (((boolean)o) ? 1 : 0) + "\'";
            } else if (o!=null){ 
                s += "\'" + o + "\'"; 
            } else {
                s += o;
            }
            
            s += ",";
            
        }
        
        return s.substring(0,s.length()-1);
    }
    
    // ---------------------------------------------------------------------------------- //
    
    /**
     *
     * @param o Verifica a igualdade entre dois O's ou entre o conteúdo deste O e um Object[]
     * @return Afirmação de igualdade
     */
    @Override
    public boolean equals(Object o) {
        
        if (o instanceof O){
            
            O oo = (O) o;
            
            if (oo.e.id == e.id){
                
                if (Arrays.equals(a, oo.getA())){
                    
                    return true;
                    
                }
                
            }
            
        } else if (o instanceof Object[]){
            
            return Arrays.equals(a, (Object[])o);
            
        }
        
        return false;
        
    }
    
    // ---------------------------------------------------------------------------------- //

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.e);
        hash = 83 * hash + Arrays.deepHashCode(this.a);
        return hash;
    }
    
    // ---------------------------------------------------------------------------------- //
    
    public Object[] getA(){
        
        return a;
        
    }
    
    // ---------------------------------------------------------------------------------- //
    
    public int getInt(int i){
        return (int) a[i];
    }
    
    public String getString(int i){
        return (String) a[i];
    }
    
    public char getChar(int i){
        return (char) a[i];
    }
    
    public LocalDate getLdt(int i){
        return (LocalDate) a[i];
    }
    
    public double getDouble(int i){
        return (double) a[i];
    }
    
    public boolean getBoolean(int i){
        return (boolean) a[i];
    }

}
