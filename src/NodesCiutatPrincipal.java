import java.util.ArrayList;
//Clase per crear objecte que seran ciutat principal utilitzacio en la cartografia pares de fills nodes mes importants
//metodes compresos entre get set i constructor unic metode rellevant com a tal
//el pas de informacio de les ciutat i pasem aquesta per ser mostrades
public class NodesCiutatPrincipal {
	
	private String nomCiutat;
	private ArrayList<NodesVeins> llistatVeins=new ArrayList<NodesVeins>();
	private String informacioVeins;
	
	public NodesCiutatPrincipal(String nomCiutat){
		this.setNomCiutat(nomCiutat);
	}
	public String mostrarDadesCiutatPrincipal(){		
		
		informacioVeins=("Ciutat Principal "+nomCiutat+":"+"\n"+"Llistat veins:"+"\n");	
		
		for(int i = 0; i < llistatVeins.size(); i++) {
			
			informacioVeins=informacioVeins+llistatVeins.get(i).MostrarDadesVei();
		}
		return informacioVeins;
	}

	public void afegirVeiLlista(NodesVeins nodeVei){
		llistatVeins.add(nodeVei);
	}
	public String getNomCiutat() {
		return nomCiutat;
	}

	public void setNomCiutat(String nomCiutat) {
		this.nomCiutat = nomCiutat;
	}

	public ArrayList<NodesVeins> getLlistatVeins() {
		return llistatVeins;
	}

	public void setLlistatVeins(ArrayList<NodesVeins> llistatVeins) {
		this.llistatVeins = llistatVeins;
	}
}
