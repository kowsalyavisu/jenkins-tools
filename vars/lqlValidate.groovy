import groovy.json.JsonSlurper
import groovy.json.JsonOutput
def call(String token, String testParam, String fileName) {

    //def fileContents = readFile fileName+".json"
    //print(fileContents)
    //final slurper = new JsonSlurperClassic()
    //Map queryParam = new HashMap<>(slurper.parseText(fileContents))
    //print(queryParam)
    print("before calling method")
    def map = getUserData(fileName)
    print(map)
    def curlCommand = [
        "curl --show-error --fail",
        "-XPOST 'https://api.lytics.io/api/query/_test?email=test@test.com&ucdmid=ucdmid"+testParam+"'",
        "-H 'Content-type: application/json'",
        "-H 'Authorization: "+token+"'",
        "--data-binary @"+fileName+".lql"
        
    ]
    sh curlCommand.join(" ")
}



@NonCPS
def getUserData(String fileName) {
   String fileContent = new File(${env.WORKSPACE}+"/"+fileName+".json").text
   def jsonSlurper = new JsonSlurper() 
   def resultJson = jsonSlurper.parseText(fileContent)
   resultJson.each {
      print “${it.key}”
   }
}