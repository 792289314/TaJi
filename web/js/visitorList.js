var vm = new Vue({
    el: '#app',
    component:['el-calendar'],
    data() {
        return {
            allDiaryList: [],
            // 缺一个默认选择的日期
            //data1:'',
        }
    },
    created() {
        const tim=new Date();

        this.getSelectedDiary(tim.getUTCFullYear()+"-"+tim.getUTCMonth()+"-"+tim.getUTCDay());

        this.showElement();
    },
    methods: {

        ClearShowDiaryDiv: function () {
            var div = document.getElementById("showDiary");
            div.innerHTML = "";
        },

        // 根据日期获得当天全部公开日记记录
        getSelectedDiary: function (date) {
           // this.$message(date);
            const self=this;

            axios({
                url: 'getAllDiaryByDate.do',
                method: 'post',
                data: {'date': date}
            }).then(function (response) {
                if (response.data == "error") {
                    self.$message.error("啊呀，数据库连接被拒绝了");
                } else {
                   self.allDiaryList = response.data;

                    /*  { 后台拿来的数据
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
                            }*/
                    self.showElement();
                }
            }).catch(function (error) {
                self.$message.error("获取随机日记记录出错 " + error);
            })

        },


        showElement: function () {
            this.ClearShowDiaryDiv();
            if(this.allDiaryList.length!=0)
            {
                for (let i = 0; i < this.allDiaryList.length; i++) {
                    //大盒子
                    let div = document.createElement('div');
                    div.className = "showBogBox";
                    div.innerHTML = this.allDiaryList[i].diary.text;
                    div.id = 'showD' + i;
                    document.getElementById('showDiary').appendChild(div);

                    //显示姓名 分类等的盒子
                    let divTitle = document.createElement('div');
                    divTitle.style.width="100%";
                    divTitle.style.height="60px";
                    divTitle.style.backgroundColor=this.allDiaryList[i].diary.classify.color;
                    divTitle.id = 'showTitle' + i;
                    document.getElementById(div.id).appendChild(divTitle);

                    // let div = document.createElement('div');
                    // div.className = "showBogBox";
                    // div.innerHTML = this.allDiaryList[i].diary.text;
                    // div.id = 'showD' + i;
                    // document.getElementById('showDiary').appendChild(div);

                }
            }
            else
            {
                let div1 = document.createElement('div');
                div1.className="noneDiary";
                div1.innerHTML = "当天没有人写过日记诶 Σ（ﾟдﾟlll）";
                document.getElementById('showDiary').appendChild(div1);

            }
        },

        returnClick:function () {
            window.location.href = document.referrer;//跳转上一个页面并刷新
        },
    },


});