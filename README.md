# pfm-mustic9: Distributed computing systems for cracking
Distributed computing systems for cracking


## Compile Instructions
If you want compile the full project, you must have set the environment variable named `MW_HOME`. This variable references your Oracle Middleware instalation path (Weblogic Server Version 10.3.6.0 & OSB Version 11.1.1.7).

After that, you can run maven: 

`mvn -Dosb.home=$MW_HOME/Oracle_OSB1 -Dweblogic.home=$MW_HOME/wlserver_10.3 clean install`


## LICENSE
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
