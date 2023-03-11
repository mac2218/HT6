
import java.util.*;
import javax.print.DocFlavor.INPUT_STREAM;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// Daniel Machic 22118
// Hoja de trabajo #6
// 04/03/2023
// Principal

public class Principal{

    public static void main(String[] args){
        ArrayList<String> Archivolineas = new ArrayList<>();
        File file = new File("C:/Ejemplos/ListadoProducto.txt"); 
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                while(br.ready()) {
                    Archivolineas.add(br.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner tec = new Scanner(System.in); 
        boolean continuar = false;
        String type;
        int option = 0;
        

        FactoryMap<String, ArrayList<String>> factory = new FactoryMap<String, ArrayList<String>>();
        Map<String, List<String>> Inventary;
        Map<String, List<String>> Group;
    
        System.out.println("\nQue tipo de implementacion de map deseas usar: ");
        System.out.println("1) HashMap   2) TreeMap   3) LinkedHashMap ");
        type = tec.nextLine();  
        
        Group = factory.getMap(type);
        Inventary = factory.getMap(type);

        List<String> lacteos = new ArrayList<>();
        lacteos.add("Queso de cabra");
        lacteos.add("Queso Manchego");
        lacteos.add("Leche descremada");
        lacteos.add("Leche deslactosada");
        lacteos.add("Leche entera");
        Inventary.put("Lacteos", lacteos);

        List<String> sillones = new ArrayList<>();
        sillones.add("Cojines y colchonetas de masaje");
        sillones.add("Sillones relax y sofas de masajes");
        sillones.add("Sillones de masajes avanzados");
        sillones.add("Sofas camas");
        Inventary.put("Sillones de masaje", sillones);

        List<String> condimentos = new ArrayList<>();
        condimentos.add("Sirope de regaliz");
        condimentos.add("Especies Cajun del chef");
        condimentos.add("Mezcla Gumbo del chef");
        Inventary.put("Condimentos", condimentos);

        List<String> muebles = new ArrayList<>();
        muebles.add("Mesas de jardin");
        muebles.add("Sillas de jardin");
        muebles.add("Conjuntos mesas y sillas de jardin");
        muebles.add("Mesas de Ping Pong exteriores");
        Inventary.put("Mueble de terraza", muebles);
        
        List<String> frutas = new ArrayList<>();
        frutas.add("Peras secas");
        frutas.add("Pasas");
        frutas.add("Manzana roja");
        frutas.add("Manzana verde");
        Inventary.put("Frutas", frutas);

        List<String> bebidas = new ArrayList<>();
        bebidas.add("Cerveza tibetana Barley");
        bebidas.add("Te frio");
        bebidas.add("Coca cola 1 litro");
        bebidas.add("Coca cola 2 litros");
        Inventary.put("Bebidas", bebidas);

        List<String> carnes = new ArrayList<>();
        carnes.add("Res");
        carnes.add("Pollo");
        carnes.add("Cerdo");
        carnes.add("Camarones");
        carnes.add("Pescados");
        Inventary.put("Carnes", carnes);

        while(continuar == false){     

            System.out.println("\n ........ MENU ........  \n");
            System.out.println("¿Que desea que se mueste?\n") ;
            System.out.println(" 1. Agregar producto ");
            System.out.println(" 2. Informacion de cada articulo  ");
            System.out.println(" 3. Informacion de cada articulo ordenada por tipo");
            System.out.println(" 4. Categoria del producto");
            System.out.println(" 5. El producto y su categoria en el inventario ");
            System.out.println(" 6. El producto y la categoria en el inventario ordenado por tipo");
            System.out.println(" 7. Salir ");

            System.out.println("\n Ingrese que desea hacer: ");
            option = Integer.parseInt(tec.nextLine());   
            
            switch(option){

                case 1:

                    // Acá se agrega un producto
                    System.out.println("¿A que categoria categoría pertenece el producto? \n ");
                    String category = tec.nextLine();    
                    if(Inventary.containsKey(category)){

                        System.out.println("¿Que producto desea ingresar?: ");
                        String prod = tec.nextLine();     

                        String busqueda = prod;
                        for (Map.Entry<String, List<String>> entry : Inventary.entrySet()) {
                            List<String> values = entry.getValue();
                            if (values.contains(busqueda)) {

                                Group.putIfAbsent(category, new ArrayList<String>());
                                Group.get(category).add(prod);    
                                System.out.println("Producto agregado! \n");
                            }
                        }

                    }
                    else{
                        System.out.println(" Error, la categoria no existe ");
                    }

                break;

                case 2:

                    //Muestra el carrito del usuario
                    System.out.println("\n");

                    System.out.println(" Informacion del carrito actual ");
                    for (Map.Entry<String, List<String>> entry : Group.entrySet()) {
                        String llave = entry.getKey();
                        List<String> valores = entry.getValue();
                        System.out.println("\nCategoria: " + llave);

                        Map<String, Integer> myProducts = new HashMap<>();
                        for (String valor : valores) {
                            int cant = myProducts.getOrDefault(valor, 0);
                            myProducts.put(valor, cant + 1);
                        }

                        for (Map.Entry<String, Integer> values : myProducts.entrySet()) {
                            String valor = values.getKey();
                            int cant = values.getValue();
                            System.out.println("  Producto: " + valor + "\nCantidad: " + cant);
                        }
                    }

                break;                

                case 3:
                    
                    //Muestra el carrito del usuario pero ordenado segun la categoria
                    System.out.println("\n");

                    System.out.println(" Informacion de su pedido ");

                    List<Map.Entry<String, List<String>>> orderedGroup = new ArrayList<>(Group.entrySet());
                    orderedGroup.sort(Comparator.comparing(Map.Entry::getKey));

                    for (Map.Entry<String, List<String>> entry : orderedGroup) {

                        String llave = entry.getKey();
                        List<String> valores = entry.getValue();
                        System.out.println("\nCategoria: " + llave);

                        Map<String, Integer> myProducts = new HashMap<>();
                        for (String valor : valores) {
                            int cant = myProducts.getOrDefault(valor, 0);
                            myProducts.put(valor, cant + 1);
                        }
                        List<Map.Entry<String, Integer>> orderedProd = new ArrayList<>(myProducts.entrySet());
                        orderedProd.sort(Comparator.comparing(Map.Entry::getKey));

                        for (Map.Entry<String, Integer> values : orderedProd) {
                            String valor = values.getKey();
                            int cant = values.getValue();
                            System.out.println("Hay" + cant + "unidades de"+ valor);
                        }
                    }

                break;

                case 4:
                // Opcion que sirve para buscar la cateoría del producto
                System.out.println(" ¿Que producto deseas buscar? \n ");
                String prod = tec.nextLine();    
                String isfound = null;

                for(String key : Inventary.keySet()){

                    List<String> values = Inventary.get(key);

                    if(values.contains(prod)){
                        isfound = key;
                        break;
                    }
                }

                if(isfound != null){
                    System.out.println("Este producto pertenece a " + isfound);
                }else{
                    System.out.println("Categoria invalida");
                }

            break;

                case 5:
                for (Map.Entry<String, List<String>> entry : Inventary.entrySet()) {
		            System.out.println(entry.getKey()+" : "+entry.getValue());
		        }

                break; 
                
                case 6:
                    // Muestras todas las categorias del Inventary ordenadas por orden alfabetico
                    if(Inventary instanceof HashMap){

                        Map<String, List<String>> InventaryOrd = new TreeMap<String, List<String>>(Inventary);
                        for (Map.Entry<String, List<String>> entry : InventaryOrd.entrySet()) {
                            System.out.println(entry.getKey()+" : "+entry.getValue());
                        }

                    }else if(Inventary instanceof TreeMap){
                        for (Map.Entry<String, List<String>> entry : Inventary.entrySet()) {
                            System.out.println(entry.getKey()+":"+entry.getValue());
                        }
                    }else if(Inventary instanceof LinkedHashMap){
                        Map<String, List<String>> InventaryOrd = new TreeMap<String, List<String>>(Inventary);
                        for (Map.Entry<String, List<String>> entry : InventaryOrd.entrySet()) {
                            System.out.println(entry.getKey()+":"+entry.getValue());
                        }

                    }

                break;
                case 7:
 
                    System.out.println(" Fin del programa");
                    continuar = true;
                break;
                default:
                    System.out.println("Seleccion incorrecta, vuelve a ingresarlo \n");
                break;

            }

        }
        
    }

}
