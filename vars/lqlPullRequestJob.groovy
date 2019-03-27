def call() {
  node {
    try {
    stage('SCM') {
      scmCheckout() 
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


