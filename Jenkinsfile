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
  sh "pybot tests/acceptance"
  step([$class: 'RobotPublisher',
  disableArchiveOutput: false,
  logFileName: 'log.html',
  otherFiles: '',
  outputFileName: 'output.xml',
  outputPath: '.',
  passThreshold: 100,
  reportFileName: 'report.html',
  unstableThreshold: 0]);
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
