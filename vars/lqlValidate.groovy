import groovy.json.JsonSlurper
import groovy.json.JsonOutput
def call(String token, String testParam, String fileName) {

    //def fileContents = readFile fileName+".json"
    //print(fileContents)
    //final slurper = new JsonSlurperClassic()
    //Map queryParam = new HashMap<>(slurper.parseText(fileContents))
    //print(queryParam)
    def map = parseJson(fileName)
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
def parseJson(String fileName){
  def fileContents = readFile fileName+".json"
  def lazyMap = new groovy.json.JsonSlurper().parseText(fileContents)
  def map = [:]
  for ( prop in lazyMap ) {
      map[prop.key] = prop.value
  }
  return map;
}