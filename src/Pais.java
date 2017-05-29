import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Pais {
	
	private  ArrayList<NodesCiutatPrincipal> llistaCiutatPrincipal = new ArrayList<NodesCiutatPrincipal>();
	//private ArrayList<NodesL> rutaOptima = new ArrayList<NodesL>();
	String ruta="";
	
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
			
			ArrayList<NodesL> ciudades=null;
			
			
			ciudades=new ArrayList<NodesL>();
			
			for(int i = 0; i < llistaCiutatPrincipal.size(); i++) {
	
				ciudades.add(new NodesL(llistaCiutatPrincipal.get(i).getNomCiutat()));
			}
			
			for(int i = 0; i < ciudades.size(); i++) {
				
				
					
					ArrayList<NodesVeins> llistaVeins = llistaCiutatPrincipal.get(i).getLlistatVeins();
			
					
					for(int y = 0; y < llistaVeins.size(); y++) {
						
						
						NodesL nodeVei;
						nodeVei=new NodesL(llistaVeins.get(y).getNomVei(),llistaVeins.get(y).getCost());
						//System.out.println(ciudades.get(i).getName());
						//System.out.println(nodeVei.getName()+nodeVei.getCost());
						ciudades.get(i).addNextNode(nodeVei);
						
					}
					/*System.out.println("Principa:"+ciudades.get(i).getName());
					ArrayList<NodesL> llistaL = ciudades.get(i).getNextNodes();
						for(int y = 0; y < llistaL.size(); y++) {
						System.out.println(llistaL.get(y).getName());
						
					}*/
				
			}
			
			
			//ciudades.get(0).addNextNode(ciudades.get(1));ciudades.get(0).addNextNode(ciudades.get(2));ciudades.get(1).addNextNode(ciudades.get(3));ciudades.get(1).addNextNode(ciudades.get(4));ciudades.get(1).addNextNode(ciudades.get(0));ciudades.get(2).addNextNode(ciudades.get(6));ciudades.get(2).addNextNode(ciudades.get(0));ciudades.get(3).addNextNode(ciudades.get(5));ciudades.get(3).addNextNode(ciudades.get(1));ciudades.get(4).addNextNode(ciudades.get(5));ciudades.get(4).addNextNode(ciudades.get(1));ciudades.get(5).addNextNode(ciudades.get(3));ciudades.get(5).addNextNode(ciudades.get(4));ciudades.get(5).addNextNode(ciudades.get(8));ciudades.get(5).addNextNode(ciudades.get(6));ciudades.get(6).addNextNode(ciudades.get(5));ciudades.get(6).addNextNode(ciudades.get(2));ciudades.get(6).addNextNode(ciudades.get(7));ciudades.get(7).addNextNode(ciudades.get(6));ciudades.get(8).addNextNode(ciudades.get(5));
			
			NodesL c1 = ciudades.get(opcioTriadaOrigen-1);
			
			String c2= ciudades.get(opcioTriadaDesti-1).getName();
			ArrayList<NodesL> lista= c1.findShortesRoute(c2, c1);
			
			int costTotal=0;
			for(int x =0 ; x<lista.size();x++){
				costTotal=costTotal+lista.get(x).getCost();
				System.out.println(lista.get(x).getName());
			}
			
			System.out.println(costTotal);
			
		}
		
	}
	public boolean comprovarPossiblesOpcions(int opcio,int voltesFetes){
		
		if(opcio>0 && opcio<voltesFetes+1){
			return true;
		}
		return false;
	}
		
		
	
}
