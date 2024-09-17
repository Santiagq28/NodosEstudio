package grupo.tareanodos;

import java.util.Random;
import javax.swing.JOptionPane;

public class TareaNodos {

    Random random = new Random();
    String abc[]={"a","b","c","d","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    Nodo inicio;
    TareaNodos(){
        inicio = null;
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    public void crearNodos(int c1, int c2){
        
        for(int i=0;i<c1;i++){
            String name = "";
            int age = 0;
            float notes = 0;
            for(int x=0;x<c2;x++){
                int letra = random.nextInt(25);
                name+=abc[letra];
            }
            age = random.nextInt(31);
            notes = Math.round((random.nextFloat(5))*100) / 100.0f;
            
            Nodo nuevo = new Nodo();
            nuevo.setNombre(name);
            nuevo.setEdad(age);
            nuevo.setPromedio(notes);
            nuevo.setEnlace(null);
            
            if(inicio == null){
                inicio = nuevo;
            }else{
                Nodo temporal = inicio;
                while(temporal.getEnlace() != null){
                    temporal = temporal.getEnlace();
                }
                temporal.setEnlace(nuevo);
            }
        }
        JOptionPane.showMessageDialog(null,"Nodos creados");
    }
    
    public void consultarNodos(){
        Nodo temporal = new Nodo();
        if(inicio == null){
            JOptionPane.showMessageDialog(null,"Lista vacía");
            return;
        }
        temporal = inicio;
        if(temporal.getEnlace() == null){
            JOptionPane.showMessageDialog(null,temporal.getNombre()+" "+temporal.getEdad()+" "+temporal.getPromedio());
            return;
        }else{
            while(temporal.getEnlace()!=null){
                JOptionPane.showMessageDialog(null,temporal.getNombre()+" "+temporal.getEdad()+" "+temporal.getPromedio());
                temporal = temporal.getEnlace();
            }
            
        }
    }
    
    public void ordenarNodos(){
        if (inicio == null || inicio.getEnlace() == null) {
            JOptionPane.showMessageDialog(null, "La lista está vacía o tiene solo un elemento.");
            return;
        }

        boolean huboCambios;
        do {
            Nodo actual = inicio;
            Nodo siguiente = inicio.getEnlace();
            Nodo anterior = null;
            huboCambios = false;

            while (siguiente != null) {
                if (actual.getNombre().compareToIgnoreCase(siguiente.getNombre()) > 0) {
                    huboCambios = true;

                    if (anterior == null) {
                        actual.setEnlace(siguiente.getEnlace());
                        siguiente.setEnlace(actual);
                        inicio = siguiente;
                    } else {
                        anterior.setEnlace(siguiente);
                        actual.setEnlace(siguiente.getEnlace());
                        siguiente.setEnlace(actual);
                    }

                    anterior = siguiente;
                    siguiente = actual.getEnlace();
                } else {
                    anterior = actual;
                    actual = siguiente;
                    siguiente = siguiente.getEnlace();
                }
            }
        } while (huboCambios);

        JOptionPane.showMessageDialog(null, "La lista ha sido ordenada alfabéticamente por nombre.");
    

    }
}
