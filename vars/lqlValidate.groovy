 
import groovy.json.JsonOutput
def call(String token, String testParam) {
    def curlCommand = [
        "curl --show-error --fail",
        "-XPOST 'https://api.lytics.io/api/query/_test?"+testParam+"'",
        "-H 'Content-type: application/json'",
        "-H 'Authorization: "+token+"'",
        "--data-binary @user_redshift.lql"
        
    ]
    sh curlCommand.join(" ")
}