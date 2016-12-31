Prerequisite
-------------
Java 1.8

How to run
-----------------
java -jar ReportBuilder-0.1.jar <input.json file path>
Example: java -jar ReportBuilder-0.1.jar input.json

JSON Input
------------------
{
	"driver":"org.postgresql.Driver", 
	"db-url":"jdbc:postgresql://localhost:5432/nextreport",
	"db-user":"hatim",
	"db-pwd":"hatim",
	"report-format":"PDF",
	"report-dir":"reports",
	"report-file":"GetFirstName.report",
	"output-dir":"output",
	"static-image-dir":"images",
	"query-timeout":"60",
	"params":
		[
			{
				"key":"name", 
				"type":"String", 
				"value":"Hatim"
			},
			{
				"key":"id", 
				"type":"Integer", 
				"value":"1"
			},
			{
				"key":"John", 
				"type":"Integer", 
				"value":[1,2,3]
			}
		]
}	

