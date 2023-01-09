pipeline {
    agent any
    tools {
        jdk 'Java 17'
        maven 'Maven 4.0.0'
    }
    checkout scm
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}
