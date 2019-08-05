const webpack = require('webpack')

module.exports ={
    plugins: [
        new webpack.DefinePlugin({
            'login.env':{
                METRICSS3ACCKEY: JSON.stringify(process.env.S3METRICSACC),
                METRICSS3SECKEY: JSON.stringify(process.env.S3METRICSSEC)
            }
        })

    ]

}