def call() {
  checkout changelog: false,
      poll: false,
      scm: [
          $class: 'GitSCM',
          branches: 'master',
          doGenerateSubmoduleConfigurations: false,
          submoduleCfg: [],
          userRemoteConfigs: [[
                                  credentialsId: 'kowsalyavisu@gmail.com',
                                  name: 'origin',
                                  url: 'https://github.com/kowsalyavisu/lql.git'
                              ]]
      ]
}