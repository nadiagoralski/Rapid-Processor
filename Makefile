c:
	mvn compile
i:
install:
	mvn package

tFull: tUtils tDB

tUser:
	mvn -Dtest=*User* test

tUtils:
	mvn -Dtest=*UtilTest* test

tDB:
	mvn -Dtest=*DB* test



# .PHONY: all clean 
# #phony tells make that these commands are not file names

# #an example makefile "function", will run everything indented if you say "make out"
# out:
# 	javac Backend.java
# 	#simple command line output

# run: 
# 	java Backend

# #can run multiple functions from one function
# all: out run

# testc:
# 	javac -cp .:junit-4.12.jar BackendTest.java

# test: 
# 	java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore BackendTest

# clean:
# 	rm -f *.class
