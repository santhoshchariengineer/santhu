pipeline {
    // Setting agent to none tells Jenkins not to wait for a specific node label
    agent none 

    stages {
        stage('Fetch Code from SCM') {
            // We specify agent any inside the stage itself to force it to run
            agent any 
            steps {
                checkout scm
                echo 'Source code synchronized successfully on Amazon Linux host.'
            }
        }

        stage('Validate File Content') {
            agent any
            steps {
                script {
                    if (fileExists('index.html')) {
                        sh 'grep "</html>" index.html'
                        echo 'HTML markup validation processing passed.'
                    } else {
                        error 'Critical Failure: Target build index.html missing.'
                    }
                }
            }
        }

        stage('Store Production Asset') {
            agent any
            steps {
                archiveArtifacts artifacts: 'index.html', fingerprint: true
            }
        }
    }
}
