package myproject.MergeTool;

import java.io.File;

public class MergeToolMain {

	public static void main(String[] args) {
		File file1 = new File("testfiles/file1.txt");
		File file2 = new File("testfiles/file2.txt");
		MergeTool mt = new MergeTool(file1, file2);
		
		mt.compare();
	}

}
