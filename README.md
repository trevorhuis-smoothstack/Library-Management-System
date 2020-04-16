# Library-Management-System

Week 1’s assignment (Library Management System)

Write a program that operates on top of 3 different files and performs provides CRUD functionality for each file to the user. Each file is representing an entity, our entities are Book, Author, and Publisher. Each book has a Publisher and an Author associated with it.

When creating a book, you need to make sure that the corresponding author and publisher exists in the other two files (We need to maintain referential integrity).

When removing a publisher or an author you need to make sure that the corresponding books for the publisher or the author is deleted (We need to maintain referential integrity).

Use as much java 8 functionalities such as lambda’s and streams.

You need to make this program as user friendly as possible.

Write Unit Test Cases for all methods/classes.
Following are what you need to have for each file

Book  book_id, book_name, author_id and publisher_id

Publisher  publisher_id, publisher_name and address

Author  author_id, author_name
