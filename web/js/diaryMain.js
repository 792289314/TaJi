var vm = new Vue({
    el: '#app',

    data() {
        return {
            //添加日记里的字体大小
            Font: '50px',
            Color: '#000000',


            textDisabled: true,

            // diaryMainFlag: true,
            modifyDiaryFlag: false,
            modifyDiary: {
                id: 0,// diaryList列表里的第id项记录
                name: '111',
                text1: 'hjkhjkhjkkj',
                deleteDialogVisible: false,
                files: {}
            },// 原先 modifyDiary.html里的内容

            // 该用户所有的分类，默认第一个为 未分类
            userClassify: [],

            //该用户的所有日记 按事件分类
            userDiary: [],

            //当前界面上浏览的列表
            diaryList: [],

            // -------这是一个分割线-------

            welcomeUser: '',//个人中心欢迎你获取用户名
            personalDrawer: false,//个人中心折叠框
            addDrawer: false,//添加日记折叠框
            textarea1: '',//添加日记加的日记内容
            allDiaryMsg: '1',//全部日记数量
            nonDiaryMsg: '1',//未分类日记数量

            classifyList: [],
            classifyValue: 0,//0代表选中全部按钮

            kindOptions: [],
            kindValue: 1,//添加日记选中的分类的value
            kindState: '',

            weatherOptions: [{////添加日记中 天气的下拉列表框 需要给这样的数组
                value: 0,
                label: '晴天'
            }, {
                value: 1,
                label: '多云'
            }, {
                value: 2,
                label: '下雨'
            },],
            weatherValue: 0,
            dataValue: '',

            checkPeoOptions: [
                {//添加日记中 查看权限的下拉列表框 需要给这样的数组
                    value: false,
                    label: '所有人可见'
                },
                {
                    value: true,
                    label: '仅自己可见'
                }],
            checkPeoValue: true,
            brandFold: true,

            // 上传的文件列表
            fileList: [],
            fileData: new FormData,
            uploadData: {
                fieldData: {
                    id: '', // 机构id,
                }
            },

        }
    },

    created() {
        this.welcomeUser = sessionStorage.getItem("name");
        // 刚开始 从后台获取该用户的所有日记信息
        // 先拿所有分类信息 然后根据分类信息 拿到每个分类下的日记
        this.getUserMessage();
    },

    methods: {
        // 获取用户 分类+日记信息 更新用的

        getUserMessage: function () {
            this.getClassify();
            this.getUserDiary();
        },

        ChooseDiary: function () {
            this.getDiaryList();
        },

        visitor: function () {
            /*点击个人中心折叠框中过客列表打开过客页面*/
            window.open("visitorList.html", "_self");
        },
        personClick: function () {
            /*点击个人中心折叠框中过客列表 打开person页面 修改密码*/
            window.open("person.html", "_self");
        },
        classifyMan: function () {
            /*点击个人中心折叠框中过客列表 打开分类管理页面 */
            window.open("classifyManagement.html", "_self");
        },

        // 获得星期中文
        getWeekToString: function (x) {
            if (x === 1) return "星期一";
            if (x === 2) return "星期二";
            if (x === 3) return "星期三";
            if (x === 4) return "星期四";
            if (x === 5) return "星期五";
            if (x === 6) return "星期六";
            return "星期日";
        },
        // 获得天气中文 - 后来改成图标了
        // ps：后悔 为啥不数据库里写好
        getWeatherToString(x) {
            if (x == 0) return "<i class=\"el-icon-sunny\" style='font-size: 30px'></i>";
            if (x == 1) return "<i class=\"el-icon-heavy-rain\" style='font-size: 30px'></i>";
            if (x == 2) return "<i class=\"el-icon-cloudy\n\" style='font-size: 30px'></i>";
        },

        // 补充前导0
        setFill: function (val) {
            return val < 10 ? "0" + val : val;
        },

        // 提取时间信息
        getTimeMessage: function (item) {
            const t = new Date(item.diaryTime.time);
            var str1 = t.getFullYear() + " 年 " + (t.getUTCMonth() + 1) + " 月 " +
                t.getUTCDate() + " 日        " +
                this.getWeekToString(t.getDay());
            var str2 = t.getHours() + " : " +
                this.setFill(t.getMinutes()) + " : " +
                this.setFill(t.getSeconds()) + "     " +
                this.getWeatherToString(item.diaryWeather);
            return str1 + '<br>' + str2;
        },

        //版面中间 动态生成div 从后端拿到日记内容 并一左一右显示
        addElement: function () {
            /*
             后端传来的所有日记数据保存在 diaryList （数组）中， 这个变量我这边建了 但是你那边没有
             {
                "classifyColor":"#3f3f3f3f", // 分类颜色
                "classifyFlag":false, // 该分类是否公开 0-公开 1-私密
                "classifyId":1, // 分类的id
                "classifyName":"未命名", // 分类的名字
                "diaryFlag":false, // 当前日记是否公开 0-公开 1-私密
                "diaryId":1," // 日记编号
                "diaryText":"这是一个未公开的日记", // 日记内容
                "diaryTime":{
                    "date":13,"day":0,"hours":10,"minutes":45,
                    "month":11,"nanos":0,"seconds":45,
                    "time":1607827545000,"timezoneOffset":-480,"year":120},
                    // 一个类型为TimeStamp的时间变量 这个还需要转处理一下 再等等
                "diaryWeather":1 // 当前天气 0-晴天 1-多云 2-雨天
               }
*/
        },

        //点击每一个div触发事件
        diaryDivClick: function (id) {
            this.textDisabled = true;
            this.modifyDiary.id = this.diaryList[id].diaryId;
            this.modifyDiary.name = this.diaryList[id].classifyName;
            this.modifyDiary.text1 = this.diaryList[id].diaryText;
            this.modifyDiary.files = this.diaryList[id].files;

            // this.diaryMainFlag = false;
            this.modifyDiaryFlag = true;
            // document.getElementById("diaryMainFlag").style.display="none";
        },

        // 提取 该用户 所有分类 并 统计 每一个分类 所拥有的日记数量
        // 提取 该用户 所有的 相关日记 按 创建日期排列
        // 点击 分类 显示 该分类下的日记 按创建事件排列
        getClassify: function () {
            var self = this;
            axios({
                url: 'getClassify.do',
                methods: 'post',
            }).then(function (response) {
                if (response.data == "error") {
                    // session 中没有用户id
                    // 此时应该返回登陆界面重新登陆

                } else {
                    // self.userClassify = response.data;
                    self.classifyList = response.data;

                }
            }).catch(function (error) {
                self.$message.error("请求过程中发生错误：" + error);
            })
        },

        // 获得用户的所有日记
        getUserDiary: function () {
            const self = this;
            axios({
                url: 'getUserDiary.do',
                method: 'post'
            }).then(function (response) {
                if (response.data == "error") {
                    // session 中没有用户id
                    // 此时应该返回登陆界面重新登陆
                } else {
                    self.diaryList = self.userDiary = response.data;

                }

            }).catch(function (error) {
                self.$message.error("提交过程中发生错误： error");

            })
        },

        // 从usrDiary中筛选出相关的列表 呈现在界面上
        getDiaryList: function () {
            this.diaryList = [];
            const classifyId = this.classifyList[this.classifyValue].id;
            if (classifyId != 0) { // 用户特色分类
                for (var i = 0; i < this.userDiary.length; i++) {
                    if (this.userDiary[i].classifyId == classifyId) {
                        this.diaryList.push(this.userDiary[i]);
                    }
                }
            } else {// 全部
                this.diaryList = this.userDiary;
            }
        },

        //------------------------------ 图片上传 -------------------------//
        // 上传到服务器
        submitUpload: function (diaryId) {
            this.fileData = new FormData();
            this.$refs.upload.submit();// el-upload  上绑定 ref='upload'
            const self = this;
            let config = {
                headers: {
                    //application/x-www-form-urlencoded 无法进行文件上传。
                    'Content-Type': 'multipart/form-data'
                }
            }
            this.fileData.append("diaryId", diaryId);
            axios({
                url: 'File.do',
                //dataType: 'data',
                //contentType: 'multipart/form-data',
                data: self.fileData,
                method: 'post',
                config: config


            }).then(function (response) {
                if (response.data == 'error') {
                    self.$message.error("文件上传失败！");
                } else {
                    /*self.$message({
                        message: "上传成功",
                        type: 'success'
                    });*/
                    self.fileList = []; // 清空文件列表


                    //--- 衔接Publish()里的内容 ---
                    self.$message({
                        message: '成功添加日记',
                        type: 'success'
                    });
                    //重新获取一遍数据库里的日记信息
                    self.getUserMessage();

                }
            }).catch(function (error) {
                self.$message(error);
            });
        },
        // 文件上传失败 报错
        handleError: function (error, file, fileList) {
            this.$message.error(error);
        },
        // 上传文件
        uploadFile: function (file) {
            this.fileData.append('files', file.file);  // append增加数据
        },

        //监控上传文件列表
        handleChange(file, fileList) {
            let existFile = fileList.slice(0, fileList.length - 1).find(f => f.name === file.name);
            if (existFile) {
                this.$message.error('当前文件已经存在!');
                fileList.pop();
            }
            this.fileList = fileList;
        },
        // 学习博客 https://blog.csdn.net/weixin_43915587/article/details/91953230

        // 上传的图片太大 导致 Failed to load resource: 网络连接已中断。
        //----------------------------------------------------------//

        // 右下角添加日记的发布按钮
        Publish: async function () {
            const self = this;
            this.addDrawer = false;
            const time = new Date().getTime(); // 获取当前时间
            var data = {
                "classifyId": this.classifyList[this.kindValue].id,
                "diaryFlag": (this.checkPeoValue || this.classifyList[this.kindValue].flag),
                "diaryText": this.textarea1.valueOf(),
                "diaryTime": time.valueOf(),
                "diaryWeather": this.weatherValue
            };

            await axios({
                url: 'addDiary.do',
                method: 'post',
                data: data,/*
                // 下面这一部分解决 diaryText乱码问题 // 发现后端设置一下就行
                responseType: 'blob',
                transformResponse: [function (data) {
                    let reader = new FileReader();
                    reader.readAsText(data, 'GBK');
                    reader.onload = function (e) {
                        console.log(reader.result);
                    }
                    return data;
                }]*/
            }).then(function (response) {
                if (response.data != "error") {
                    //QAQ不好意思 这了写臭了 ...至于为啥写臭呢...因为图片上传这部分学的晚..
                    //获得diaryId后 连接图片和日记的关系
                    self.submitUpload(response.data);

                } else {
                    self.$message.error("添加日记操作失败");
                }

                /*if (response == "error") {
                    self.$message.error("添加日记操作失败");
                } else {
                    self.$message({
                        message: '成功添加日记',
                        type: 'success'
                    });
                    //重新获取一遍数据库里的日记信息
                    self.getUserMessage();
                }*/
            }).catch(function (error) {
                self.$message.error("请求过程中发生错误：" + error);
            })


        },

        Exit: function () {
            const self = this;
            axios({
                url: 'Exit.do',
                method: 'post'
            }).then(function (response) {
                self.$message({
                    message: "退出成功！正在返回登陆界面ing...",
                    type: 'success'
                });
                //不知道为什么 后台控制的跳转没成功 response.sendRedirect后 需要手动刷新才能打开
                // 这里暂时先这么写着
                //window.open("../account.html");  这是在新的窗口打开
                setTimeout(function () {
                    window.location.href = "../account.html";
                }, 500);

            }).catch(function (error) {
                self.$message.error("退出失败！" + error);
            })
        },


        /* ---- modifyDiary 里的方法     ---- */


        /* 删除日记*/
        deleteBtn: function () {
            this.modifyDiary.deleteDialogVisible = true;

        },
        editBtnCli: function () {
            this.textDisabled = false;
        },
        /* 确定删除日记*/
        sureDeleteClick: function () {
            this.modifyDiary.deleteDialogVisible = false
            const self = this;
            axios({
                url: 'deleteDiary.do',
                method: 'post',
                data: {
                    diaryId: self.modifyDiary.id
                }
            }).then(function (response) {
                if (response.data == "error") self.$message("删除日记失败");
                else {
                    self.$message({
                        message: "成功删除日记！",
                        type: 'success'
                    });
                    self.classifyValue = 0;
                    self.getUserMessage();
                    self.modifyDiaryFlag = false;
                }
            }).catch(function (error) {
                self.$message.error("删除日记发生错误 " + error);
            })

        },
        /* 日记div*/
        editDiaryBox: function () {

        },
        /* 保存日记*/
        saveClick: function () {
            //this.diaryMainFlag = true;
            const self = this;

            // 更新后台数据库里的内容
            axios({
                url: 'modifyDiary.do',
                method: 'post',
                data: {
                    diaryId: self.modifyDiary.id,
                    diaryText: self.modifyDiary.text1
                }
            }).then(function (response) {
                if (response.data == "error") {
                    self.$message.error("修改日记失败");
                } else {
                    self.$message({
                        message: "修改成功！",
                        type: 'success'
                    });
                    self.classifyValue = 0;
                    self.getUserMessage();
                    self.modifyDiaryFlag = false;
                }
            }).catch(function (error) {
                self.$message.error("修改日记发生错误 " + error);
            })
        },

        // 返回按钮
        returnClick: function () {
            this.modifyDiaryFlag = false;
            //this.diaryMainFlag = true;
            //this.addElement();
        },


    },
    watch: {
        Font: function (val) {
            //    this.$message(val);
            document.getElementById("addTextArea").style.fontSize = "" + val + "px";
        },
        Color: function (val) {
            document.getElementById("addTextArea").style.color = val;
        }
    }

});