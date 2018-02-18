pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        svn(url: 'https://github.com/jasbir-s-minhas-loblaw/capability-framework', changelog: true, poll: true)
      }
    }
  }
}