import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Pais {
	
	private  ArrayList<NodesCiutatPrincipal> llistaCiutatPrincipal = new ArrayList<NodesCiutatPrincipal>();
	private ArrayList<NodesL> rutaOptima = new ArrayList<NodesL>();
	
	public void LecturaDocument(String direccioDocument) throws FileNotFoundException, IOException {
		System.out.println("ESQUEMA DE RUTES QUE ES DISPOSEN:");
		boolean bandera=true;
		int index=-1;
		NodesCiutatPrincipal ciutatPrincipalAuxiliar;
		NodesVeins nodeVeiAuxiliar;
		
        String lineEnTractament;
        FileReader file = new FileReader(direccioDocument);
        BufferedReader buffer = new BufferedReader(file);
        while((lineEnTractament = buffer.readLine())!=null) {
        	
            String [] nomCp =lineEnTractament.split(":");
         
            
            ciutatPrincipalAuxiliar= new NodesCiutatPrincipal(nomCp[0]);
            llistaCiutatPrincipal.add(ciutatPrincipalAuxiliar);
            index=index+1;
            
			String nodes =  nomCp[1];
			String cadenaTractament="";
			String nomNodeFill="";
			int distanciaNodeFill = 0;
			for(int i = 0; i < nodes.length(); i++) {
                if(nodes.charAt(i)!='(' && nodes.charAt(i)!=')'&&nodes.charAt(i)!=';'){
                	cadenaTractament=cadenaTractament+nodes.charAt(i);
                }else{
                	if(!cadenaTractament.equals("")){
                		if(bandera){
                			nomNodeFill=cadenaTractament;
                			cadenaTractament="";
                		}else{
                        	distanciaNodeFill=Integer.parseInt(cadenaTractament);
                        	cadenaTractament="";                			
                		}
                		if(bandera){
                    		bandera=false;
                    	}else{
                    		bandera=true;
                    		if(distanciaNodeFill>0){
                    			nodeVeiAuxiliar=new NodesVeins(nomNodeFill,distanciaNodeFill);
                    			llistaCiutatPrincipal.get(index).afegirVeiLlista(nodeVeiAuxiliar);
                    		}
                    	}
                	}
                }
            }
        }
        buffer.close();
    }
	public void MostrarRutes(){
		for(int i = 0; i < llistaCiutatPrincipal.size(); i++) {
			
			System.out.println(llistaCiutatPrincipal.get(i).mostrarDadesCiutatPrincipal());
		}
		
	}
	public void EstriarUnaRuta(){
	
		int opcioTriadaOrigen;
		int opcioTriadaDesti;
		int voltesDonades=0;
		do{
			System.out.println("+===========================================+");
			System.out.println("Escull una opció de les següents per l'origen:");
			for(int x = 0; x < llistaCiutatPrincipal.size(); x++) {
				
				System.out.println((x+1)+"--"+llistaCiutatPrincipal.get(x).getNomCiutat());
				voltesDonades++;
			}
			System.out.println("+===========================================+");
			
			opcioTriadaOrigen=Test.sc.comprovarInt();
		}while (!comprovarPossiblesOpcions(opcioTriadaOrigen,voltesDonades));
		voltesDonades=0;
		do{
			System.out.println("+===========================================+");
			System.out.println("Escull una opció de les següents per el desti:");
			for(int x = 0; x < llistaCiutatPrincipal.size(); x++) {
				
				System.out.println((x+1)+"--"+llistaCiutatPrincipal.get(x).getNomCiutat());
				voltesDonades++;
			}
			System.out.println("+===========================================+");
			
			opcioTriadaDesti=Test.sc.comprovarInt();
		}while (!comprovarPossiblesOpcions(opcioTriadaDesti,voltesDonades));
	
		if(opcioTriadaOrigen==opcioTriadaDesti){
			System.out.println("La ciutat origen i desti son la mateixa");
		}else{
			String nomOrigen=llistaCiutatPrincipal.get(opcioTriadaOrigen-1).getNomCiutat();
			NodesL nodeTractament=new NodesL(nomOrigen,0,"");
			String ruta=algoritmeEstrella(nodeTractament,opcioTriadaDesti);
			System.out.println(ruta);
		}
		
	}
	public boolean comprovarPossiblesOpcions(int opcio,int voltesFetes){
		
		if(opcio>0 && opcio<voltesFetes+1){
			return true;
		}
		return false;
	}
	public String algoritmeEstrella(NodesL origen, int desti){
		
		String nomDesti=llistaCiutatPrincipal.get(desti-1).getNomCiutat();
		String ruta = origen.getNom()+origen.getCost();
		
		
		if(origen.getNom().equals(nomDesti)){
			return ruta;
		}
		return ruta;
		
	}
}
