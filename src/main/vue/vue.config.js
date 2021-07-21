module.exports = {
    // pages: {
    //     index: {
    //         entry: 'src/main.js',
    //         filename: 'manager.html',
    //     }
    // },
    // publicPath: process.env.NODE_ENV === 'production' ? '/vue' : '',
    devServer: {
        proxy: {
            '/inquiry': {
                // 프록시 요청을 보낼 서버의 주소
                target: 'http://localhost:8081'
            }
        }
    }
}