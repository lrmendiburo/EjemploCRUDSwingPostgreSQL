
package swing.postgres;

import java.util.ArrayList;

public class Tools {
      
    static boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }
    
    static boolean nitExitente(int i) {  
        boolean flag=false;
        ArrayList<Cliente> listaC=BaseDato.leer();
        for(Cliente cliente:listaC){
            if(cliente.getNit()==i){
                flag=true;
            }
        }
        return flag;
    }
    
}
