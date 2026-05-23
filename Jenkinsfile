pipeline {
    agent any 

    stages {
        stage('Fetch Code from SCM') {
            steps {
                echo 'Source code synchronized successfully from GitHub.'
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

        stage('Deploy to Nginx Web Server') {
            steps {
                // 1. Move the verified HTML file to Nginx's web root
                sh 'cp index.html /usr/share/nginx/html/'
                
                // 2. Move your photo file to Nginx's web root
                sh 'cp image0.jpg /usr/share/nginx/html/'
                
                echo 'Deployment Successful! Code and profile image are live.'
            }
        }
    }
}
