const express = require('express');
const authController = require('../controllers/auth.controller');

const router = express.Router();

router.get('/signup', authController.SignUp);
router.post('/signin', authController.SignIn);
router.get('/verify', authController.Verify);

module.exports = router;