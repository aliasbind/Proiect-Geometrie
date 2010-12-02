PROG=Main
OBJ=Main.class

all: $(PROG)

$(PROG): $(OBJ)
	java $(PROG)

%.class : %.java
	javac %.java

clean:
	rm $(OBJ)
