pipeline {
    agent any 

    stages {
        stage('Fetch Code from SCM') {
            steps {
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

        stage('Deploy to Nginx Web Server') {
            steps {
                // Copies the verified file directly to Nginx's live web root directory
                sh 'cp index.html /usr/share/nginx/html/'
                echo 'Deployment Successful! Code is now live on Nginx.'
            }
        }
    }
}
