const jsonServer = require('json-server')
const server = jsonServer.create()
const router = jsonServer.router('db.json')
const middlewares = jsonServer.defaults()

server.use(middlewares);

// To handle POST, PUT and PATCH you need to use a body-parser
// You can use the one used by JSON Server
server.use(jsonServer.bodyParser)
// server.use((req, res, next) => {
//     console.log(router.db.__wrapped__);
//     // if (req.method === 'POST' && req.path === '/caliber/authentication/login') {

//     // }
//     // Continue to JSON Server router
//     next()
// })

server.post('/caliber/authentication/login', function (req, res) {
    let users = router.db.__wrapped__.users;
    let username = req.body.userName;
    let password = req.body.password;
    res.setHeader("Content-Type","application/json");
    for (let i = 0; i < users.length; i++) {
        if (users[i].username === username && users[i].password === password) {
            res.send(JSON.stringify(
                {
                    statusCode: 200,
                    description: "You have been authenticated successfully.",
                    data: "ENCRYPTED_TOKEN"
                }
            ));
            return;
        }
    }
    res.send(JSON.stringify(
        {
            statusCode: 401,
            description: "Incorrect username or password.",
            data: null
        }
    ));
});

server.use(router);
server.listen(3000, () => {
    console.log('JSON Server is running')
})
