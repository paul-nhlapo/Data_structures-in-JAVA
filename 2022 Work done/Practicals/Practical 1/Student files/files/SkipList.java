//Paul Nhlapo u18108378
import java.util.Random;

@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<? super T>>
{

	public int maxLevel;
	public SkipListNode<T>[] root;
	private int[] powers;
	private Random rd = new Random();

	SkipList(int i)
	{
		maxLevel = i;
		root = new SkipListNode[maxLevel];
		powers = new int[maxLevel];
		for (int j = 0; j < i; j++)
			root[j] = null;
		choosePowers();
		rd.setSeed(1230456789);
	}

	SkipList()
	{
		this(4);
	}

	public void choosePowers()
	{
		powers[maxLevel-1] = (2 << (maxLevel-1)) - 1;
		for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++)
			powers[i] = powers[i+1] - (2 << j);
	}

	public int chooseLevel()
	{
		int i, r = Math.abs(rd.nextInt()) % powers[maxLevel-1] + 1;
		for (i = 1; i < maxLevel; i++)
		{
			if(r < powers[i])
				return i-1;
		}
		return i-1;
	}

	public boolean isEmpty()
	{
		//Your code goes here
		return root[0] == null;
	}

	public void insert(T key)
	{
		//Your code goes here
		SkipListNode<T>[] curr = new SkipListNode[maxLevel];
		SkipListNode<T>[] prev = new SkipListNode[maxLevel];
		SkipListNode<T> newNode;
		int Level, i;
		curr[maxLevel-1] = root[maxLevel-1];
		prev[maxLevel-1] = null;
		for(Level=maxLevel-1; Level>=0; Level--) {
			while ( curr[Level] != null && curr[Level].key.compareTo(key)<0){
				prev[Level] = curr[Level];
				curr[Level] = curr[Level].next[Level];
			}
			if (curr[Level] != null && curr[Level].key.equals(key))
				return;
			if (Level>0)
					if (prev[Level] == null){
						curr[Level-1] = root[Level-1];
						prev[Level-1] = null;
					}
			else{
				curr[Level-1] = prev[Level].next[Level-1];
				prev[Level-1] = prev[Level];
			}
		}
		
		Level = chooseLevel();
		newNode = new SkipListNode<T>(key, Level+1);
		for (i = 0; i<=Level; i++) {
			newNode.next[i] = curr[i];
			if (prev[i] == null)
				root[i] = newNode;
			else prev[i].next[i] = newNode;
		}
	}	

	public T first()
	{
		//Your code goes here
		return (root[0].key);
	}

	public T last()
	{
		//Your code goes here 
		SkipListNode<T>[] curr = new SkipListNode[1];
		curr[0] = root[0];
		while(curr[0].next[0] != null){
			curr[0] = curr[0].next[0];
		}
		return curr[0].key;
	}	

	public T search(T key)
	{
		//Your code goes here
		int Level;
		SkipListNode<T> prev, curr;
		for(Level = maxLevel-1; Level>=0 && root[Level] == null; Level--);
		prev =curr = root[Level];

		while (true){
			if(key.equals(curr.key))
				return curr.key;
			else if (key.compareTo(curr.key)<0){
				if (Level == 0)
					return null;
				else if (curr == root[Level])
					curr = root[--Level];
				else curr = prev.next[--Level];
			}
			else{
				prev = curr;
				if (curr.next[Level] != null)
					curr = curr.next[Level];
				else{
					for (Level--; Level>=0 && curr.next[Level] == null; Level--);
					if (Level>=0)
						curr = curr.next[Level];
					else return null;
				}
			}
		}
	}
}