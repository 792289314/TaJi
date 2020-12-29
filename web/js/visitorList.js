var vm = new Vue({
    el: '#app',
    component:['el-calendar'],
    data() {
        return {
            allDiaryList: [],
            // 缺一个默认选择的日期
        }
    },
    created() {
        this.getSelectedDiary('2020-12-12');
    },
    methods: {
        // 获得全部日记记录


        getSelectedDiary: function (date) {
            this.$message(date);

            axios({
                url: 'getAllDiaryByDate.do',
                method: 'post',
                data: {'date': date}
            }).then(function (response) {
                if (response.data == "error") {
                    this.$message("啊呀，数据库连接被拒绝了");
                } else {
                    this.allDiaryList = response.data;
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

                }
            }).catch(function (error) {
                this.$message("获取随机日记记录出错 " + error);
            })

        },


        showElement: function () {
            for (let i = 0; i < this.allDiaryList.length; i++) {
                let div = document.createElement('div');
                div.style.width = '100%';
                div.style.border = '1px solid black';
                // div.innerHTML = '{{左边}}';
                div.innerHTML = this.allDiaryList[i].diaryText;

                div.id = 'showD' + i;
                div.style.minHeight = "100px";
                document.getElementById('showDiary').appendChild(div);

            }
        }

    },
});