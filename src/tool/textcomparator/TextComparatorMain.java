package tool.textcomparator;

import java.io.File;

import org.apache.commons.cli.*;

public class TextComparatorMain {

	public static void main(String[] args) {
		
		Options options = new Options();
		
		Option sCommand = new Option("s", "Compare two strings.");
		sCommand.setArgs(2);
		sCommand.setArgName("string1 string2");
		options.addOption(sCommand);
		
		Option fCommand = new Option("f", "Compare two files.");
		fCommand.setArgs(2);
		fCommand.setArgName("file1 file2");
		options.addOption(fCommand);
		
		options.addOption("h", "help",  false, "Print help for the command.");
		
		CommandLineParser parser = new BasicParser();
        CommandLine commandLine = null;
        
        HelpFormatter formatter = new HelpFormatter();
        String formatstr = "java TextComparatorMain [option] [target1 target2]";
        
        try {
        	commandLine = parser.parse( options, args );
        } catch (ParseException e) {
            formatter.printHelp( formatstr, options );
            return;
        }
        
        if (commandLine.hasOption("h")) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatstr, "", options, "");
            return;
        }
        
        String str1, str2;
		File file1, file2;
		TextComparator tc = null;
        
        if (commandLine.hasOption("s")) {
        	String[] strings = commandLine.getOptionValues("s");
        	str1 = strings[0];
        	str2 = strings[1];
        	tc = new TextComparator(str1, str2);
        }

        if (commandLine.hasOption("f")) {
        	String[] strings = commandLine.getOptionValues("f");
        	file1 = new File(strings[0]);
        	file2 = new File(strings[1]);
        	tc = new TextComparator(file1, file2);
        }
		
		tc.compare();
		new TextComparatorGui(tc);
	}

}
