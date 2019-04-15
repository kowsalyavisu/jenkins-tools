def call() {
  node {
    try {

    def lytics_dev = 'at.0324245c93ba580383bf52a5e8c50ff8.ebbe2a01aa043fc7f13a864255fc25da'
    

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
      def approvedUser = ['kviswanathan', 'admin', 'null']
      print("${user}")
      echo "${approvedUser}"
      def users = "${user}"
      if( approvedUser.contains(users) ) { 
        def fileName = approve()
        print(fileName)
        def filesNames = fileName.split(',')
        filesNames.each
        {
          String line -> print(line)
          updateLqlQuery(lytics_dev, line)
        }
      }
      else {
        echo "Access denied"
      }
    }

  }
  catch (err) {
    throw err
  }
}
        
}



def updateLqlQuery(String token, String fileName) {
  print('in update')
    def curlCommand = [
            "curl --show-error --fail",
            "-XPOST 'https://api.lytics.io/api/query?version=new",
            "-H 'Content-type: application/json'",
            "-H 'Authorization: "+token+"'",
            "--data-binary @"+fileName.trim()
    ]
    print(curlCommand)
    sh curlCommand.join(" ")
}





