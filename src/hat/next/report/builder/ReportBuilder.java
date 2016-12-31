package hat.next.report.builder;

import hat.next.report.builder.util.ReportBuilderUtil;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import ro.nextreports.engine.Report;
import ro.nextreports.engine.ReportRunner;
import ro.nextreports.engine.chart.Chart;
import ro.nextreports.engine.util.ReportUtil;

/**
 * ReportBuilder builds a report based on information provided
 * via input.json file.  
 * 
 * @author
 */
public class ReportBuilder {
	/**
	 * Object to hold ReportBuilder bean (Input values)
	 */
	private ReportBuilderBean bean;

	/**
	 * Object to hold output stream, so later stream can be closed.
	 */
	protected OutputStream output = null;
	
	/**
	 * Startup method.. 
	 */
	public static void main(String[] args) {
		String inputFile = "input.json";
		if (null == args || args.length == 0) {
			System.out.println("Oops! No input file path provided. Searching default input.json at bin directory.");
		} else {
			inputFile = args[0];
		}
		new ReportBuilder(inputFile);
	}

	/**
	 * 
	 * @param arg
	 */
	public ReportBuilder(String arg) {
		Connection connection = null;
		try {
			String jsonString = readInputFile(arg);

			bean = new ReportBuilderInputParser(URLDecoder.decode(jsonString,
					"UTF-8")).getBean();

			connection = getConnection();
			
			String reportFile = bean.getReportDir() + File.separator
					+ bean.getReportFile();
			File reports = new File(reportFile);
			if (reports.isFile()) {
				makeReport(connection, reports);
			} else if (reports.isDirectory()) {
				for (File rpt : reports.listFiles()) {
					makeReport(connection, rpt);
				}
			} else {
				throw new RuntimeException("Can't find the report ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private String readInputFile(String inputFile) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				inputFile));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			sb.append(line);
		}
		bufferedReader.close();
		return sb.toString();
	}

	/**
	 * 
	 * @param conn
	 * @param f
	 */
	public void makeReport(Connection conn, File f) {
		try {
			// Load the report.
			Report report = ReportUtil.loadReport(new FileInputStream(f));
			
			List<Chart> charts = ReportUtil.getDetailCharts(report.getLayout());
			for( Chart c : charts ) {
				System.out.println(c.getName());
			}
			
			ReportBuilderUtil.copyImages(ReportUtil.getStaticImages(report) , bean.getStaticImageDir(), getImageDir());
			
			// Create output file stream.
			output = getOutputStream(f);
			// run the report
			runReport(conn, report);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream(output);
		}
	}

	/**
	 * 
	 * @return
	 */
	private String getImageDir() {
		String destDir = ".";
		if( ReportBuilderJsonKeys.HTML_REPORT_FORMAT.equalsIgnoreCase(bean.getReportFormat()) ) {
			destDir = bean.getOutputDir();
		}
		
		return destDir;
	}
	
	protected void runReport(Connection connection, Report report)
			throws Exception {
		System.out.println("Run report...");
		long time = System.currentTimeMillis();

		ReportRunner runner = new ReportRunner();
		
		runner.setChartImagePath(getImageDir());
		
		runner.setConnection(connection);
		runner.setReport(report);
		runner.setQueryTimeout(bean.getQueryTimeout()); // optional
		runner.setParameterValues(bean.getParameters()); // optional (for no
															// parameters)
		runner.setFormat(bean.getReportFormat());
		runner.run(output);
		time = System.currentTimeMillis() - time;
		System.out.println("Done in " + time + " ms");
	}
	
	public Connection getConnection() throws Exception {
		Class.forName(bean.getDriver());
		return DriverManager.getConnection(bean.getUrl(), bean.getUser(),
				bean.getPassword());
	}

	protected OutputStream getOutputStream(File f) throws IOException {
		String reportFormat = ReportBuilderUtil.toLowerCase( bean.getReportFormat() );
		String outputFileName = f.getName().replace(".report", "." +reportFormat);
		File outputDir = new File(bean.getOutputDir());
		outputDir.mkdirs();

		File file = new File(outputDir.getAbsolutePath() + File.separator
				+ outputFileName);

		return new FileOutputStream(file);
	}

	public static void closeConnection(Connection connection) {
		if (connection == null) {
			return;
		}

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeStream(Closeable stream) {
		if (stream == null) {
			return;
		}

		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
