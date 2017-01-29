def branch = env.BRANCH_NAME

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

stage('Nofification') {
  node {
    sh "echo 'Send Notifications'"
  }
}
