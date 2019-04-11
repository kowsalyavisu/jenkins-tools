def call() {
  node {
    try {

    def lytics_dev = 'at.0324245c93ba580383bf52a5e8c50ff8.ebbe2a01aa043fc7f13a864255fc25da'
    def lytics_qa = 'at.78bcad7041aed5866943ff40f9316faf.41db3e9b37a25031a30c9f5f75fd5c20'

    stage('Prepare') {
      scmCheckout("origin/pr/1/merge", 'https://github.com/kowsalyavisu/lql.git', "+refs/pull/*:refs/remotes/origin/pr/*")
       
    }

    stage('test-lytics') {
      sh "ls *.lql > listJsonFiles"
      def files = new File("/Users/kviswanathan/.jenkins/workspace/test-pipe/listJsonFiles").text
      def lines = files.readLines();
      lines.each {String line -> 
        lqlValidate(lytics_dev, line, "${env.WORKSPACE}")    
      }
    }


    stage('Deploy') {
      approve()
    }

  }
  catch (err) {
    throw err
  }
}
        
}




