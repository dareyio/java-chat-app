# Java Chat Application

This is a simple console-based chat application implemented in Java.

## Overview

The application is composed of a server and a client. Multiple clients can connect to the server and send messages, which are then broadcast to all other connected clients. 

The application is built using Maven and the code is organized into the following classes:

- `Server`: Accepts client connections, broadcasts messages to clients, and handles client disconnects.
- `Client`: Connects to the server and handles sending and receiving messages.
- `UserThread`: Manages the connection for each client on the server side.
- `ReadThread` and `WriteThread`: Handle reading and writing messages on the client side.

## Running the Application

You can run the application using the provided runnable JAR file, `chat-app-1.0-SNAPSHOT.jar`, which is located in the `target` directory. To start the server and two clients, use the following commands in separate terminals:

```bash
java -jar chat-app-1.0-SNAPSHOT.jar server
java -jar chat-app-1.0-SNAPSHOT.jar client
java -jar chat-app-1.0-SNAPSHOT.jar client

## Jenkins CI/CD Pipeline

This project includes a Jenkins pipeline for continuous integration and deployment. The pipeline is defined in the Jenkinsfile located at the root of the project.

The pipeline is organized into three stages:

1. **Build:** This stage compiles the code, runs the tests, and packages the application into a JAR file.
    ```groovy
    stage('Build') {
        steps {
            echo 'Building..'
            sh 'mvn clean package'
        }
    }
    ```

2. **Test:** This stage executes the unit tests and publishes the results using the JUnit plugin.
    ```groovy
    stage('Test') {
        steps {
            echo 'Testing..'
            sh 'mvn test'
        }
        post {
            always {
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }
    }
    ```

3. **Deploy:** This stage deploys the JAR file to a Nexus repository.
    ```groovy
    stage('Deploy') {
        steps {
            echo 'Deploying....'
            script {
                def version = '1.0-SNAPSHOT'
                def artifactId = 'chat-app'
                def groupId = 'com.chatapp'
                def repoUrl = 'http://localhost:8081/repository/maven-releases/'
                sh """
                    mvn deploy:deploy-file \
                        -Durl=${repoUrl} \
                        -DrepositoryId=nexus \
                        -Dfile=target/${artifactId}-${version}.jar \
                        -DgroupId=${groupId} \
                        -DartifactId=${artifactId} \
                        -Dversion=${version} \
                        -Dpackaging=jar \
                        -DgeneratePom=true
                """
            }
        }
    }
    ```

### Jenkins Prerequisites

Before running the pipeline, ensure you have installed the following plugins on your Jenkins server:

- Pipeline
- JUnit
- Maven Integration
- Nexus Artifact Uploader

You also need to configure your Nexus Repository credentials in Jenkins. Navigate to Manage Jenkins > Manage Credentials and add your Nexus Repository credentials. The credential id should be set to 'nexus' to match the `repositoryId` in the Jenkinsfile.

### Running the Jenkins Pipeline

To run the Jenkins pipeline, follow these steps:

1. Create a new pipeline job in Jenkins.
2. Point it to the Git repository where this code is hosted. Jenkins will automatically detect the `Jenkinsfile` and use it to configure the pipeline.
3. Once the pipeline is set up, you can run it by clicking 'Build Now' in Jenkins.

The pipeline will then compile the code, run the tests, and deploy the JAR file to your Nexus repository. The results of each stage will be displayed in Jenkins, and any failures will be highlighted.
