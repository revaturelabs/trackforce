// hello.js
module.exports = (req, res, next) => {
    if (req.method === 'POST' && req.path === '/caliber/authentication/login') {
        console.log(req.path)
    }
    res.header('X-Hello', 'World')
    next()
  }