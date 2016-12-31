package hat.next.report.builder;

import java.util.Map;

public class ReportBuilderBean {
	
	private String reportFormat;
	private String driver;
	private String url;
	private String user;
	private String password;
	private String reportDir;
	private String reportFile;
	private String outputDir;
	private String staticImageDir;
	private Integer queryTimeout;
	private Map<String, Object> parameters;
	
	/**
	 * @return the reportDir
	 */
	public String getReportDir() {
		return reportDir;
	}
	/**
	 * @param reportDir the reportDir to set
	 */
	public void setReportDir(String reportDir) {
		this.reportDir = reportDir;
	}
	/**
	 * @return the reportFile
	 */
	public String getReportFile() {
		return reportFile;
	}
	/**
	 * @param reportFile the reportFile to set
	 */
	public void setReportFile(String reportFile) {
		this.reportFile = reportFile;
	}
	/**
	 * @return the outputDir
	 */
	public String getOutputDir() {
		return outputDir;
	}
	/**
	 * @param outputDir the outputDir to set
	 */
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
	/**
	 * @return the reportFormat
	 */
	public String getReportFormat() {
		return reportFormat;
	}
	/**
	 * @param reportFormat the reportFormat to set
	 */
	public void setReportFormat(String reportFormat) {
		this.reportFormat = reportFormat;
	}
	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}
	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	/**
	 * @return the staticImageDir
	 */
	public String getStaticImageDir() {
		return staticImageDir;
	}
	/**
	 * @param staticImageDir the staticImageDir to set
	 */
	public void setStaticImageDir(String staticImageDir) {
		this.staticImageDir = staticImageDir;
	}
	/**
	 * @return the queryTimeout
	 */
	public Integer getQueryTimeout() {
		return queryTimeout;
	}
	/**
	 * @param queryTimeout the queryTimeout to set
	 */
	public void setQueryTimeout(Integer queryTimeout) {
		this.queryTimeout = queryTimeout;
	}
	
	
}
