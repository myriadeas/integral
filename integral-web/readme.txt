#Package testing profile. It is meaningless to test development in integral-web because you always need cas
#package whole integral project
mvn install -Dyo.test.skip=true -P testing
#Run integral-web from war package because we need grunt to execute during package
mvn tomcat7:run-war -Djavax.net.ssl.trustStorePassword=password -Djavax.net.ssl.trustStore=C:\Users\hutingung\Development\workspace\myriadeas\integral-1.1-SNAPSHOT\integral-mystic\integral-mystic-web\server.jks -Dyo.test.skip=true
