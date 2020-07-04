public class L004 {
    
    // SPREAD OF INFECTION ````````````````````````````````````````````````````````````````````````
    public static class Pair{
        int vtx;
        int TOI; // time of infection
        
        Pair(int vtx,int TOI){
            this.vtx = vtx;
            this.TOI = TOI;
        }
    }
    public static int spreadOfInfection(ArrayList<Edge>[] graph,int src, int time){
        boolean[] visited = new boolean[graph.length];
        Queue<Pair> queue = new ArrayDeque<>();
        int count = 0;
        queue.add(new Pair(src,1));
        
        while(queue.size() > 0){
             Pair rem = queue.remove();
             
             if(!visited[rem.vtx]){
                 visited[rem.vtx] = true;
                 count++;
                 for(Edge e : graph[rem.vtx]){
                     if(!visited[e.nbr] && rem.TOI < time){
                         queue.add(new Pair(e.nbr,rem.TOI+1));
                     }
                 }
             }
        }
        return count;
     }
   
    // SHORTEST PATH in terms of WT --> dijkstra's algo ````````````````````````````````````````````````````````
    public static class Pair implements Comparable<Pair>{
        int vtx;
        String psf;
        int wsf;
        
        Pair(int vtx,String psf,int wsf){
            this.vtx = vtx;
            this.psf = psf;
            this.wsf = wsf;
        }
        
        public int compareTo(Pair o){
            return this.wsf - o.wsf;
        }
    }
    
    public static void dijkstra(ArrayList<Edge>[] graph,int src){
        boolean []visited = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src,""+src,0));
        
        while(pq.size() > 0){
            // remove
            Pair rem = pq.remove();
            
            if(!visited[rem.vtx]){
             // mark + print 
                visited[rem.vtx] = true;
               System.out.println(rem.vtx + " via "+rem.psf+" @ "+rem.wsf);
             // add
                 for(Edge e : graph[rem.vtx]){
                     if(!visited[e.nbr]){
                         pq.add(new Pair(e.nbr,rem.psf+e.nbr,rem.wsf+e.wt));
                     }
                 }
            } 
        }
    }

    // MINIMUM WIRE : MINIMUM SPANNING TREE ``````````````````````````````````````````````````````````````````
    public static class MWPair implements Comparable<MWPair>{
        int vtx;
        int pvtx;
        int wt;
        MWPair(int vtx,int pvtx,int wt){
            this.vtx = vtx;
            this.pvtx = pvtx;
            this.wt = wt;
        }
        public int compareTo(MWPair o){
            return this.wt-o.wt;
        }
    }
    public static void MinimumWire(ArrayList<Edge>[] graph){
        PriorityQueue<MWPair> pq = new PriorityQueue<>();
        int src = 0;
        pq.add(new MWPair(src,-1,0));
        boolean[] visited = new boolean[graph.length];
        while(pq.size() > 0){
            MWPair rem = pq.remove();
            
            if(!visited[rem.vtx]){
                visited[rem.vtx] = true;
                
                if(rem.pvtx != -1){
                    System.out.println("["+rem.vtx+"-"+rem.pvtx+"@"+rem.wt+"]");
                }
                
                for(Edge e : graph[rem.vtx]){
                    if(!visited[e.nbr]){
                        pq.add(new MWPair(e.nbr,rem.vtx,e.wt));
                    }
                }
            }
        }
        
    }

    // ORDER OF COMPILATION : DFS ```````````````````````````````````````````````````````````````````````````````
    public static void OrderOFCompilation(ArrayList<Edge>[] graph){
        boolean visited[]= new boolean [graph.length];
        Stack<Integer> st = new Stack<>();
        for(int v = 0; v < graph.length;v++){
            if(visited[v] == false){
                OFC(graph,v,st,visited);
            }
        }
        
        while(st.size() > 0){
            System.out.println(st.pop());
        }
    }
    
    public static void OFC(ArrayList<Edge>[] graph,int src,Stack<Integer> st,boolean visited[]){
        
        visited[src] = true;
        
        for(Edge e: graph[src]){
            if(visited[e.nbr] == false){
                OFC(graph,e.nbr,st,visited);
            }
        }
        
        st.push(src);
        
    }

    // ITERATIVE DFS `````````````````````````````````````````````````````````````````````````````````````````
    public static class DFSPair{
        int vtx;
        String psf;
        DFSPair(int vtx,String psf){
            this.vtx = vtx;
            this.psf = psf;
        }
    }
    public static void itrDFS(ArrayList<Edge>[] graph,int src){
         Stack<DFSPair> st = new Stack<>();
         
         st.push(new DFSPair(src,""+src));
         boolean visited[] = new boolean[graph.length];
         while(st.size() > 0){
             DFSPair rem = st.pop();
             
             if(visited[rem.vtx] == false){
                 visited[rem.vtx] = true;
                 
                 System.out.println(rem.vtx+"@"+rem.psf);
                 
                 for(Edge e : graph[rem.vtx]){
                     if(!visited[e.nbr]){
                         st.push(new DFSPair(e.nbr,rem.psf+e.nbr));
                     }
                 }
             }
         }
    }

    // HAMILTONIAN CYCLE/PATH ````````````````````````````````````````````````````````````````````````````````
    // hamiltonian(graph,src,src,"",new HashSet<Integer>());
    public static void hamiltonian(ArrayList<Edge>[] graph,int osrc,int vtx,String psf,
    HashSet<Integer> visited){
        if(visited.size() == graph.length-1){
            psf = psf + vtx;
            
            boolean cycle = false;
            for(Edge e : graph[vtx]){
                if(e.nbr == osrc){
                    cycle = true;
                    break;
                }
            }
            
            if(cycle){
                System.out.println(psf+"*");    
            }else{
                System.out.println(psf+".");
            }
            
            return;
        }
        visited.add(vtx);// mark    
        for(Edge e: graph[vtx]){
            if(!visited.contains(e.nbr)){
                hamiltonian(graph,osrc,e.nbr,psf+vtx,visited);
            }
        }
        visited.remove(vtx);// unmark
    }
}
