# Sudoku Solver in Java

## Overview

This project implements a **Sudoku Solver** using a **3D sparse table** and **linked lists** in Java. It applies **iterative techniques** to solve Sudoku puzzles efficiently. The solution is designed to handle **m × n Sudoku grids**, allowing for different board sizes beyond the traditional 9×9 format.

## Features

* **Custom Singly Linked List**: No built-in Java data structures were used.
* **3D Sparse Table Representation**: Efficiently links Sudoku rows, columns, and blocks.
* **Iterative Solving Techniques**:
   * **Sole Candidate**: Fills in cells with only one possible value.
   * **Unique Candidate**: Identifies the only valid placement for a number in a row, column, or block.
   * **Duplicate Cells**: Removes unnecessary possibilities using a subset elimination technique.

## Project Structure

```
📁 SudokuSolver
├── 📄 Board.java       # Represents the Sudoku grid
├── 📄 Cell.java        # Represents an individual Sudoku cell
├── 📄 List.java        # Custom linked list implementation
├── 📄 Node.java        # Node for the linked list
├── 📄 SudokuSolver.java # Main logic for solving Sudoku
├── 📄 Main.java        # Entry point of the application
```

## Installation & Usage

### 1. Clone the Repository

```bash/wsl
git clone https://github.com/your-username/sudoku-solver.git
cd sudoku-solver
```

### 2. Compile the Java Files

```bash/wsl
javac *.java
```

### 3. Run the Program

```bash/wsl
java Main
```

## Input Format

The input file should follow this format:

```
2x3 1 2 3 4 5 6 - - - 1 2 3 4 5 6 - - - ...
```

Where:
* `2x3` defines the block size
* `-` represents empty cells

## Example Output

```
Solved Sudoku: 4 6 2 5 3 1 3 1 5 6 2 4 ...
Number of moves: 12
```

## Key Algorithms

The Sudoku solver implements several key solving strategies:

1. **Sole Candidate Method**: 
   - Identifies cells with only one possible value
   - Fills these cells immediately
   - Reduces complexity of the solving process

2. **Unique Candidate Technique**:
   - Scans rows, columns, and blocks
   - Identifies numbers that can only be placed in one specific cell
   - Eliminates possibilities systematically

3. **Subset Elimination**:
   - Removes redundant cell possibilities
   - Helps in reducing the search space
   - Improves solving efficiency

## Performance Considerations

- **Space Complexity**: O(m * n * k), where m and n are grid dimensions, k is the number of possible values
- **Time Complexity**: Varies based on puzzle difficulty, but optimized through iterative techniques
- **Scalability**: Supports variable grid sizes beyond traditional 9x9 Sudoku

## Potential Improvements

- Is to implement more advanced solving techniques
- Add support for generating Sudoku puzzles
- Create a graphical user interface
- Add difficulty level detection


## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.