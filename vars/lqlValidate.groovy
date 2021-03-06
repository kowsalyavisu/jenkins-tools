import groovy.json.JsonSlurper
import groovy.json.JsonOutput
def call(String token, String fileName, String env) {
    def queryParam = getUserData(fileName, env)  
    def curlCommand = [
        "curl --show-error --fail",
        "-XPOST 'https://api.lytics.io/api/query/_test?"+queryParam+"'",
        "-H 'Content-type: application/json'",
        "-H 'Authorization: "+token+"'",
        "--data-binary @"+fileName 
    ]
    sh curlCommand.join(" ")
}



@NonCPS
def getUserData(String fileName, String env) {
  def testFileName = fileName.replace("lql", "json")
  def fileContent = new File("/Users/kviswanathan/.jenkins/workspace/test-pipe/"+testFileName).text
  def resultJson = new JsonSlurper() .parseText(fileContent)
  def queryParam = resultJson.collect { it }.join('&')
  return queryParam
}