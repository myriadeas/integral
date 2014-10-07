mvn tomcat7:run -Djavax.net.ssl.trustStorePassword=password -Djavax.net.ssl.trustStore=C:\Users\hutingung\Development\workspace\myriadeas\integral-1.1-SNAPSHOT\integral-mystic\integral-mystic-web\server.jks

keytool -import -trustcacerts -keystore server.jks -storepass password -alias dev.cas.myriadeas.com.my -file dev.cas.myriadeas.com.my.crt -noprompt

#create ssl for tomcat
keytool -genkey -alias localhost -keyalg RSA -sigalg SHA256withRSA -keypass password -storepass password -keystore server.jks -dname "cn=localhost,ou=localhost,o=localhost" -validity 3650


steps to get authentication token for integral-web2
1. go to http://localhost/integral-mystic/secured.jsp
2. login using username: SYSTEM, password: SYSTEM
3. copy proxyTicket=<ticketValue> and save into integral-web2/app/token.json
4. edit integral-web2/app/scripts/main.js (change $http.get('token/') to $http.get('token.json');

vufind
remove unused hanlder 

<requestHandler name="/browse" class="org.vufind.solr.handler.BrowseRequestHandler">
    <str name="authIndexPath">${solr.solr.home:./solr}/authority/index</str>
    <str name="bibIndexPath">${solr.solr.home:./solr}/biblio/index</str>

    <!-- These definitions should match the field names used in the authority index. -->
    <str name="preferredHeadingField">heading</str>
    <str name="useInsteadHeadingField">use_for</str>
    <str name="seeAlsoHeadingField">see_also</str>
    <str name="scopeNoteField">scope_note</str>


    <str name="sources">topic,author,title,lcc,dewey,hierarchy</str>

    <lst name="topic">
      <str name="DBpath">${solr.solr.home:./solr}/alphabetical_browse/topic_browse.db</str>
      <str name="field">topic_browse</str>
    </lst>

    <lst name="author">
      <str name="DBpath">${solr.solr.home:./solr}/alphabetical_browse/author_browse.db</str>
      <str name="field">author_browse</str>
    </lst>

    <lst name="title">
      <str name="DBpath">${solr.solr.home:./solr}/alphabetical_browse/title_browse.db</str>
      <str name="field">title_fullStr</str>
    </lst>

    <lst name="lcc">
      <str name="DBpath">${solr.solr.home:./solr}/alphabetical_browse/lcc_browse.db</str>
      <str name="field">callnumber-a</str>
    </lst>

    <lst name="dewey">
      <str name="DBpath">${solr.solr.home:./solr}/alphabetical_browse/dewey_browse.db</str>
      <str name="field">dewey-raw</str>
      <str name="ignoreDiacritics">yes</str>
    </lst>

    <lst name="hierarchy">
      <str name="DBpath">${solr.solr.home:./solr}/alphabetical_browse/hierarchy_browse.db</str>
      <str name="field">hierarchy_browse</str>
    </lst>
  </requestHandler>
