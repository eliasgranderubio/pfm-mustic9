# pfm-mustic9: Distributed computing systems for cracking
Distributed computing systems for cracking

## Compile Instructions
If you want compile the full project, you must have set the environment variable named `MW_HOME`. This variable references your Oracle Middleware instalation path (Weblogic Server Version 10.3.6.0 & OSB Version 11.1.1.7).

After that, you can run maven: 

`mvn -Dosb.home=$MW_HOME/Oracle_OSB1 -Dweblogic.home=$MW_HOME/wlserver_10.3 clean install`
