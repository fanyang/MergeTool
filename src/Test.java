import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Test {
	
	private ArrayList<String> array1 = new ArrayList<>();
	private ArrayList<String> array2 = new ArrayList<>();
	
	public Test(String file1, String file2) throws Exception{
		
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
	
	public void compare() {
		
		int[][] table = new int[array1.size() + 1][array2.size() + 1];
		
		table[0][0] = 0;
		for (int i = 1; i < array2.size() + 1; i++) table[0][i] = i;
		for (int i = 1; i < array1.size() + 1; i++) table[i][0] = i;
		

		for (int i = 1; i < array2.size() + 1; i++) {
			for (int j = 1; j < array1.size() + 1; j++) {
				int min = Math.min(table[j][i-1] + 1, table[j-1][i] + 1);
				min = Math.min(min, table[j-1][i-1] 
						+ (array2.get(i-1).equals(array1.get(j-1)) ? 0 : 1)
						);
				table[j][i] = min;
			}
		}
		
		System.out.println(array1);
		System.out.println(array2);
		for (int[] arr : table) {
			System.out.println(Arrays.toString(arr));
		}
	}

	public static void main(String[] args) throws Exception{
		String file1 = "testfiles/file1.txt";
		String file2 = "testfiles/file2.txt";
		new Test(file1, file2).compare();
	}

}
