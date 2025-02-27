// 携带cookie信息 保持session的一致性
axios.defaults.withCredentials = true;
var vm = new Vue({
    el: '#app',
    data: {
        //登录
        userEmail: "", passWord: "",
        //注册
        RuserEmail: "", RuserName: "", RpassWord: "", RRpassWord: "",
        show_loginbox: true, show_registerbox: false,
        //忘记密码
        forgetPwdDialogVisible: false,
        forgetActive: 1,
        forgetEmail: "", forgetPwd: "", forgetRPwd: "",
    },

    methods: {

        login: function () {
            this.show_loginbox = true;
            this.show_registerbox = false;
            var obj = document.getElementById("loginBtn1");
            obj.style.borderBottom = "2px solid #99cdaa";
            document.getElementById("registerBtn1").style.borderBottom = "none";
        },
        register: function () {
            this.show_registerbox = true;
            this.show_loginbox = false;
            var obj = document.getElementById("registerBtn1");
            obj.style.borderBottom = "2px solid #99cdaa";
            document.getElementById("loginBtn1").style.borderBottom = "none";
        },

        //登录校验邮箱和密码 正则表达式
        // 检查邮箱是否符合
        checkEmailFormat: function (email) {
            if (/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,5}$/.test(email)) {
                return true;
            } else {
                return false;
            }
        },

        // 登陆界面为什么要有提示？？？？
        checkUserEmail: function () {

            /* if (!this.checkEmailFormat(this.userEmail)) {
                 // 检查邮箱 不符合时 checkEmailFormat 内部处理 提示报错
                 // * 输入框红框提示
             } else {
                 // 邮箱符合条件
             }*/
        },

        // 检查密码
        checkPasswordFormat: function (pwd) {
            if (/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$/.test(pwd)) {
                return true;
            } else {
                return false;
            }
        },
        // 登陆界面的密码检验
        checkUserpwd: function () {
            /*if (!this.checkPasswordFormat(this.passWord)) {
                // 检查密码 不符合时 checkPasswordFormat 内部处理并提示报错
            } else {
                // 密码符合条件
            }*/
        },


        //注册校验 邮箱密码再次密码昵称
        checkRUserEmail: function () {
            if (!this.checkEmailFormat(this.RuserEmail)) {
                this.$message.error('邮箱格式错误, 请重新输入');
                return false;
            } else {
                // 邮箱符合条件
                return true;
            }
        },


        // 检查 注册界面 用户名的格式
        checkNameFormat: function (name) {
            if (/^[a-zA-Z0-9]{4,8}$/.test(name)) {
                return true;
            } else {
                return false;
            }
        },

        checkRUserName: function () {
            if (!this.checkNameFormat(this.RuserName)) {
                // 检查注册用户名 不符合时 checkRUserName 内部处理 提示报错
                // * 输入框红框提示
                this.$message.error('昵称必须由数字或字母组成,请输入4-8位');
                return false;
            } else {
                return true;
            }
        },


        // 注册界面 检查第一次输入的密码
        checkRUserpwd: function () {
            if (!this.checkPasswordFormat(this.RpassWord)) {
                // 检查密码 不符合时 checkPasswordFormat 内部处理并提示报错
                this.$message.error('密码必须由数字和字母组合,请输入6-10位');
                return false;
            } else {
                // 密码符合条件
                return true;
            }
        },

        checkPassConsistency: function () {//二次密码一致性
            if (this.RpassWord === this.RRpassWord) {
                return true;
            } else {
                this.$message.error('两次密码输入不一致,请重新输入');
                // alert("两次密码输入不一致,请重新输入");
                return false;
            }
        },


        //绿色大登录 按钮 提交账户密码到后台
        onLoginSubmit: function () {

            var self = this;
            axios({
                // 后缀带.do的 Servlet 交给 SpringMVC 处理
                url: "onLoginSubmit.do",
                method: "post",
                data: {
                    'userEmail': self.userEmail,
                    'passWord': self.passWord
                }
                // onLoginSubmit 查询用户是否存在
                // 若不存在 返回 error
                // 若存在 将用户数据写入到 session 中
            }).then(function (response) {
                if (response.data === "error") {
                    // 登陆失败
                    self.$message.error('请检查输入');
                    //错误并清空
                    self.userEmail = "";
                    self.passWord = "";
                } else {
                    //登录成功，0.5s后调入主页面
                    self.$message({
                        message: '登录成功！正在跳转页面ing...',
                        type: 'success'
                    });
                    // 把用户名放到sessionStorage里
                    sessionStorage.setItem("name", response.data);

                    setTimeout(function () {
                        window.location.href = "TaJiMain/diaryMain.html";
                    }, 500);
                }
            }).catch(function (error) {
                console.log(error)
            })
        },


        // 注册大按钮
        onRegisterSubmit: function () {

            // 检测 两次密码是否一致 、 邮箱是否填对、
            // 若不一致 提示问题出在哪儿 后面的操作不执行 直接return
            if (!this.checkRUserEmail() || !this.checkRUserName() ||
                !this.checkRUserpwd() || !this.checkPassConsistency) return;

            var self = this;
            axios({
                url: "onRegisterSubmit.do",
                method: 'post',
                data: {
                    'userEmail': self.RuserEmail,
                    'userName': self.RuserName,
                    'passWord': self.RpassWord,
                }
                // onRegisterSubmit 注册
                // 若注册失败 返回 error
                // 若注册成功 将用户数据写入到 session 中
            }).then(function (response) {
                if (response.data == "error") {
                    // * 提示 注册失败
                    //alert("注册失败");
                    self.$message("注册失败");
                    self.RuserEmail = "";
                    self.RuserName = "";
                    self.RpassWord = "";
                    self.RRpassWord = "";
                } else {
                    self.$message({
                        message: "欢迎用户" + self.RuserName + "的到来",
                        type: 'success'
                    });
                    sessionStorage.setItem("name", self.RuserName);

                    //登录成功，0.5s后调入主页面
                    setTimeout(function () {
                        window.location.href = "TaJiMain/diaryMain.html";
                    }, 500);

                }
            })
        },

        //忘记密码按钮
        forgetPwdClick: function () {
            this.forgetPwdDialogVisible = true;
        },
        //忘记密码弹出窗口 确定按钮
        forgetSureClick: function () {
            this.forgetPwdDialogVisible = false;
        },

        // 忘记密码第一步 输入邮箱 判断邮箱是否存在
        //  由于 axios是异步进行的 如果不加 async/await 就会导致 axios进行的同时 flag未赋值为true就被返回
        // 所以 要么直接在axios里处理  不使用flag 直接在then里写 self.forgetActive++
        //  要么 如下
        forgetStep1: async function () {
            const self = this;
            var flag = false;
            await axios({
                url: 'forgetEmail.do',
                method: 'post',
                data: {email: self.forgetEmail}
            }).then(function (response) {
                if (response.data == "error") {
                    self.$message.error("该邮箱未被注册过哦！");
                } else {// ok
                    flag = true;
                }

            }).catch(function (error) {
                self.$message.error("忘记密码第一步出问题了 " + error);
            })
            return flag;
        },

        // 忘记密码第2步 修改密码
        forgetStep2: async function () {
            const self = this;
            var flag = false;
            await axios({
                url: 'forgetPassword.do',
                method: 'post',
                data: {
                    email: self.forgetEmail,
                    password: self.forgetPwd
                }
            }).then(function (response) {
                if (response.data == "error") {
                    self.$message.error("数据库连接出问题");
                } else {// ok
                    flag = true;
                }

            }).catch(function (error) {
                self.$message.error("忘记密码第二步出问题了 " + error);
            })
            return flag;
        },

        //忘记密码弹出窗口 下一步按钮
        pwdNextClick: function () {
            if (this.forgetActive == 1) {
                if (this.forgetStep1()) {
                    this.forgetActive++;
                }
            } else if (this.forgetActive == 2) {
                if (this.forgetPwd != this.forgetRPwd) {
                    this.$message.error("两次输入的密码不一致");
                } else if (this.forgetStep2()) {
                    this.forgetActive++;
                }
            } else if (this.forgetActive == 3) {
                this.forgetActive = 1;
            }
        },
        pwdBeforeClick: function () {
            this.forgetActive = 1;
        },
        forgetLogin: function () {
            window.location.href = document.referrer;//跳转上一个页面并刷新
        }
    },
});
