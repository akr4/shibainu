==================
Shibainu
==================
Shibainu is a template for Java web application. It utilizes:

- Maven
- Jetty (as app server for dev time)
- SpringMVC
- Spring Data (JDBC) + QueryDSL
- Jackson (for API)
- Handlebars

==================
setup dev database
==================
createuser -DRS shibainu
createdb -EUTF-8 -Oshibainu shibainu


==================
Run
==================
mvn -Dlog.highlight=true -Dapp.env=dev jetty:run

