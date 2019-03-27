
import groovy.json.JsonOutput

def call() {
  node {
    stage('SCM') {
      checkout() 
    }

    stage('test-lytics') {
      lqlValidate()    
    }
  }
        
}


