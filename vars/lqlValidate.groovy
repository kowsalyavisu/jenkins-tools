import groovy.json.JsonSlurper
import groovy.json.JsonOutput
def call(String token, String testParam, String fileName, String env) {

    //def fileContents = readFile fileName+".json"
    //print(fileContents)
    //final slurper = new JsonSlurperClassic()
    //Map queryParam = new HashMap<>(slurper.parseText(fileContents))
    //print(queryParam)
    print("before calling method")
    def map = getUserData(fileName, env)
    print(map)
    def curlCommand = [
        "curl --show-error --fail",
        "-XPOST 'https://api.lytics.io/api/query/_test?"+map+"'",
        "-H 'Content-type: application/json'",
        "-H 'Authorization: "+token+"'",
        "--data-binary @"+fileName+".lql"
        
    ]
    sh curlCommand.join(" ")
}



@NonCPS
def getUserData(String fileName, String env) {
  print(env)
   String fileContent = new File("/Users/kviswanathan/.jenkins/workspace/test-pipe/user_redshift.json").text
   def jsonSlurper = new JsonSlurper() 
   def resultJson = jsonSlurper.parseText(fileContent)
   print("result-->"+resultJson)
   def res = resultJson.collect { it }.join('&')
   print(res)
   return res
}