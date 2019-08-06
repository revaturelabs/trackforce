const webpack = require('webpack')

module.exports ={
    plugins: [
        new webpack.DefinePlugin({
            'login.env':{
                METRICSS3ACCKEY: JSON.stringify(process.env.METRICSS3ACCKEY),
                METRICSS3SECKEY: JSON.stringify(process.env.METRICSS3SECKEY),            }
        })

    ]

}