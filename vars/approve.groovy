 def call() {
        def userInput

        try {
            userInput = input(
                id: 'filename', message: 'File to send (separate with , if more than one)', parameters: [
                [$class: 'StringParameterDefinition', description: '', name: 'fileName']])
        } catch(err) { // input false
            def user = err.getCauses()[0].getUser()
            userInput = false
            echo "Aborted by: [${user}]"
        }
        print("user input: "+userInput)
        return userInput
    }
