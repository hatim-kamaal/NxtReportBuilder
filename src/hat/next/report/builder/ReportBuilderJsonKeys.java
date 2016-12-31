package hat.next.report.builder;

public interface ReportBuilderJsonKeys {
	
	String DRIVER = "driver";
	String DB_URL = "db-url";
	String DB_USER = "db-user";
	String DB_PASSWORD = "db-pwd";
	String REPORT_FORMAT = "report-format";
	String REPORT_DIR = "report-dir";
	String REPORT_FILE = "report-file";
	String OUTPUT_DIR = "output-dir";
	String STATIC_IMAGE_DIR = "static-image-dir";
	//String DYNAMIC_CHART_DIR = "dynamic-chart-dir";
	String PARAMS = "params";
	String PARAM_KEY = "key";
	String PARAM_TYPE = "type";
	String PARAM_VALUE = "value";
	String PARAM_FORMAT = "format";
	String QUERY_TIMEOUT = "query-timeout";
	
	
	
	
	String DEFAULT_VAL_DRIVER = "org.postgresql.Driver";
	String DEFAULT_VAL_URL = "jdbc:postgresql://localhost:5432/nextreport";
	String DEFAULT_VAL_REPORT_FORMAT = "PDF";
	String DEFAULT_QUERY_TIMEOUT = "180";
	String DEFAULT_VAL_BLANK = "";
	
	String HTML_REPORT_FORMAT = "HTML";
	
	
	
}
