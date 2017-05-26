
public class NodesL {

	String nom;
	int cost;
	String cami;
	
	public NodesL(String nom,int cost,String cami){
		this.nom=nom;
		this.cost=cost;
		this.cami=cami;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getCami() {
		return cami;
	}

	public void setCami(String cami) {
		this.cami = cami;
	}
}
