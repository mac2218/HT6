import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.TreeMap;


// Daniel Machic 22118
// Hoja de trabajo #6
// 04/03/2023
// FactoryMap

public class FactoryMap<K,V>{

    // Metodo que obtiene un map segun se haya indicado
    public Map<String, List<String>> getMap(String tipo){

        if (tipo == null){
            return null;}
        if(tipo.equalsIgnoreCase("LinkedHashMap")){
            return new LinkedHashMap<>();}
        if(tipo.equalsIgnoreCase("HashMap")){
            return new HashMap<>();}   
        if(tipo.equalsIgnoreCase("TreeMap")){
            return new TreeMap<>();}

        return null;

    }

}