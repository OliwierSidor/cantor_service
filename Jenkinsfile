pipeline {
    agent any
    tools {
        jdk 'Java 17'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}
