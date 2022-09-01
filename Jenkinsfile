node{
    stage ("Get git repository") {
        git(
            branch: "master",
            url: 'http://gitlab.wapsi.tech/tpitz/gatling_terry.git',
            credentialsId: 'LDAP_Jenkins'
        )
    }

        stage("Execute test") {
            docker.image('maven').inside('') {
                sh "mvn clean gatling:test -Dgatling.simulationClass=com.wapsi.simulations.Nominal"
            }
        
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
