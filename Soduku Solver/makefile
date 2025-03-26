default:
	javac *.java

run:
	java Main

clean:
	rm -f *.class
	reset
	clear

tar:
	tar -cvz Board.java Cell.java List.java Node.java SudokuSolver.java -f uXXXXXXXX.tar.gz

unzip:
	tar -zxvf *.tar || tar -zxvf *.tar.gz