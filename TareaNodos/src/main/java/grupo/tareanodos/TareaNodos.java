package grupo.tareanodos;

import java.util.Arrays;
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
        
        long startTime = System.currentTimeMillis();
        
        for(int i=0;i<c1;i++){
            String name = "";
            int age = 0;
            float notes = 0;
            for(int x=0;x<c2;x++){
                int letra = random.nextInt(25);
                name+=abc[letra];
            }
            age = random.nextInt(31);
            notes = Math.round(((random.nextFloat())*5)*100) / 100.0f;
            
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
        long endTime = System.currentTimeMillis(); // Tiempo de finalización
        long duration = endTime - startTime; // Duración en milisegundos
        JOptionPane.showMessageDialog(null,"Nodos creados");
        
        JOptionPane.showMessageDialog(null,"Tiempo del proceso: "+duration+" milisegundos");
        
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
        
        long startTime = System.currentTimeMillis();
        
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
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        JOptionPane.showMessageDialog(null, "La lista ha sido ordenada alfabéticamente por nombre.");
        
        JOptionPane.showMessageDialog(null,"Tiempo del proceso: "+duration+" milisegundos");

    }
    
    public void ordenarEnOrden() {
        
        long startTime = System.currentTimeMillis();
                    
        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "La lista està vacia");
            return;
        }
        
        // Recorrer los nodos        
        Nodo inicial = inicio;
        int cantidadNodos = getLongitud();
        int posicion = 0;
        String[] arrayNombres = new String[cantidadNodos];
        while(inicial!=null) {
            arrayNombres[posicion] = inicial.getNombre();
            inicial = inicial.getEnlace();
            posicion++;
        }
        
        // Tengo los nodos ordenados
        Arrays.sort(arrayNombres);
        
        
        // Algoritmo de busqueda
        // if inicio = arrayNombres[0]
        
        
        //Encontrar el primer nodo según el nombre
        String nombreInicio = arrayNombres[0];
        Nodo nuevoInicio = null;
        inicial = inicio;
        posicion = 1;
        while(inicial!=null) {
            if (inicial.getNombre().equals(nombreInicio)) {
                nuevoInicio = inicial;
                eliminarIndice(posicion);
                break;
            }
            inicial = inicial.getEnlace();
            posicion++;
        }        
        
        // Cicla cantidad de nodos veces - 1
        for (int i = 1; i < cantidadNodos; i++) {
            inicial = inicio;
            // Cicla hasta encontrar el nodo
            posicion = 1;
            while(inicial!=null) {
                if (inicial.getNombre().equals(arrayNombres[i])) {
                    Nodo temporal = nuevoInicio;
                    // Llega a la posición necesaria para establecer el enlace
                    for (int j = 1; j < i; j++) {
                        temporal = temporal.getEnlace();                        
                    }
                    temporal.setEnlace(inicial);
                    // Elimina al nodo de la lista original para
                    // no confundir nombres repetidos
                    eliminarIndice(posicion);
                    break;
                }
                posicion++;
                inicial = inicial.getEnlace();
            }
        }        
        inicio = nuevoInicio;
        JOptionPane.showMessageDialog(null, "Ordenados");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        JOptionPane.showMessageDialog(null,"Tiempo del proceso: "+duration+" milisegundos");
    }
    
    public int getLongitud() {
        Nodo temporal = inicio;
        int cantidad = 0;
        if (inicio != null) {        
            do {
            cantidad++;
            temporal = temporal.getEnlace();
            } while (temporal!=null);
        }
        return cantidad;
    }
    
    public void eliminarIndice(int indice) {
        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "La lista está vacia");
            return;
        } 
        
        int posicion = 1;
        boolean indiceEncontrado = false;
        
        Nodo anterior = inicio;        
        Nodo siguiente = null;
        
        if (indice == 1) {
            inicio = inicio.getEnlace();
            return;
        }
        
        while(anterior!=null) {
            if (posicion+1==indice) {
                siguiente = anterior.getEnlace().getEnlace();
                indiceEncontrado = true;
                break;
            }
            anterior = anterior.getEnlace();
            posicion++;
        }
        if (indiceEncontrado) {
            anterior.setEnlace(siguiente);
        }
        else {
            JOptionPane.showMessageDialog(null, "No se encontró el indice ingresado.");
        }        
    }
}
