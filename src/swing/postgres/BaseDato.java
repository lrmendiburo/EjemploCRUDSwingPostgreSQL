package swing.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDato {

    public static void main(String[] args) {
    }
    
    static void creandoBD(){
        Connection cnx;
        PreparedStatement ps;
        try {
        cnx = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","1234");
        ps = cnx.prepareStatement("CREATE DATABASE clientes");
        ps.executeUpdate();
        ps.close();
        cnx.close();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    }

    static Connection conectando(String host, String port, String database, String user, String password) {

        Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://"+host+":"+port+"/"+database+"",
            ""+user+"", ""+password+"");
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
        return c;
    }

    static void crearTabla() {
        Connection connection = conectando("localhost", "5432", "clientes", "postgres", "1234");
        Statement stmt = null;
        try {

            stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Cliente "
                    + "(NIT INT PRIMARY KEY     NOT NULL,"
                    + " NOMBRE           TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    static void insertar(Cliente cliente) {
        Connection connection = conectando("localhost", "5432", "clientes", "postgres", "1234");
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "INSERT INTO Cliente (NIT,NOMBRE) "
                    + "VALUES ("+cliente.getNit()+""+", '"+cliente.nombre+"');";
            
            stmt.executeUpdate(sql);

            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    static ArrayList<Cliente> leer() {
        Connection connection = conectando("localhost", "5432", "clientes", "postgres", "1234");
        ArrayList<Cliente> listaC = new ArrayList<Cliente>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cliente;");
            while (rs.next()) {
                int nit = rs.getInt("nit");
                String nombre = rs.getString("nombre");
                Cliente cliente =new Cliente(nit, nombre);
                listaC.add(cliente);
                
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return listaC;
        
    }

    static void actualizar(Cliente cliente, int Nit) {
        Connection connection = conectando("localhost", "5432", "clientes", "postgres", "1234");
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String nombre=cliente.getNombre();
            String nit=cliente.getNit()+"";
            String nitOld=Nit+"";
            String sql = "UPDATE Cliente set NIT="+nit+" , NOMBRE='"+nombre+"' where NIT="+nitOld+" ;";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    static void eliminar(int Nit) {
        Connection connection = conectando("localhost", "5432", "clientes", "postgres", "1234");
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "DELETE from Cliente where NIT = "+Nit+";";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    
}
