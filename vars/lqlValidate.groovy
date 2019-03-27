 
import groovy.json.JsonOutput
call() {
    def curlCommand = [
        "curl --show-error --fail",
        "-XPOST 'https://api.lytics.io/api/query/_test?email=test@test.com&ucdmid=ucdmid'",
        "-H 'Content-type: application/json'",
        "-H 'Authorization: at.0324245c93ba580383bf52a5e8c50ff8.ebbe2a01aa043fc7f13a864255fc25da'",
        "--data-binary @user_redshift.lql"
        
    ]
    sh curlCommand.join(" ")
}