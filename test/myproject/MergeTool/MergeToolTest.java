package myproject.MergeTool;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MergeToolTest {

	@Test
	public void testMergeToolFileFile() {
		File file1 = new File("testfiles/file1.txt");
		File file2 = new File("testfiles/file2.txt");
		MergeTool mt = new MergeTool(file1, file2);
		
		mt.compare();
		List<String> list1 = mt.getList1();
		List<String> list2 = mt.getList2();
		String[] arr1 = {"s", "-", "-", "u", "*n", "d", "a", "y"};
		String[] arr2 = {"s", "+a", "+t", "u", "*r", "d", "a", "y"};
		List<String> resultList1 = Arrays.asList(arr1);
		List<String> resultList2 = Arrays.asList(arr2);
		
		assertThat(list1, is(resultList1));
		assertThat(list2, is(resultList2));
	}

	@Test
	public void testMergeToolStringString() {
		MergeTool mt = new MergeTool("sunday", "saturday");
		
		mt.compare();
		List<String> list1 = mt.getList1();
		List<String> list2 = mt.getList2();
		String[] arr1 = {"s", "-", "-", "u", "*n", "d", "a", "y"};
		String[] arr2 = {"s", "+a", "+t", "u", "*r", "d", "a", "y"};
		List<String> resultList1 = Arrays.asList(arr1);
		List<String> resultList2 = Arrays.asList(arr2);
		
		assertThat(list1, is(resultList1));
		assertThat(list2, is(resultList2));
	}


}
