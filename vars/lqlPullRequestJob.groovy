def call() {
  node {
    try {
    stage('SCM') {
      scmCheckout("origin/pr/1/merge", 'https://github.com/kowsalyavisu/lql.git', "+refs/pull/*:refs/remotes/origin/pr/*")
       
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


