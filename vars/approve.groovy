
def call() {
    def userInput

    try {
        userInput = input(
                id: 'filename', message: 'File to deploy (separate with , for more than one file)', parameters: [
                [$class: 'StringParameterDefinition', description: '', name: 'fileName']])
    } catch(err) { // input false
        def user = err.getCauses()[0].getUser()
        echo "Aborted by: [${user}]"
    }
    print("user input: "+userInput)
    return userInput
}
