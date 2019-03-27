def call() {
  node {
    try {
    stage('SCM') {
      scmCheckout('test') 
    }

    stage('test-lytics') {
      lqlValidate()    
    }
  }
  catch (err) {
    throw err
  }
}
        
}


