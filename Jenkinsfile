node('master') {
    stage ("Get git repository") {
        git(
            url: 'http://gitlab.wapsi.tech/tpitz/gatling_terry.git',
            credentialsId: 'LDAP_Jenkins'
        )
    }

    try {
        stage("Execute test") {
            docker.image('maven').inside('') {
                sh "mvn clean gatling:test -Dgatling.simulationClass=com.wapsi.simulations.Nominal"
            }
        }
    } catch (e) {
        throw e
    } finally {
        gatlingArchive()
        sh('mkdir report')
        sh('cp -R target/gatling/$(cat target/gatling/lastRun.txt)/* ./report/')
        publishHTML(
            target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'report',
                reportFiles: 'index.html',
                reportName: 'Gatling report',
                reportTitles: ''
            ]
        )
        junit 'report/js/assertions.xml'
        cleanWs()
    }
}