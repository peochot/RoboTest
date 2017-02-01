stage('Build') {
  node {
    checkout scm
  }
}

stage('Static Code Analysis') {
  node {
    sh "echo 'Run Static Code Analysis'"
  }
}

stage('Unit Tests') {
  node {
    println env.BRANCH_NAME
    while true
    {
      sleep 1000
      echo " branch: ${BRANCH_NAME}"
      echo " build display name: ${env.BUILD_DISPLAY_NAME}"
      echo " node name: ${env.NODE_NAME} "
      echo " executor count: ${env.EXECUTOR_NUMBER}"
    }
  }
}

stage('Acceptance Tests') {
  node {
    sh "echo 'Run Acceptance Tests'"
  }
}

stage('Nofification') {
  node {
    sh "echo 'Send Notifications'"
  }
}
