# Console based Task Manager
- A samll console based Java application to manage tasks.
- The main intention for developing this application was to apply theoretical Java concepts practically, rather than using the application in daily life.
- Java concept's used to develop this application are as below.
    - Core Java
    - I/O
    - Design patterns
        - Singleton
        - MVCF1
        - Factory



## **Running the application**
- Please install **Java Runtime Environment (JRE)** from [Java Downloads](https://www.java.com/en/download/manual.jsp).
- Create bin folder manually in the root directory of the cloned repository, so that ````.class```` files are placed after compling ````.java```` source files.
- Run the below commands in the terminal
    - ````javac -d <absolute path to recently created bin folder> <absolute path where .java files are present>\*.java````
    - ````java -cp <absolute path to recently created bin folder> <fully qualified class name where main() is present>````

        **Example**
        - ````javac -d C:\task_mvcf1\bin C:\task_mvcf1\src\com\uttara\taskmvc\*.java````
        - ````java -cp C:\task_mvcf1\bin com.uttara.taskmvc.StartApp````
