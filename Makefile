PROG=Main
OBJ=Main.class DrawPanel.class Bezier.class StatusBar.class ManualInputArea.class

all: $(PROG)

$(PROG): $(OBJ)
	java $(PROG)

%.class : %.java
	javac $^

clean:
	rm $(OBJ)
