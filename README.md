# pfm-mustic9: Distributed computing systems for cracking
Distributed computing systems for cracking


## Compile Instructions
If you want compile the full project, you must have set the environment variables named `MW_HOME` and `JDEV_HOME`:
+ `MW_HOME` variable references your Oracle Middleware instalation path (Weblogic Server Version 10.3.6.0 & OSB Version 11.1.1.7).
+ `JDEV_HOME` variable references your Oracle JDeveloper instalation path (Oracle JDeveloper 11g R1 11.1.1.7.0).

After that, you can run maven: 

`mvn -Dosb.home=$MW_HOME/Oracle_OSB1 -Dweblogic.home=$MW_HOME/wlserver_10.3 -Djdeveloper.home=$JDEV_HOME clean install`


## Bugs and Feedback
For bugs, questions and discussions please use the [Github Issues](https://github.com/eliasgranderubio/pfm-mustic9/issues).


## License
Copyright (C) 2015 Elías Grande Rubio

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License along
with this program; if not, write to the Free Software Foundation, Inc.,
51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
