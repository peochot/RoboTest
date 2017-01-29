
@NonCPS
def branch(pattern, body) {
    def matcher = (env.BRANCH_NAME =~ pattern)
    if(matcher.matches())
    body()
}

stage('Build') {
  node {
    checkout scm
  }
}


stage('Unit Tests') {
  node {
    println "Unit Test "
  }
}
branch(/testtest/,
  stage('Acceptance Tests') {
    node {
      println "Acceptance Test "
    }
  }
)
