declare const login: Process;

interface Process {
    env: Env
}

interface Env{
    METRICSS3ACCKEY: string,
    METRICSS3SECKEY: string
}

interface GlobalEnvironment{
    process: Process
}
