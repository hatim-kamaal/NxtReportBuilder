package hat.next.report.builder;

import hat.next.report.builder.util.ReportBuilderUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

/**
 * 
 * @author Hatim Kamal
 *
 */
public class ReportBuilderInputParser implements ReportBuilderJsonKeys{
	private ReportBuilderBean bean;

	/**
	 * 
	 * @param json
	 * @throws Exception
	 */
	public ReportBuilderInputParser(String jsonString) throws Exception {

		if (jsonString == null || jsonString.trim().length() == 0) {
			throw new RuntimeException("No input recieved.");
		}

		JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
		JSONObject inputObject = (JSONObject) jsonParser.parse(jsonString);

		bean = new ReportBuilderBean();
		bean.setDriver(getStringOrDefault(inputObject, DRIVER,
				DEFAULT_VAL_DRIVER));
		bean.setUrl(getStringOrDefault(inputObject, DB_URL,
				DEFAULT_VAL_URL));
		bean.setUser(getStringOrDefault(inputObject, DB_USER, DEFAULT_VAL_BLANK));
		bean.setPassword(getStringOrDefault(inputObject, DB_PASSWORD, DEFAULT_VAL_BLANK));
		bean.setReportDir(getStringOrDefault(inputObject, REPORT_DIR, DEFAULT_VAL_BLANK));
		bean.setReportFile(getStringOrDefault(inputObject, REPORT_FILE, DEFAULT_VAL_BLANK));
		bean.setOutputDir(getStringOrDefault(inputObject, OUTPUT_DIR, DEFAULT_VAL_BLANK));
		String format = getStringOrDefault(inputObject, REPORT_FORMAT,DEFAULT_VAL_REPORT_FORMAT);
		bean.setReportFormat(ReportBuilderUtil.toUpperCase(format));
		bean.setStaticImageDir(getStringOrDefault(inputObject, STATIC_IMAGE_DIR, DEFAULT_VAL_BLANK));
		
		bean.setQueryTimeout( Integer.parseInt( getStringOrDefault(inputObject, QUERY_TIMEOUT, DEFAULT_QUERY_TIMEOUT ) ) );
		
		bean.setParameters(getParams((JSONArray) inputObject.get(PARAMS)));
	}
	
	
	
	
	/**
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getParams(JSONArray params) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		if (null == params) {
			return null;
		}

		int numOfParams = params.size();

		JSONObject param = null;
		for (int i = 0; i < numOfParams; i++) {
			param = (JSONObject) params.get(i);
			parameters.put(param.getAsString(PARAM_KEY), getValue(param));
		}

		return parameters;
	}

	/**
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	private Object getValue(JSONObject param) throws Exception {
		String key = param.getAsString(PARAM_KEY);
		String type = param.getAsString(PARAM_TYPE);
		String format = param.getAsString(PARAM_FORMAT);
		Object value = param.get(PARAM_VALUE);

		if (null == value) {
			throw new RuntimeException("No value provided for key - " + key);
		}

		return getValue(type, value, format);
	}

	/**
	 * 
	 * @param type
	 * @param value
	 * @param format
	 * @return
	 * @throws Exception
	 */
	private Object getValue(String type, Object value, String format)
			throws Exception {
		boolean isArray = false;
		int size = 0;
		JSONArray valueArray = null;
		String valueString = null;
		if (value instanceof JSONArray) {
			isArray = true;
			valueArray = (JSONArray) value;
			size = valueArray.size();
		} else {
			valueString = value.toString();
		}

		switch (type) {
		case "Boolean":
			if (isArray) {
				Boolean[] b1 = new Boolean[size];
				for (int i = 0; i < size; i++) {
					b1[i] = new Boolean((String) valueArray.get(i));
				}
				return b1;
			} else {
				return new Boolean(valueString);
			}
		case "Date":
			SimpleDateFormat sdf = new SimpleDateFormat(
					null != format ? format : "dd/mm/yyyy");
			if (isArray) {
				Date[] d1 = new Date[size];
				for (int i = 0; i < size; i++) {
					d1[i] = sdf.parse(valueArray.get(i).toString());
				}
				return d1;
			} else {
				return sdf.parse(valueString);
			}
		case "Integer":
			if (isArray) {
				Integer[] i1 = new Integer[size];
				for (int i = 0; i < size; i++) {
					i1[i] = Integer.parseInt(valueArray.get(i).toString());
				}
				return i1;
			} else {
				return new Integer(valueString);
			}
		case "Double":
			if (isArray) {
				Double[] d2 = new Double[size];
				for (int i = 0; i < size; i++) {
					d2[i] = Double.parseDouble(valueArray.get(i).toString());
				}
				return d2;
			} else {
				return new Double(valueString);
			}
		case "Long":
			if (isArray) {
				Long[] l1 = new Long[size];
				for (int i = 0; i < size; i++) {
					l1[i] = Long.parseLong(valueArray.get(i).toString());
				}
				return l1;
			} else {
				return new Long(valueString);
			}
		default:
			if (isArray) {
				String[] s1 = new String[size];
				for (int i = 0; i < size; i++) {
					s1[i] = valueArray.get(i).toString();
				}
				return s1;
			} else {
				return valueString;
			}
		}
	}

	/**
	 * 
	 * @param inputObject
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	private String getStringOrDefault(JSONObject inputObject, String key,
			String defaultValue) {
		String value = inputObject.getAsString(key);
		return null == value ? defaultValue : value;
	}

	/**
	 * 
	 * @return
	 */
	public ReportBuilderBean getBean() {
		return bean;
	}

}
