public class L001 {
    // HAS PATH ``````````````````````````````````````````````````````````````````````````````````````
    public static boolean hasPath(ArrayList<Edge>[] graph,int src,int dest,boolean []visited){
        if(src == dest){
            return true;
        }
      
        visited[src] = true;
        for(Edge e : graph[src]){
            if(visited[e.nbr] == false){
                boolean nbrHasPath = hasPath(graph,e.nbr,dest,visited);
                if(nbrHasPath == true){
                    return true;
                }
            }
        }
       
        return false;
    }

    // PRINT ALL PATHS ```````````````````````````````````````````````````````````````````````````````````
    public static void printAllPath(ArrayList<Edge>[] graph,int src,int dest,boolean[]visited,String psf){
        if(src == dest){
            // path print
            System.out.println(psf+dest);
            return;
        }
        
        visited[src] = true; // mark
        // move to unvisted nbr
        for(Edge e : graph[src]){
            if(visited[e.nbr] == false){
                // unvisited
                printAllPath(graph,e.nbr,dest,visited,psf+src);
            }
        }
        visited[src] = false; // unmark
    }

    // MULTISOLVER ```````````````````````````````````````````````````````````````````````````````````
    public static void multisolver(ArrayList<Edge>[] graph,int src,int dest,int criteria,int k){
        boolean[] visited = new boolean[graph.length];
        multisolverHelper(graph, src, dest, visited, criteria, k,"", 0);
        
        System.out.println("Smallest Path = " + spath + "@" + spathwt);
        System.out.println("Largest Path = " + lpath + "@" + lpathwt);
        System.out.println("Just Larger Path than " + criteria + " = " + cpath + "@" + cpathwt);
        System.out.println("Just Smaller Path than " + criteria + " = " + fpath + "@" + fpathwt);
        System.out.println(k + "th largest path = " + pq.peek().psf + "@" + pq.peek().wsf);  
      }
     static String spath;
     static Integer spathwt = Integer.MAX_VALUE;
     static String lpath;
     static Integer lpathwt = Integer.MIN_VALUE;
     static String cpath;
     static Integer cpathwt = Integer.MAX_VALUE;
     static String fpath;
     static Integer fpathwt = Integer.MIN_VALUE;
     static PriorityQueue<Pair> pq = new PriorityQueue<>();
     public static void multisolverHelper(ArrayList<Edge>[] graph, int src, int dest,
     boolean[] visited, int criteria, int k, String psf, int wsf) {
      if(src == dest){
          // path found
          psf += dest;
          // System.out.println(psf+" @ " + wsf);
          
          if(wsf > lpathwt){
              // wsf is heavier than current lPathwt
              lpathwt = wsf;
              lpath = psf;
          }
          
          if(wsf < spathwt){
              // wsf is lightier than current lPathwt
              spathwt = wsf;
              spath = psf;
          }
          
          if(wsf > criteria && wsf < cpathwt){
              // ceil
                  cpathwt = wsf;
                  cpath = psf;
          }
          
          if(wsf < criteria && wsf > fpathwt){
              // floor
                  fpathwt = wsf;
                  fpath = psf;
          }
          
          // directly put k elements
          if(pq.size() < k){
              pq.add(new Pair(wsf,psf));
          }else if(wsf > pq.peek().wsf){
              // selection process
              pq.remove();
              pq.add(new Pair(wsf,psf));
          }
          return;
      }   

      visited[src] = true;
      
      for(Edge e : graph[src]){
          if(!visited[e.nbr]){
              // unvisited nbr 
              multisolverHelper(graph,e.nbr,dest,visited,criteria,k,psf+src,wsf+e.wt);
          }
      }
      
      visited[src] = false; // unmark --> for all possiblites
      
     }
}
