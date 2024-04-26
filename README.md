# In-Memory Database with Transaction Support

## Description
This Java application is a simple in-memory key-value store that supports basic transaction operations. It allows starting a transaction, making changes (put), checking the value (get), committing changes, and rolling back to the previous state if needed.

## How to Run the Code
1. Ensure you have Java installed on your system. You can download it from [Oracle's official site](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. Clone this repository to your local machine.
3. Navigate to the `src` directory where `InMemoryDB.java` is located.
4. Compile the Java program:
   ```bash
   javac InMemoryDB.java
5. Run the program:
    ```bash
    java InMemoryDB


## Proposed Feature for Future Assignments
Adding the ability for users to specify transaction types and amounts via a terminal interface would truly enhance the interactivity of this assignment. It would be cool being able to command the database in real time, seeing each transaction logged as it happens. This feature would not only make the experience more engaging but also give users a better understanding of how transactions impact a database.
