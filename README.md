# BFTTP
BFTTP- BrainFuck Text Tranfer Protocol



Current iteration supports all BF commands except ,

Output HTML in index.bf (located in subfolders below BFWebServer/bin/BFInt/ subfolders equivilent to web address)

to start: (in BFWebServer/bin/) java ClientListener


TODO

-Fix error where code doesn't return 404

-Properly migrate Interpreter code into repository

-Write BF code to interpret HTTP requests (rather than handling it in java)

-Add file IO to protocol

-Either write BF interpreter in BF or allow BF code to request Java Interpreter interprets another file before continuing

-Write BF code to Parse HTML files

-Work on BF framework to allow dynamic web pages(either write so something normal interpreted in BF or just BF interpreted in BF)
