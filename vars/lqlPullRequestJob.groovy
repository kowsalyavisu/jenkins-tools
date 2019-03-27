
import groovy.json.JsonOutput

def call() {
  node {
    stage('checkout') {
      checkout() 
    }

    stage('test-lytics') {
      lqlValidate()    
    }
  }
        
}


