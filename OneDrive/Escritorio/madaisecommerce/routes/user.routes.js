const router = require('express').Router();
const UserController = require('../controller/userCtrl');

router.post(
    '/createUser',
    [],
    UserController.createUser
);

router.get(
    '/confirm/:token',
    [],
    UserController.confirm
);

module.exports = router;