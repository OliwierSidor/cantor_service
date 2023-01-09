pipeline {
    agent any
    tools {
        jdk 'Java 17'
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
