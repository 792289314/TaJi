var vm = new Vue({
        el: '#app',
        data() {
            return {

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
                // allRadio:'-1',//绑定第一次选中全部按钮
                allDiaryMsg: '1',//全部日记数量
                nonDiaryMsg: '1',//未分类日记数量
                /*classifyList: [
                    {
                        value: '12',
                        classifyName: '旅行',
                    },
                    {
                        value: '13',
                        classifyName: '心情',
                    },
                    {
                        value: '14',//此项分类的数量
                        classifyName: '计划',
                    },
                ],
                classifyValue: '',*/
                /*
                    {"color":"#3f3f3f3f","flag":false,"id":1,"name":"未命名",cnt:9}
                    {"color":"#4a4a4a4a","flag":false,"id":2,"name":"学习",cnt:10}
                */
                classifyList: [
                    {//头部 显示右哪些分类的单选按钮
                        id: '2',
                        label: 2,
                        value: '12',
                        classifyName: '旅行',
                    },
                    {
                        id: '3',
                        label: 3,
                        value: '13',
                        classifyName: '心情',
                    },
                    {
                        id: '4',
                        label: 4,
                        value: '14',//此项分类的数量
                        classifyName: '计划',
                    }],
                classifyValue: 0,//-1代表选中全部按钮

                kindOptions: [
                    /*{
                        数组id
                        分类id
                        分类名
                        分类状态：公开/私密
                        分类显示的颜色
                    },*/
                    {
                        //添加日记中 分类的下拉列表框 需要给这样的数组
                        value: '选项1',// 值改成 用户分类的id
                        label: '旅游',
                        state: 0,
                        kindColor: '#ffff',
                    },
                    {
                        value: '选项2',
                        label: '心情',
                        state: 0,
                        kindColor: '#ffff',
                    },
                    {
                        value: '选项3',
                        label: '计划',
                        state: 0,
                        kindColor: '#ffff',
                    },
                    {
                        value: '选项4',
                        label: '与男友的事',
                        state: 0,
                        kindColor: '#ffff',
                    },
                    {
                        value: '选项5',
                        label: '。。。',
                        state: 0,
                        kindColor: '#ffff',
                    }],
                kindValue: '',//添加日记选中的分类的value
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
                weatherValue: '',
                dataValue: '',

                checkPeoOptions: [
                    {//添加日记中 查看权限的下拉列表框 需要给这样的数组
                        value: 0,
                        label: '所有人可见'
                    },
                    {
                        value: 1,
                        label: '仅自己可见'
                    }],
                checkPeoValue: '',


                brandFold: true,
            }
        },

        created() {
            this.welcomeUser = sessionStorage.getItem("name");
            // 刚开始 从后台获取该用户的所有日记信息
            // 先拿所有分类信息 然后根据分类信息 拿到每个分类下的日记
            this.getClassify();
            this.getUserDiary();
            //this.addElement();


        },
        methods: {
            ChooseDiary: function (val) {
                this.getDiaryList(val);
                var div = document.getElementById("move");
                div.innerHTML = "";
                this.addElement();
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
            // 获得天气中文
            // ps：后悔 为啥不数据库里写好
            getWeatherToString(x) {
                if (x == 0) return "晴";
                if (x == 1) return "雨天";
                if (x == 2) return "多云";
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

                for (let i = 0; i < this.diaryList.length; i++) {
                    /*
                                 2020年12月12日 星期x
                                 20:45:45 晴（
                    */
                    const t = new Date(this.diaryList[i].diaryTime.time);
                    var str1 = t.getFullYear() + " 年 " + (t.getUTCMonth() + 1) + " 月 " +
                        t.getUTCDate() + " 日        " +
                        this.getWeekToString(t.getDay());
                    var str2 = t.getHours() + " : " +
                        t.getMinutes() + " : " + t.getSeconds() + "   " +
                        this.getWeatherToString(this.diaryList[i].diaryWeather);

                    if (i % 2 === 0)//偶数 显示在左边
                    {
                        let div = document.createElement('div');
                        // div.style.backgroundColor = 'red';
                        div.className = "move_div";
                        // 放日记主体text
                        //div.innerHTML = '{{左边}}';
                        div.innerHTML = this.diaryList[i].diaryText;
                        div.id = 'Elem' + i;
                        //var div_h = div.offsetHeight;
                        //alert(div_h);
                        document.getElementById('move').appendChild(div);

                        let div2 = document.createElement('div');
                        div2.className = "move_div2";
                        div2.innerHTML = str1 + '<br>' + str2;
                        div2.id = 'Ele' + i;
                        document.getElementById('move').appendChild(div2);

                    } else {
                        let div1 = document.createElement('div');
                        div1.className = "move_div1";


                        //div1.innerHTML = '{{右边}}';
                        div1.innerHTML = this.diaryList[i].diaryText;
                        div1.id = 'Elem' + i;

                        document.getElementById('move').appendChild(div1);


                        let div3 = document.createElement('div');
                        div3.className = "move_div3";
                        div3.innerHTML = str1 + '<br>' + str2;
                        div3.id = 'Ele' + i;
                        document.getElementById('move').appendChild(div3);
                    }
                }
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
                    self.$message("请求过程中发生错误：" + error);
                })
            },

            // 获得用户的所有日记
            getUserDiary: function () {
                const self = this;
                axios({
                    url: 'getUserDiary.do',
                    methods: 'post'
                }).then(function (response) {
                    if (response.data == "error") {
                        // session 中没有用户id
                        // 此时应该返回登陆界面重新登陆
                    } else {
                        self.diaryList = self.userDiary = response.data;
                        self.addElement();// 得到日记记录后 更新ui
                    }

                }).catch(function (error) {
                    self.$message("提交过程中发生错误： error");

                })
            },


            // 从usrDiary中筛选出相关的列表 呈现在界面上
            getDiaryList: function (classifyId) {
                this.diaryList = [];
                //alert(classifyId);
                if (classifyId != 0) { // 用户特色分类
                    for (var i = 0; i < this.userDiary.length; i++) {
                        if (this.userDiary[i].classifyId == classifyId) {
                            // this.diaryList.add(this.userDiary[i]);
                            this.diaryList.push(this.userDiary[i]);
                        }
                    }
                } else {// 全部
                    this.diaryList = this.userDiary;
                }
            },

            // 还没写完
            // 右下角添加日记的发布按钮
            Publish: function () {
                let self = this;
                //alert(self.kindValue);
                let selectedColor = document.getElementById('selectedColor').value;
                let selectedFont = document.getElementById('selectedFont').value;
                // self.weatherValue
                const time = new Date().getTime(); // 获取当前时间
                //alert(time);
                // self.checkPeoValue
                //alert(self.textarea1)

                axios({
                    url: 'addDiary.do',
                    methods: 'post',
                    data: {
                        'classifyId': self.kindOptions[self.kindValue].value,
                        //'userId': '',在 java后台session里
                        'diaryFlag': self.checkPeoValue | self.kindOptions[self.kindValue].state,// 分类flag | 谁可以看flag
                        'diaryText': self.textarea1,
                        'diaryTime': time,
                        'diaryWeather': self.weatherValue
                    }
                }).then(function (response) {
                    if (response == "error") {
                        self.$message("添加日记操作失败");
                    } else {
                        self.$message("成功添加日记");
                        // 刷新界面 重新进入该网页

                    }
                }).catch(function (error) {
                    self.$message("请求过程中发生错误：" + error);
                })
            },

            exit: function () {
                axios({
                    url: '',
                    methods: 'post'
                }).then(function (response) {

                }).catch(function (error) {

                })
            },


        },
    });