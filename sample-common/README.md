#Shared Resources
------------------

###layout for shared Images, CSS and Java Script, Properties


sampleWebApp
|
---sample-common.jar
|	|
|	+--src/main/java
|	|
|	---src/main/resources
|	|	|
|	|	+--com/sample
|	|	|	|
|	|	|	```messages.properties``
|	|	|	```config.properties``
|	|	|	```log4j.properties``
|	|	|
|	|	---META-INF
|	|		+--fonts
|	|		+--css
|	|		+--js
|	|		+--images
|	|
|	pom.xml
|
---sample-web1.war
|	|	
|	+--src/main/java
|	|	
|	+--src/main/resources
|	|	
|	---src/main/webapp
|	|	|
|	|	+--resources
|	|	+--pages
|	|	|
|	|	---WEB-INF
|	|		|
|	|		beans.xml
|	|		faces-config.xml
|	|		jboss-web.xml
|	|		web.xml
|	|
|	pom.xml
|
---sample-web2.war
|	|	
|	+--src/main/java
|	|	
|	+--src/main/resources
|	|	
|	---src/main/webapp
|	|	|
|	|	+--resources
|	|	+--pages
|	|	|
|	|	---WEB-INF
|	|		|
|	|		beans.xml
|	|		faces-config.xml
|	|		jboss-web.xml
|	|		web.xml
|	|
|	pom.xml
|
pom.xml