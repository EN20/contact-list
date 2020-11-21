# contact-list
Sample project for kuehne-nagel.com

This is a sample contact list project

## Requirements:

1. **docker**

2. **oracle database**

    simply follow the instructions here https://github.com/MaksymBilenko/docker-oracle-12c to install and run oracle 12c on docker container.
    
    to avoid port conflicts just forward the oracle 8080 port on your 8081 port or change the application port to somthing else
    
    here is the command: 
    
    `docker run -d -p 8081:8080 -p 1521:1521 -v /my/oracle/data:/u01/app/oracle quay.io/maksymbilenko/oracle-12c`

3. **java 11**

4. **lombok plugin** installed on your Intellij IDEA (just for development)