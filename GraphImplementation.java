import java.util.ArrayList;
import java.util.List;

// the 
public class GraphImplementation implements Graph
{
	int[][] graph;
	int V;

	// take the number of vertices and initiate the adjacency list(graph) with all its content to be 0
	GraphImplementation(int vertices)
	{
		V = vertices;
		graph = new int[V][V];
		for(int i = 0; i < V; i++)
		{
			for(int j = 0; j < V; j++)
			{
				graph[i][j] = 0;
			}
		}
	}

	// add a directed edge from src to tar to the graph
	public void addEdge(int src, int tar)
	{
		graph[src][tar] = 1;
	}

	// find the array of vertices that is the destination that the vertex connects to
	public int[] neighbors(int vertex)
	{
		int count = 0;
		for(int i = 0; i < V; i++)
		{
			if(graph[vertex][i] == 1)
			{
				count++;
			}
		}
		int[] temp = new int[count];
		int startpoint = 0;
		for(int j = 0; j < V; j++)
		{
			if(graph[vertex][j] == 1)
			{
				temp[startpoint] = j;
				startpoint++;
			}
		}

		return temp;
	}

	// this is the topological sort, return a list of vertices that the destination is always after the startpoint
	public List<Integer> topologicalSort()
	{
		ArrayList<Integer> schedule = new ArrayList<Integer>();
		int[] incident = new int[V];
		for(int i = 0; i < V; i++)
		{
			incident[i] = 0;
		}

		// count the incidence(the number of times that a vertex is a destination) of each vertex
		for(int k = 0; k < V; k++)
		{
			for(int j = 0; j < V; j++)
			{
				incident[k] += graph[j][k];
			}
		}

		for(int q = 0; q < V; q++)
		{
			// find the next vertex
			int next_vertex = next_vertex(incident);
			// if the vertex is -1 which means there is a circle
			if(next_vertex == -1)
			{
				System.out.println("Ah, find a circle!");
				return schedule;
			}
			// add the vertex to the schedule(list)
			schedule.add(next_vertex);
		}

		return schedule;
	}

	// the function to find the next vertex(if its incident is 0, then pick it, else return -1)
	int next_vertex(int[] incident)
	{
		int temp;
		for(int i = 0; i < V; i++)
		{
			if(incident[i] == 0)
			{
				incident[i] = -1;
				for(int j = 0; j < V; j++)
				{
					if(graph[i][j] == 1)
					{
						incident[j]--;
					}
				}
				return i;
			}
		}

		return -1;
	}










}
