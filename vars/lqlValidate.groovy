import groovy.json.JsonSlurperClassic
import groovy.json.JsonOutput
def call(String token, String testParam, String fileName) {

    def fileContents = readFile fileName+".json"
    print(fileContents)
    final slurper = new JsonSlurperClassic()
    def queryParam = new HashMap<>(slurper.parseText(fileContents))
    print(queryParam)

    def curlCommand = [
        "curl --show-error --fail",
        "-XPOST 'https://api.lytics.io/api/query/_test?email=test@test.com&ucdmid=ucdmid"+testParam+"'",
        "-H 'Content-type: application/json'",
        "-H 'Authorization: "+token+"'",
        "--data-binary @"+fileName+".lql"
        
    ]
    sh curlCommand.join(" ")
}