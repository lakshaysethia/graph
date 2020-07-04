public class L003 {
    
    // BFS TRAVERSAL ``````````````````````````````````````````````````````````````````````````````
    public static class Pair{
        int vtx;
        String psf;
        Pair(int vtx , String psf){
            this.vtx = vtx;
            this.psf = psf;
        }
    }
    
    public static void BFS(ArrayList<Edge>[] graph,int src){
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(src,""+src));
        boolean visited[] = new boolean[graph.length];
        while(queue.size() > 0){
            Pair tmp = queue.remove();   
            if(!visited[tmp.vtx]){
                // mark 
                visited[tmp.vtx] = true;
                
                System.out.println(tmp.vtx +"@" + tmp.psf);
                
                // add
                for(Edge e : graph[tmp.vtx]){
                    if(!visited[e.nbr]){
                        queue.add(new Pair(e.nbr,tmp.psf+e.nbr));
                    }
                }
            }
        }   
    }

    // IS GRAPH CYCLIC ``````````````````````````````````````````````````````````````````````````````
    public static boolean isCyclic(ArrayList<Edge>[] graph){
       
        boolean visited[] = new boolean[graph.length];
        
        for(int v = 0 ; v < graph.length ; v++){
            if(!visited[v]){
                 // mark all vtces of the component & check isCyclic    
                if(isCyclic(graph,visited,v)){
                     // graph is cyclic
                    return true;
                }
            }
        }
        
        // graph is not cyclic
        return false;
    }
    public static boolean isCyclic(ArrayList<Edge>[] graph,boolean visited[],int src){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(src);
        
        while(queue.size() > 0){
            int vtx = queue.remove();
            
            if(!visited[vtx]){
                // mark 
                visited[vtx] = true;
                
                // add unvisited nbrs 
                for(Edge e : graph[vtx]){
                    if(!visited[e.nbr]){
                        queue.add(e.nbr);
                    }
                }
            }else {
                // component is cyclic
                return true;
            }
        }
        // component is non-cyclic
        return false;
    }

    // IS BIPARTITE ``````````````````````````````````````````````````````````````````````````````````````
    public static class Pair{
        int vtx;
        int level;
        
        Pair(int vtx,int level){
            this.vtx = vtx;
            this.level = level;
        }
    }
    public static boolean isGraphBipartite(ArrayList<Edge>[] graph){
        HashMap<Integer,Integer> visited = new HashMap();
        for(int v = 0 ;v < graph.length ; v++){
            if(!visited.containsKey(v) && isBipartite(graph,visited,v) == false){
                return false;
            }
        }
        return true;
    }
    
    public static boolean isBipartite(ArrayList<Edge>[] graph,HashMap<Integer,Integer>visited
    ,int src){
        
     Queue<Pair> queue = new ArrayDeque<>();
     queue.add(new Pair(src,0));
        
     while(queue.size() > 0){
         Pair tmp = queue.remove();
         
         if(visited.containsKey(tmp.vtx)){
             // already visited --> bipartite check
             if(tmp.level%2 != visited.get(tmp.vtx)%2){
                 return false;
             }
         }else{
             // first visit
             visited.put(tmp.vtx,tmp.level);
         }
         
         for(Edge e: graph[tmp.vtx]){
             if(visited.containsKey(e.nbr) == false){
                 queue.add(new Pair(e.nbr,tmp.level+1));
             }
         }
     }
     
     return true;
    }
}
