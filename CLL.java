import java.io.*;
public class CLL{
	Node rear;
	static class Node{
		int val;
		Node link;
		Node(int d){
			this.val=d;
			this.link=null;
		}
	}
	public static CLL insert(CLL list,int d){
		Node new_node=new Node(d);
		if(list.rear==null){
			list.rear=n_node;
			n_node.link=list.rear;
		}
		else{
			n_node.link=list.rear.link;
			list.rear.link=n_node;
			list.rear=n_node;
		}

	}
	public static void delete_back(CLL list){
		if(list.rear==null){
			System.out.println("Underflow");

		}
		else if(list.rear.link==list.rear){
			list.rear=null
		}
		else{
			
		}

		}
	}
}