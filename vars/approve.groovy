 def approve() {
        def userInput

        try {
            userInput = input(
                id: 'Proceed1', message: 'Proceed to next step?', parameters: [
                [$class: 'BooleanParameterDefinition', defaultValue: true, description: '', name: 'Please confirm']])
        } catch(err) { // input false
            def user = err.getCauses()[0].getUser()
            userInput = false
            echo "Aborted by: [${user}]"
        }

        return userInput
    }
