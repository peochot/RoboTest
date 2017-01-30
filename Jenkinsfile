
@NonCPS
def branch(pattern, body) {
    echo "Current branch ${env.BRANCH_NAME}"
    echo "Pattern ${pattern}"
    def matcher = (env.BRANCH_NAME =~ pattern)
    if(matcher.matches())
       body()
    else
       echo "Branch doesn't match the pattern. Skipping step"
}

stage('Build') {
  node {
    checkout scm
  }
}


stage('Unit Tests') {
  node {
    println "branch "+ env.BRANCH_NAME
    println "Unit Test "
  }
}
branch(/^master$/,{
  stage('Acceptance Tests') {
    node {
      println "Acceptance Test "
    }
  }
})
