def call() {
  node {
    stage('SCM') {
      checkout('test') 
    }

    stage('test-lytics') {
      lqlValidate()    
    }
  }
        
}


