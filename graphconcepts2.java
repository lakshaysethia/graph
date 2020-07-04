public class L002 {
    
    static class Edge {
        int src;
        int nbr;
        int wt;
  
        Edge(int src, int nbr, int wt) {
           this.src = src;
           this.nbr = nbr;
           this.wt = wt;
        }
     }

     // GCC
     public static void gcc(ArrayList<Edge>[] graph,int vtx,boolean visited[],ArrayList<Integer> comp){
         comp.add(vtx);
         visited[vtx] = true;
 
         for(Edge e : graph[vtx]){
             if(!visited[e.nbr]){
                 gcc(graph,e.nbr,visited,comp); 
             }
         }
     }

    // GET All CONNECTED COMPONENT 
    public static ArrayList<ArrayList<Integer>> GetAllConnectedComponents(ArrayList<Edge>[] graph){
      ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
      boolean visited[] = new boolean[graph.length];
      // write your code here
        for(int i = 0; i<graph.length;i++){
            if(!visited[i]){
                // unvisited
                // GCC
                ArrayList<Integer> comp = new ArrayList();
                gcc(graph,i,visited,comp);
                comps.add(comp);
            }
        }
      
        return comps;    
    }
    
    // IS GRAPH CONNECTED
    public static boolean isGraphConnected(ArrayList<Edge>[] graph){
        ArrayList<ArrayList<Integer>> comps = GetAllConnectedComponents(graph);
        
        return comps.size() == 1;
   }

   // NUMBER OF ISLANDS //////////////////////
    public static int islands(int [][]area){
    // 1--> water
    // 0--> land
    boolean visited[][] = new boolean[area.length][area[0].length];
    int count = 0;
    for(int i = 0 ; i < area.length ; i++){
        for(int j = 0 ; j < area[0].length; j++){
            if(!visited[i][j] && area[i][j] == 0){
                // unvisited + land
                gccPostActive(area,i,j,visited);
                count++;
            }
        }
    }
    
    return count;
}

    public static void gccPostActive(int [][]area,int x,int y,boolean [][]visited){
        // north , east , west , south
        // mark all the land
        if(x >= 0 && x < area.length && y >=0 && y < area[0].length && !visited[x][y] && area[x][y] == 0){
            visited[x][y] = true; // mark ? -> repetition prevention
            
            // north
            gccPostActive(area,x-1,y,visited);
            // east
            gccPostActive(area,x,y+1,visited);
            // west
            gccPostActive(area,x,y-1,visited);
            // south
            gccPostActive(area,x+1,y,visited);
        }   
        
    }

    public static void gccPreActive(int [][]area,int x,int y,boolean [][]visited){
        // north , east , west , south
        // mark all the land
        
        
        visited[x][y] = true; // mark ? -> repetition prevention
        
        // north
        if(x-1 >= 0 && !visited[x-1][y] && area[x-1][y] == 0)
            gccPreActive(area,x-1,y,visited);
        // east
        if(y+1 < area[0].length && !visited[x][y+1] && area[x][y+1] == 0)
            gccPreActive(area,x,y+1,visited);
        // west
        if(y-1 >= 0 && !visited[x][y-1] && area[x][y-1] == 0)
            gccPreActive(area,x,y-1,visited);
        // south
        if(x+1 < area.length && !visited[x+1][y] && area[x+1][y] == 0)
            gccPreActive(area,x+1,y,visited);
    }
    
    // NUMBER OF PAIRS , such that none belong to same component
    public static int friendPairs(ArrayList<Edge>[] graph){
        ArrayList<ArrayList<Integer>> clubs = GetAllConnectedComponents(graph);
      //   System.out.println(clubs);
        
        ArrayList<Integer> clubFreq = new ArrayList<>();
        for(ArrayList<Integer> club : clubs){
            clubFreq.add(club.size());
        }
        
       
        int nop = 0;
        
        for(int i = 0; i < clubFreq.size() ; i++){
            for(int j = i+1 ; j< clubFreq.size() ;j++){
                nop += clubFreq.get(i) * clubFreq.get(j);
            }
        }
        return nop;
     }
}
