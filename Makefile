PROG=Main
OBJ=Main.class

all: $(PROG)

$(PROG): $(OBJ)
	java $(PROG)

%.class : %.java
	javac $^

clean:
	rm $(OBJ)
