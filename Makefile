PROG=Main
OBJ=Main.class DrawPanel.class Curve.class Point.class

all: $(PROG)

$(PROG): $(OBJ)
	java $(PROG)

%.class : %.java
	javac $^

clean:
	rm $(OBJ)
