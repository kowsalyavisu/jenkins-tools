
import groovy.json.JsonOutput

def call() {
  node {
    stage('checkout') {
      checkout changelog: false,
          poll: false,
          scm: [
              $class: 'GitSCM',
              branches: [[name: '*/master']],
              doGenerateSubmoduleConfigurations: false,
              submoduleCfg: [],
              userRemoteConfigs: [[
                                      credentialsId: 'kowsalyavisu@gmail.com',
                                      name: 'origin',
                                      url: 'https://github.com/kowsalyavisu/lql.git'
                                  ]]
          ]
        }

        stage('test-lytics') {
          def curlCommand = [
              "curl -s",
              "-XPOST 'https://api.lytics.io/api/query/_test?email=test@test.com&ucdmid=ucdmid'",
              "-H 'Content-type: application/json'",
              "-H 'Authorization: at.0324245c93ba580383bf52a5e8c50ff8.ebbe2a01aa043fc7f13a864255fc25d'",
              "--data-binary @user_redshift.lql"
              
          ]
          sh curlCommand.join(" ")
        }
      }
}


