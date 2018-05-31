@echo off

rem apollo config db info
set apollo_config_db_url="jdbc:mysql://localhost:3306/ApolloConfigDB?characterEncoding=utf8"
set apollo_config_db_username="root"
set apollo_config_db_password="123456"

rem apollo portal db info
set apollo_portal_db_url="jdbc:mysql://localhost:3306/ApolloPortalDB?characterEncoding=utf8"
set apollo_portal_db_username="root"
set apollo_portal_db_password="123456"

rem meta server url, different environments should have different meta server addresses
set dev_meta="http://192.168.9.164:8080"
set fat_meta="http://192.168.5.90:8080"
set uat_meta="http://172.21.11.73:8080"
set pro_meta="http://apolloconfig.1hai.cn"

set META_SERVERS_OPTS=-Dpro_meta=%pro_meta% -Ddev_meta=%dev_meta% -Dfat_meta=%fat_meta% -Duat_meta=%uat_meta%

rem =============== Please do not modify the following content =============== 
rem go to script directory
cd "%~dp0"

cd ..

rem package config-service and admin-service
echo "==== starting to build config-service and admin-service ===="

call mvn clean package -DskipTests -pl apollo-configservice,apollo-adminservice -am -Dapollo_profile=github -Dspring_datasource_url=%apollo_config_db_url% -Dspring_datasource_username=%apollo_config_db_username% -Dspring_datasource_password=%apollo_config_db_password%

echo "==== building config-service and admin-service finished ===="

echo "==== starting to build portal ===="

call mvn clean package -DskipTests -pl apollo-portal -am -Dapollo_profile=github,auth -Dspring_datasource_url=%apollo_portal_db_url% -Dspring_datasource_username=%apollo_portal_db_username% -Dspring_datasource_password=%apollo_portal_db_password% %META_SERVERS_OPTS%

echo "==== building portal finished ===="

echo "==== starting to build client ===="

call mvn clean deploy -DskipTests -pl apollo-client -am %META_SERVERS_OPTS%

echo "==== building client finished ===="

pause
