#!/usr/bin/python
# -*- coding: utf-8 -*-

import sys
import re

fo = open(sys.argv[2], "w")
fo.seek(0, 0)


def strip_non_ascii(string):
   stripped = (letter for letter in string if (0 < ord(letter) < 127))
   return ''.join(stripped)

def main(argv):
   with open (argv) as f:
      for line in f:
         if ((re.match('^\s*\n', line)) or (re.match('^\n', line)))!= None :
            continue
         else:
            fo.writelines(strip_non_ascii(line))

###
# Arguments
#   argv[1]: input dictionary to clean
#   argv[2]: cleaned output dictionary
###			
if __name__ == "__main__":
	main(sys.argv[1])

