import groovy.json.JsonSlurper
import groovy.json.JsonOutput
def call(String token, String testParam, String fileName, String env) {

    print("before calling method")
    def queryParam = getUserData(fileName, env)  
    def curlCommand = [
        "curl --show-error --fail",
        "-XPOST 'https://api.lytics.io/api/query/_test?"+queryParam+"'",
        "-H 'Content-type: application/json'",
        "-H 'Authorization: "+token+"'",
        "--data-binary @"+fileName+".lql" 
    ]
    sh curlCommand.join(" ")
}



@NonCPS
def getUserData(String fileName, String env) {
  def fileContent = new File("/Users/kviswanathan/.jenkins/workspace/test-pipe/user_redshift.json").text
  def resultJson = new JsonSlurper() .parseText(fileContent)
  def queryParam = resultJson.collect { it }.join('&')
  return queryParam
}