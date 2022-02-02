package ENTIDADE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class E { // E(ntity)

    public final int id; public final byte[] cs;

    public E(int id, byte[] cs) {
        this.id = id;
        this.cs = cs;
    }
    
    // ---------------------------------------------------------------------------------- //

    /**
     *
     * @return Retorna um novo O dessa E
     */
    public O newo() {
        return new O(this);
    }
    
    // ---------------------------------------------------------------------------------- //
    
    /**
     *
     * @param sa As Strings que serão parseadas como atributos dessa E(ntidade)
     * @return Retorna o O formado com esses atributos
     * @throws X, Trata-se de uma classe estendendo Exception, contendo o campo 'status', referente aos códigos de erro. (veja o javadoc de X para saber especificações)
     */
    public O parse(String[] sa) throws X{
        
        if (sa.length != cs.length){
            throw new X();
        }
        
        O o = newo();
        
        byte[]  status = new byte[cs.length];
        boolean x = false; 
        
        for (int i = 0; i < cs.length; i++){
            
            if (sa[i] == null){
                continue;
            }
            
            try{
            
                switch(cs[i]){

                    case 0: // Inteiro
                        o.set(i, Integer.parseInt(sa[i]));
                        break;

                    case 1: // String
                        o.set(i, sa[i]);
                        break;

                    case 2: // Quebrado
                        o.set(i, Double.parseDouble(sa[i]));
                        break;

                    case 3: // Caractere
                        o.set(i, sa[i].charAt(0));
                        break;

                    case 4: // LocalDate
                        try {
                            o.set(i, LocalDate.parse(sa[i]));
                        } catch (Exception e) {
                            o.set(i, LocalDate.parse(sa[i], 
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        }
                        break;
                        
                    case 5: // Booleano
                        o.set(i, sa[i].equals("1") || sa[i].equals("true"));
                        break;

                    default:
                        break;

                }
                
            } catch (DateTimeParseException e){
                status[i] = 3; x = true;
                
            } catch (NumberFormatException e) {
                status[i] = 1; x = true;
                
            } catch (StringIndexOutOfBoundsException e){
                status[i] = 2; x = true;
            }
            
        }
        
        if (x){
            throw new X(status);
        }
        
        return o;
        
    }
    
    // ---------------------------------------------------------------------------------- //
    
    @Override
    public String toString() {
        
        String s = "";
        
        for (byte b : cs){
            s += b + " ";
        }
        
        return s;
        
    }
    
}
