package ENTIDADE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class C { // C(lass)
    
    private static final ArrayList <Class> clazzs = new ArrayList<>( Arrays.asList(
            new Class[]{
                
                Integer.class,      String.class, 
                Double.class,       Character.class, 
                LocalDate.class,    Boolean.class
                    
            }
    ) );
    
    private static final ArrayList <Integer> sqlTs = new ArrayList<>( Arrays.asList(
            new Integer[]{ 4, 12, 8, 1, 91, -6 }
    ) );
    
    public static byte cid(Object ob){
        
        if (ob == null){
            return 127;
        }
        
        return (byte) clazzs.indexOf(ob.getClass());
        
    }
    
    public static byte sqlCid(int type){
        
        return (byte) sqlTs.indexOf(type);
        
    }
    
}
