package ENTIDADE;

public class X extends Exception {
    
    /**
     * <b>Em 'status' são contidos códigos de erro padrão, são esses:</b>
     * <ul>
     * <li>1 - Número não formatado adequadamente</li>
     * <li>2 - Caractere vazio/String de tamanho = 0</li>
     * <li>3 - Formatação de data inadequada</li>
     * <li></li>
     * </ul>
     */
    public final byte[] status;

    public X(byte[] status) {
        this.status = status;
    }
    
    public X(){
        this.status = null;
    }
    
    @Override
    public String toString() {
        
        String s = "[X] Códigos de Erro: ";
        
        for (byte b : status){
            s += b + " ";
        }
        
        return s;
    }
    
}
