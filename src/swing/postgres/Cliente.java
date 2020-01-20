
package swing.postgres;

public class Cliente {
    int nit=0;
    String nombre="";
    
    public Cliente(int NIT, String Nombre){
        this.nit=NIT;
        this.nombre=Nombre;
    }
    
    public int getNit() {
        return nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", nit=" + nit + '}';
    }
    
    
    
}
