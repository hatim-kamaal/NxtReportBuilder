package hat.next.report.builder.util;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

public final class ReportBuilderUtil {
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String toUpperCase(String text) {
		StringBuilder ab = new StringBuilder();
		int len = text.length();
		for( int i = 0; i < len; i++ ) {
			ab.append( Character.toUpperCase(text.charAt(i)) );
		}
		return ab.toString();
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String toLowerCase(String text) {
		StringBuilder ab = new StringBuilder();
		int len = text.length();
		for( int i = 0; i < len; i++ ) {
			ab.append( Character.toLowerCase(text.charAt(i)) );
		}
		return ab.toString();
	}
	
	/**
	 * 
	 * @param images
	 * @param from
	 * @param to
	 */
	public static void copyImages(List<String> images, String from, String to) throws Exception {
		File destFile = null;
		File sourceFile = null;
		for (String image : images) {
			sourceFile = new File(from + File.separator + image);
			destFile = new File(to + File.separator + image);
			FileUtils.copyFile(sourceFile, destFile);
		}
	}

}
