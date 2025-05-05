# Skip List Character Manager
This C++ command-line program demonstrates the functionality of a Skip List — a probabilistic data structure that allows fast search, insertion, and deletion operations within an ordered sequence of characters.

## 🔍 Overview
The program inserts characters into a skip list, displays the structure at each level after insertion, allows deletion of a character, and performs a search — all while visually showing how the skip list evolves.

## 🧠 What is a Skip List?
A Skip List is a layered linked list where each level acts as an "express lane" for faster access. Higher levels allow skipping over nodes, resulting in O(log n) average time for search, insert, and delete operations.

## ✅ Features
- Insert characters into the skip list

- Dynamically build levels probabilistically

- Display the full multi-level skip list after each operation

- Delete specific characters from the list

- Search for characters and track search path

## 🛠️ How It Works
Each insertion is randomized to determine the level of the new node. The skip list is displayed with Lvl 0 as the base level and Lvl 5 as the highest. Deletion and search operations reflect structural changes or the path taken through the list.

## 🚀 Getting Started
### Prerequisites
C++ compiler (e.g., g++)

Command-line interface (e.g., Terminal, Command Prompt)
The program has a makefile so to run the program you need to run
 ~make~ 
 then 
 make run