pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn clean package'
            }
        }
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
    }
}
