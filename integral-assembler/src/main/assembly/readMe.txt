
#########################################################################
####### to run the hsqldb, integral-solr, integral-mystic, vufind########
#########################################################################

hsqldb
---------
cd /home/ubuntu/workspace/integral-assembler-1.0-SNAPSHOT-distribution/integral-assembler-1.0-SNAPSHOT/hsqldb/bin
./runServer.sh

./shutdownServer.sh


integral-solr, integral-mystic
----------------------------------
cd /home/ubuntu/workspace/integral-assembler-1.0-SNAPSHOT-distribution/integral-assembler-1.0-SNAPSHOT/bin
./startup.sh

./shutdown.sh


vufind
--------------------
cd /home/ubuntu/workspace/vufind6
./phing.sh -Dstartsolr=false startup

./phing.sh -Dstartsolr=false shutdown
git reset --hard



url
---------------------------------
http://vufind.php.ubuntu:8080/integral-solr
http://vufind.php.ubuntu:8080/integral-mystic
http://vufind.php.ubuntu/vufind


curl for index
-----------------------------
curl -X POST -H "Content-Type: application/json" --data "@C:\Users\LSN\Desktop\Utility\curl-7.23.1-win64-ssl-sspi\marc.json" http://vufind.php.ubuntu:8080/integral-mystic/rest/services/cataloguing2/marc

curl -X PUT -H "Content-Type: application/json" --data "@C:\Users\LSN\Desktop\Utility\curl-7.23.1-win64-ssl-sspi\marc.json" http://vufind.php.ubuntu:8080/integral-mystic/rest/services/cataloguing2/marc/finalize/1







