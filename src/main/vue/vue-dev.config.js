module.exports = {
    devServer: {
        port: '8082',
        proxy: {
            '/inquiry': {
                // 프록시 요청을 보낼 서버의 주소
                target: 'http://localhost:8081'
            }
        },
    }
}