package myproject.MergeTool;

import java.io.*;
import java.util.*;


public class MergeTool {
	
	private ArrayList<String> array1 = new ArrayList<>();
	private ArrayList<String> array2 = new ArrayList<>();
	
	private LinkedList<String> list1 = new LinkedList<>();
	private LinkedList<String> list2 = new LinkedList<>();
	private LinkedList<ChangeState> stateList = new LinkedList<>();
	
	
	public MergeTool(File file1, File file2){
		
		try (
				BufferedReader br1 = new BufferedReader(new FileReader(file1));
				BufferedReader br2 = new BufferedReader(new FileReader(file2));
			) {
			String str;
			
			while ((str = br1.readLine()) != null) array1.add(str);
			while ((str = br2.readLine()) != null) array2.add(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public MergeTool(String str1, String str2){
		
		for (int i = 0; i < str1.length(); i++) {
			array1.add(String.valueOf(str1.charAt(i)));
		}

		for (int i = 0; i < str2.length(); i++) {
			array2.add(String.valueOf(str2.charAt(i)));
		}

	}	
	
	
	public void compare() {
		
		//build table
		Node[][] table = new Node[array1.size() + 1][array2.size() + 1];
		
		table[0][0] = new Node(0, null);
		for (int i = 1; i < array2.size() + 1; i++) {
			table[0][i] = new Node(i, LastPosition.LEFT);
		}
		for (int i = 1; i < array1.size() + 1; i++) {
			table[i][0] = new Node(i, LastPosition.UP);
		} 
		
		for (int i = 1; i < array2.size() + 1; i++) {
			for (int j = 1; j < array1.size() + 1; j++) {
				
				int left = table[j][i-1].getValue() + 1;
				int up = table[j-1][i].getValue() + 1;
				int leftup = table[j-1][i-1].getValue() 
						+ (array2.get(i-1).equals(array1.get(j-1)) ? 0 : 1);
				
				int min = left;
				if (up < min) min = up;
				if (leftup < min) min = leftup;
				
				if (min == left) {
					table[j][i] = new Node(left, LastPosition.LEFT);
				} else if (min == up) {
					table[j][i] = new Node(up, LastPosition.UP);
				} else {
					table[j][i] = new Node(leftup, LastPosition.LEFT_UP);
				}
				
			}
		}
		
		
		//build state lists
		

		int x = array2.size();
		int y = array1.size();
		
		do {
			Node currentNode = table[y][x];
			
			switch (currentNode.getLastPosition()) {
			case UP:
				list1.push(array1.get(x-1));
				list2.push("");
				stateList.push(ChangeState.DELETE);
				y--;
				break;
			case LEFT:
				list1.push("");
				list2.push(array2.get(x-1));
				stateList.push(ChangeState.INSERT);
				x--;
				break;
			case LEFT_UP:
				stateList.push(array1.get(y-1).equals(array2.get(x-1)) 
						? ChangeState.UNMODIFIED
						: ChangeState.UPDATE);
				list1.push(array1.get(y-1));
				list2.push(array2.get(x-1));
				x--;
				y--;
				break;
			default:
				break;
			}
			
		} while (x != 0 || y != 0);
		
	}
	
	
	/**
	 * nodes in table
	 * @author think
	 *
	 */
	private class Node {
		
		private int value;
		private LastPosition lastPosition;

		public Node(int value, LastPosition lastPosition) {
			this.value = value;
			this.lastPosition = lastPosition;
		}

		public LastPosition getLastPosition() {
			return this.lastPosition;
		}

		public int getValue() {
			return this.value;
		}
		
		@Override
		public String toString() {
			return String.valueOf(value);
		}
		

	}
	
	
	/**
	 * node's last position
	 * @author think
	 *
	 */
	private enum LastPosition {
		LEFT, UP, LEFT_UP
	}


	public List<String> getList1() {
		return list1;
	}

	public List<String> getList2() {
		return list2;
	}

	public List<ChangeState> getStateList() {
		return stateList;
	}

}
