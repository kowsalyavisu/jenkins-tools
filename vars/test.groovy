def call() {
  node {
    stage('prep') {
      checkout changelog: false,
          poll: false,
          scm: [
              $class: 'GitSCM',
              branches: {[[name: 'master']]},
              doGenerateSubmoduleConfigurations: false,
              submoduleCfg: [],
              userRemoteConfigs: [[
                                      credentialsId: 'kowsalyavisu@gmail.com',
                                      name: 'origin',
                                      url: 'https://github.com/kowsalyavisu/lql.git'
                                  ]]
          ]
        }
      }
}

