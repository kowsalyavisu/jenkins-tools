def call(String headSha, String repositoryUrl, String refspec) {
  checkout changelog: false,
    poll: false,
    scm: [
        $class: 'GitSCM',
        branches: [[name: headSha]],
        doGenerateSubmoduleConfigurations: false,
        submoduleCfg: [],
        userRemoteConfigs: [[
                                credentialsId: 'kowsalyavisu@gmail.com',
                                name: 'origin',
                                refspec: refspec,
                                url: repositoryUrl
                            ]]
    ]
  }

       


