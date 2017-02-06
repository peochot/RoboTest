{ ->
  node {
      stage("Build") {
          echo "Building"
      }

      stage("Test") {
          echo "Testing"
      }

      branch(/^master$/){
          stage("Deploy") {
              echo "Deploying"
          }
      }
  }
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
