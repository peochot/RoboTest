@NonCPS
def branch(pattern, body) {
    def matcher = (env.BRANCH_NAME =~ pattern)
    assert matcher.matches()
    body()
}

stage('Build') {
  node {
    checkout scm
  }
}


stage('Unit Tests') {
  node {
    println "cac " + branch

  }
}

stage('Acceptance Tests') {
  node {
    sh "echo 'Run Acceptance Tests'"
    sh "echo $BRANCH_NAME"
  }
}
brach(/master/, ->
  stage('Nofification') {
    node {
      sh "echo 'Send Notifications'"
    }
})
