import java.util.ArrayList;

public class NodesL {

	private String name	=null;
	private int cost=0;
	private boolean parent=false;
	private ArrayList<NodesL> nextNodes=null;
	
	public ArrayList<NodesL> getNextNodes() {
		return nextNodes;
	}
	public NodesL(String name){
		this.name=name;
		this.nextNodes=new ArrayList<NodesL>();
	}
	public NodesL(String name, int cost){
		this.name=name;
		this.setCost(cost);
		this.nextNodes=new ArrayList<NodesL>();
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

	public void addNextNode(NodesL ciudad) {
		this.nextNodes.add(ciudad);
		
	}
	public ArrayList<NodesL> findShortesRoute(String destination,NodesL parentNode){
		
		ArrayList<NodesL>route = new ArrayList<NodesL>();
		//System.out.println(this.name);
		if(this.name.equalsIgnoreCase(destination)){
			route.add(this);
			return route;
		}else{
			for(int i =0; i<nextNodes.size();i++){
				System.out.println(nextNodes.get(i).getCost());
				if(!nextNodes.get(i).isParent()){
				
					parentNode.setParent(true);
					ArrayList<NodesL>newRoute=nextNodes.get(i).findShortesRoute(destination,this);
					
					if(!newRoute.isEmpty()&&(newRoute.size()<route.size() || route.isEmpty())){
						route=newRoute;
					}
				}
			
			}
			this.parent=false;
			if(!route.isEmpty()){
				route.add(this);
			}
			return route;
		}
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}
