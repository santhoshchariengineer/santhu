Groovy
pipeline{
  agent : any
  stages{
    stage{'fetch code from SCM'}
    stage{'validate code '}
    stage{'deploy to ngnix server'}
  }
}

