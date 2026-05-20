pipeline {
    agent any

    stages {
        stage('Fetch Code from SCM') {
            steps {
                // This tells Jenkins to cleanly pull the GitHub repo configured in the UI
                checkout scm
                echo 'Source code synchronized successfully on Amazon Linux host.'
            }
        }

        stage('Validate File Content') {
            steps {
                script {
                    if (fileExists('index.html')) {
                        sh 'grep -q "</html>" index.html'
                        echo 'HTML markup validation processing passed.'
                    } else {
                        error 'Critical Failure: Target build index.html missing.'
                    }
                }
            }
        }

        stage('Store Production Asset') {
            steps {
                archiveArtifacts artifacts: 'index.html', fingerprint: true
            }
        }
    }
}
