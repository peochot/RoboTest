
stage('Build') {
  properties([[$class: 'BuildBlockerProperty', blockLevel: <object of type hudson.plugins.buildblocker.BuildBlockerProperty.BlockLevel>, blockingJobs: '.*t.*', scanQueueFor: <object of type hudson.plugins.buildblocker.BuildBlockerProperty.QueueScanScope>, useBuildBlocker: true], [$class: 'ThrottleJobProperty', categories: [], limitOneJobWithMatchingParams: false, maxConcurrentPerNode: 0, maxConcurrentTotal: 0, paramsToUseForLimit: '', throttleEnabled: false, throttleOption: 'project'], pipelineTriggers([])])
  node {
    checkout scm
    println env.BRANCH_NAME
    timeout(240) {
      waitUntil {
          def executor = env.EXECUTOR_NUMBER
          echo " branch: ${BRANCH_NAME}"
          echo " build display name: ${env.BUILD_DISPLAY_NAME}"
          echo " node name: ${env.NODE_NAME} "
          echo " executor count: ${env.EXECUTOR_NUMBER}"
          return (executor == 2);
      }
    }
  }
}
