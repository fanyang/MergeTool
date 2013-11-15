Text Comparator
================
Compare two text files or two string.<br />
It shows the minimum modification of different versions of text files. 

### Main technique use
* GUI display the result.
* Apache commons cli.
* Levenshtein distance.
* try-with-resources

### Example
		TextComparator tc = new TextComparator("sunday", "saturday");
		List<ChangeState> stateList = tc.getStateList();