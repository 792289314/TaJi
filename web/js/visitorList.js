var vm = new Vue({
    el: '#app',
    component: ['el-calendar'],
    data() {
        return {
            allDiaryList: [],
        }
    },
    created() {
        const tim = new Date();// 获取本地时间
        // 提取当天的所有公开日记记录
        this.getSelectedDiary(tim.getUTCFullYear() + "-" + (tim.getUTCMonth() + 1) + "-" + tim.getUTCDate());
        // 显示到界面上
        this.showElement();
    },
    methods: {

        // 清空界面
        ClearShowDiaryDiv: function () {
            var div = document.getElementById("showDiary");
            div.innerHTML = "";
        },
        //获得天气图标
        getWeatherToString(x) {
            if (x == 0) return "<i class=\"el-icon-sunny\" style='font-size: 30px;text-align: center;line-height: 60px'></i>";
            if (x == 1) return "<i class=\"el-icon-heavy-rain\" style='font-size: 30px;text-align: center;line-height: 60px'></i>";
            if (x == 2) return "<i class=\"el-icon-cloudy\n\" style='font-size: 30px;text-align: center;line-height: 60px'></i>";
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
        // 根据日期获得当天全部公开日记记录
        getSelectedDiary: function (date) {
           // this.$message(date);
            const self = this;
            axios({
                url: 'getAllDiaryByDate.do',
                method: 'post',
                data: {'date': date}
            }).then(function (response) {
                if (response.data == "error") {
                    self.$message.error("啊呀，数据库连接被拒绝了");
                } else {
                    self.allDiaryList = response.data;
                    self.showElement();
                }
            }).catch(function (error) {
                self.$message.error("获取随机日记记录出错 " + error);
            })

        },


        // 动态生成界面 原生方法 非v-for
        showElement: function () {
            /*  allDiaryList[i] 后端拿来的数据
                          {
                            "diary":{
                                    "classify":{
                                        "color":"#3f3f3f3f",
                                        "name":"未分类"},
                                    "text":"这同样也是一个未分类的日记",
                                    "time":{
                                        "date":13,"day":0,"hours":10,"minutes":46,"month":11,"nanos":0,"seconds":27,
                                        "time":1607827587000,"timezoneOffset":-480,"year":120},
                                    "weather":0},
                            "user":{"name":"zaller12"}
                           }
                    */
            this.ClearShowDiaryDiv();
            if (this.allDiaryList.length != 0) {
                for (let i = 0; i < this.allDiaryList.length; i++) {

                    const t = new Date(this.allDiaryList[i].diary.time.time);
                    var str1 = t.getFullYear() + " 年 " + (t.getUTCMonth() + 1) + " 月 " +
                        t.getUTCDate() + " 日        " +
                        this.getWeekToString(t.getDay());
                    var str2 = t.getHours() + " : " +
                        t.getMinutes() + " : " + t.getSeconds() + "   ";


                    //大盒子
                    let div = document.createElement('div');
                    div.className = "showBogBox";
                    div.id = 'showD' + i;
                    document.getElementById('showDiary').appendChild(div);

                    //显示姓名 分类等的盒子
                    let divTitle = document.createElement('div');
                    divTitle.style.width = "100%";
                    divTitle.style.height = "60px";
                    divTitle.style.padding = "10px 0";
                    divTitle.style.backgroundColor = this.allDiaryList[i].diary.classify.color;
                    divTitle.id = 'showTitle' + i;
                    document.getElementById(div.id).appendChild(divTitle);

                    let divLeft = document.createElement('div');
                    divLeft.className = "titleLeft";
                    divLeft.innerHTML = this.allDiaryList[i].user.name + '<br>' + str1 + '<br>' + str2;

                    divLeft.id = 'showLeft' + i;
                    document.getElementById(divTitle.id).appendChild(divLeft);


                    let divRight = document.createElement('div');
                    divRight.className = "titleRight";
                    divRight.innerHTML = this.getWeatherToString(this.allDiaryList[i].diary.weather);
                    divRight.id = 'showRight' + i;
                    document.getElementById(divTitle.id).appendChild(divRight);

                    //日记内容
                    let divContain = document.createElement('div');
                    divContain.className = "diaryContain";
                    divContain.innerHTML = this.allDiaryList[i].diary.text;
                    divContain.id = 'showContain' + i;
                    document.getElementById(div.id).appendChild(divContain);
                }
            } else {
                let div1 = document.createElement('div');
                div1.className = "noneDiary";
                div1.innerHTML = "当天没有人写过日记诶 Σ（ﾟдﾟlll）";
                document.getElementById('showDiary').appendChild(div1);

            }
        },

        returnClick: function () {
            window.location.href = document.referrer;//跳转上一个页面并刷新
        },

    },


});