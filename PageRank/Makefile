# ***************************************************************
#
# Makefile genérico para compilar programas em Java (IntroCS)
#
# ***************************************************************

JFLAGS = -g
JC = javac-introcs
JAVA = java-introcs

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $<

.java:
	$(JC) $(JFLAGS) $<

clean:
	$(RM) *.class *~

