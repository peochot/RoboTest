{ ->
  node {
      stage("Build") {
          echo "Building"
          notifyStarted()
      }
      stage("Unit Test") {
          echo "Unit testing"
      }
      branch(/^master$/){
          stage("Pushing to stage") {
              echo "Push"
          }
          stage("Trigger stage job") {
              echo "Deploying"
          }
      }


      branch(/^stage$/){
          stage("Acceptance Test") {
              echo "Testing"
          }
          stage("Deploy") {
              echo "Deploying"
          }
      }
  }
}

def notifyStarted() {
  // send to HipChat
  hipchatSend (color: 'YELLOW', notify: true,
      message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})"
    )
}

@NonCPS
def branch(pattern, body) {
    echo "Current branch: ${env.BRANCH_NAME}"
    echo "Pattern: ${pattern}"
    def matcher = (env.BRANCH_NAME =~ pattern)
    if(matcher.matches())
       body()
    else
       echo "Branch doesn't match the pattern. Skipping step"
}
