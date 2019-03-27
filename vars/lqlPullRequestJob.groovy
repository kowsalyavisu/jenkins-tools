def call() {
  node {
    try {
    stage('SCM') {
      checkout('test') 
    }

    stage('test-lytics') {
      lqlValidate()    
    }
  }
  catch (err){

  }
}
        
}


