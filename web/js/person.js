// person界面 后来时间不够 没怎了做了
var vm = new Vue({
    el: '#app',
    data() {
        let checkEmail = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入邮箱账号'));
            }else {
                if (this.ruleForm.checkEmail !== '') {
                    this.$refs.ruleForm.validateField('checkEmail');
                }
                callback();
            }

        };
        let checkName = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入昵称'));
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
            }
        };
        let checkPwd = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm.pwd !== '') {
                    this.$refs.ruleForm.validateField('checkPwd');
                }
                callback();
            }
        };
        let checkRepwd = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.pwd) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            labelPosition: 'right',
            ruleForm: {
                email:'',
                name: '',
                pwd: '',
                repwd: '',

            },
            rules: {
                email: [
                    { validator: checkEmail, trigger: 'blur' }
                ],
                name: [
                    { validator: checkName, trigger: 'blur' }
                ],
                pwd: [
                    { validator: checkPwd, trigger: 'blur' }
                ],
                repwd: [
                    { validator: checkRepwd, trigger: 'blur' }
                ],
            }
        };
    },
    methods:
        {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            returnClick:function () {
                window.location.href = document.referrer;//跳转上一个页面并刷新
            },
        },
});