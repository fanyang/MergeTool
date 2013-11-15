package tool.textcomparator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tool.textcomparator.TextComparator;

public class TextComparatorTest {

	@Test
	public void testTextComparatorFile() {
		File file1 = new File("testfiles/file1.txt");
		File file2 = new File("testfiles/file2.txt");
		TextComparator tc = new TextComparator(file1, file2);
		
		tc.compare();
		List<String> list1 = tc.getList1();
		List<String> list2 = tc.getList2();
		String[] arr1 = {"S", "", "", "u", "n", "d", "a", "y"};
		String[] arr2 = {"S", "a", "t", "u", "r", "d", "a", "y"};
		List<String> resultList1 = Arrays.asList(arr1);
		List<String> resultList2 = Arrays.asList(arr2);
		
		assertThat(list1, is(resultList1));
		assertThat(list2, is(resultList2));
	}

	@Test
	public void testTextComparatorString() {
		TextComparator tc = new TextComparator("Sunday", "Saturday");
		
		tc.compare();
		List<String> list1 = tc.getList1();
		List<String> list2 = tc.getList2();
		String[] arr1 = {"S", "", "", "u", "n", "d", "a", "y"};
		String[] arr2 = {"S", "a", "t", "u", "r", "d", "a", "y"};
		List<String> resultList1 = Arrays.asList(arr1);
		List<String> resultList2 = Arrays.asList(arr2);
		
		assertThat(list1, is(resultList1));
		assertThat(list2, is(resultList2));
	}


}
